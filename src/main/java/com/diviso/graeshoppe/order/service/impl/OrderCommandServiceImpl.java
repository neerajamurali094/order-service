package com.diviso.graeshoppe.order.service.impl;

import com.diviso.graeshoppe.order.service.NotificationService;
import com.diviso.graeshoppe.order.service.OrderCommandService;
import com.diviso.graeshoppe.order.service.OrderLineService;
import com.diviso.graeshoppe.order.service.UniqueOrderIDService;
import com.diviso.graeshoppe.order.service.UserService;
import com.diviso.graeshoppe.order.avro.Address;
import com.diviso.graeshoppe.order.avro.ApprovalDetails;
import com.diviso.graeshoppe.order.avro.DeliveryInfo;
import com.diviso.graeshoppe.order.avro.Order.Builder;
import com.diviso.graeshoppe.order.avro.Status;
import com.diviso.graeshoppe.order.client.bpmn.api.FormsApi;
import com.diviso.graeshoppe.order.client.bpmn.api.ProcessInstancesApi;
import com.diviso.graeshoppe.order.client.bpmn.api.TasksApi;
import com.diviso.graeshoppe.order.client.bpmn.model.DataResponse;
import com.diviso.graeshoppe.order.client.bpmn.model.ProcessInstanceCreateRequest;
import com.diviso.graeshoppe.order.client.bpmn.model.ProcessInstanceResponse;
import com.diviso.graeshoppe.order.client.bpmn.model.RestFormProperty;
import com.diviso.graeshoppe.order.client.bpmn.model.RestVariable;
import com.diviso.graeshoppe.order.client.bpmn.model.SubmitFormRequest;
import com.diviso.graeshoppe.order.client.store.api.StoreResourceApi;
import com.diviso.graeshoppe.order.config.MessageBinderConfiguration;
import com.diviso.graeshoppe.order.domain.AuxilaryOrderLine;
import com.diviso.graeshoppe.order.domain.Order;
import com.diviso.graeshoppe.order.domain.OrderLine;
import com.diviso.graeshoppe.order.models.AcceptOrderRequest;
import com.diviso.graeshoppe.order.models.ProcessPaymentRequest;
import com.diviso.graeshoppe.order.repository.OfferRepository;
import com.diviso.graeshoppe.order.repository.OrderLineRepository;
import com.diviso.graeshoppe.order.repository.OrderRepository;
import com.diviso.graeshoppe.order.repository.search.OrderSearchRepository;
import com.diviso.graeshoppe.order.resource.assembler.CommandResource;
import com.diviso.graeshoppe.order.resource.assembler.ResourceAssembler;
import com.diviso.graeshoppe.order.security.SecurityUtils;
import com.diviso.graeshoppe.order.service.dto.NotificationDTO;
import com.diviso.graeshoppe.order.service.dto.OrderDTO;
import com.diviso.graeshoppe.order.service.dto.UniqueOrderIDDTO;
import com.diviso.graeshoppe.order.service.mapper.OrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Order.
 */
@Service
@Transactional
public class OrderCommandServiceImpl implements OrderCommandService {

	private final Logger log = LoggerFactory.getLogger(OrderCommandServiceImpl.class);

	private final OrderRepository orderRepository;

	private final OrderLineRepository orderLineRepository;

	private final OfferRepository offerRepository;

	private final SimpMessagingTemplate template;

	@Autowired
	private StoreResourceApi storeResourceApi;

	private final OrderMapper orderMapper;

	private final OrderSearchRepository orderSearchRepository;

	@Value("${bpmn.processId}")
	private String BPM_PROCESSDEFINITION_ID;

	@Autowired
	private ProcessInstancesApi processInstancesApi;

	@Autowired
	private UserService userService;

	@Autowired
	private MessageBinderConfiguration messageChannel;

	@Autowired
	private FormsApi formsApi;

	@Autowired
	private TasksApi tasksApi;

	@Autowired
	private UniqueOrderIDService orderIdService;

	@Autowired
	private ResourceAssembler resourceAssembler;

	@Autowired
	public OrderCommandServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper,
			OrderSearchRepository orderSearchRepository, OfferRepository offerRepository,
			SimpMessagingTemplate template, OrderLineRepository orderLineRepository) {
		this.orderRepository = orderRepository;
		this.orderMapper = orderMapper;
		this.template = template;
		this.orderSearchRepository = orderSearchRepository;
		this.orderLineRepository = orderLineRepository;
		this.offerRepository = offerRepository;
	}

	/**
	 * Save a order.
	 *
	 * @param orderDTO the entity to save
	 * @return the persisted entity
	 */
	@Override
	public CommandResource save(OrderDTO orderDTO) {
		UniqueOrderIDDTO orderIdDTO = orderIdService.save(new UniqueOrderIDDTO());
		String orderId = "GR-" + orderIdDTO.getId();
		log.info("Generated Order id is " + orderId);
		orderDTO.setOrderId(orderId);
		log.debug("Request to save Order : {}", orderDTO);
		Order order = orderMapper.toEntity(orderDTO);
		order = orderRepository.save(order);
		OrderDTO result = orderMapper.toDto(order);
		orderSearchRepository.save(order);
		CommandResource resource = initiateOrder(orderId, orderDTO.getStoreId(), orderDTO.getCustomerId(),
				orderDTO.getEmail());
		resource.setSelfId(result.getId());
		log.info("Result Resource is " + resource);
		update(result);
		return resource;
	}

	/**
	 * Update a order.
	 *
	 * @param orderDTO the entity to save
	 * @return the persisted entity
	 */
	@Override
	public OrderDTO update(OrderDTO orderDTO) {
		log.debug("Request to save Order : {}", orderDTO);
		Order order = orderMapper.toEntity(orderDTO);
		order = orderRepository.save(order);
		OrderDTO result = orderMapper.toDto(order);
		orderSearchRepository.save(order);

		return result;
	}

	public CommandResource initiateOrder(String trackingId, String storeId, String customerId, String email) {
		ProcessInstanceCreateRequest processInstanceCreateRequest = new ProcessInstanceCreateRequest();
		processInstanceCreateRequest.setProcessDefinitionId(BPM_PROCESSDEFINITION_ID);

		RestVariable restVariableCustomerId = new RestVariable();
		restVariableCustomerId.setName("customerId");
		restVariableCustomerId.setType("string");
		restVariableCustomerId.setValue(customerId);

		RestVariable restVariableStorekeeperId = new RestVariable();
		restVariableStorekeeperId.setName("storekeeperId");
		restVariableStorekeeperId.setType("string");
		restVariableStorekeeperId.setValue(storeId);

		processInstanceCreateRequest.setVariables(Arrays.asList(restVariableCustomerId, restVariableStorekeeperId));
		ResponseEntity<ProcessInstanceResponse> processInstanceResponse = processInstancesApi
				.createProcessInstance(processInstanceCreateRequest);
		String processInstanceId = processInstanceResponse.getBody().getId();
		log.info("ProcessInstanceId is+ " + processInstanceId);

		ResponseEntity<DataResponse> taskResponseDoctor = tasksApi.getTasks("Initiate Order", null, null, null, null,
				null, null, null, null, null, null, null, null, null, null, null, null, null, processInstanceId, null,
				null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
				null, null, null, null, null, null, "0", null, "desc", "30");
		@SuppressWarnings("unchecked")
		String orderTaskId = ((List<LinkedHashMap<String, String>>) taskResponseDoctor.getBody().getData()).get(0)
				.get("id");
		log.info("InitiateOrder task is " + orderTaskId);
		SubmitFormRequest formRequest = new SubmitFormRequest();
		List<RestFormProperty> properties = new ArrayList<RestFormProperty>();
		RestFormProperty orderId = new RestFormProperty();
		orderId.setId("orderId");
		orderId.setName("orderId");
		orderId.setType("String");
		orderId.setValue(trackingId);
		properties.add(orderId);
		String type = storeResourceApi.findStoreSettingsByStoreIdUsingGET(storeId).getBody().getOrderAcceptType();
		RestFormProperty acceptType = new RestFormProperty();
		acceptType.setId("acceptType");
		acceptType.setName("acceptType");
		acceptType.setType("String");
		acceptType.setValue(type);
		properties.add(acceptType);

		RestFormProperty customerEmail = new RestFormProperty();
		customerEmail.setId("customerEmail");
		customerEmail.setName("customerEmail");
		customerEmail.setType("String");
		customerEmail.setValue(email);
		properties.add(customerEmail);

		formRequest.setProperties(properties);
		formRequest.setAction("completed");
		formRequest.setTaskId(orderTaskId);
		formsApi.submitForm(formRequest);
		CommandResource commandResource = resourceAssembler.toResource(processInstanceId);
		commandResource.setOrderId(trackingId);
		return commandResource;
	}

	/**
	 * Get all the orders.
	 *
	 * @param pageable the pagination information
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<OrderDTO> findAll(Pageable pageable) {
		log.debug("Request to get all Orders");
		return orderRepository.findAll(pageable).map(orderMapper::toDto);
	}

	/**
	 * Get one order by id.
	 *
	 * @param id the id of the entity
	 * @return the entity
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<OrderDTO> findOne(Long id) {
		log.debug("Request to get Order : {}", id);
		return orderRepository.findById(id).map(orderMapper::toDto);
	}

	/**
	 * Delete the order by id.
	 *
	 * @param id the id of the entity
	 */
	@Override
	public void delete(Long id) {
		log.debug("Request to delete Order : {}", id);
		orderRepository.deleteById(id);
		orderSearchRepository.deleteById(id);
	}

	/**
	 * Search for the order corresponding to the query.
	 *
	 * @param query    the query of the search
	 * @param pageable the pagination information
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<OrderDTO> search(String query, Pageable pageable) {
		log.debug("Request to search for a page of Orders for query {}", query);
		return orderSearchRepository.search(queryStringQuery(query), pageable).map(orderMapper::toDto);
	}

	@Override
	public boolean publishMesssage(String orderId) {
		Order order = orderRepository.findByOrderIdAndStatus_Name(orderId, "approved").get();
		order.setOrderLines(orderLineRepository.findByOrder_Id(order.getId()));
		long restaurantCount = orderRepository.countByStoreIdAndCustomerId(order.getStoreId(), order.getCustomerId());
		long graeshoppeCount = orderRepository.countByCustomerId(order.getCustomerId());
		log.info("Order fetched from db is  "+ order);
		log.info("restaurant total count is " + restaurantCount + " Graeshoppe total count " + graeshoppeCount);
		Builder orderAvro = com.diviso.graeshoppe.order.avro.Order.newBuilder()
				.setOrderId(order.getOrderId()).setCustomerId(order.getCustomerId()).setStoreId(order.getStoreId())
				.setPaymentRef(order.getPaymentRef())
				.setOrderCountgraeshoppe(graeshoppeCount)
				.setOrderCountRestaurant(restaurantCount)
				.setDate(order.getDate().toEpochMilli()).setGrandTotal(order.getGrandTotal()).setEmail(order.getEmail())
				.setStatus(Status.newBuilder().setId(Integer.parseInt(order.getStatus().getId() + ""))
						.setName(order.getStatus().getName()).build())
				.setOrderLines(order.getOrderLines().stream().map(this::toAvroOrderLine).collect(Collectors.toList()));
				
		

		if (order.getApprovalDetails() == null) {
			orderAvro.setApprovalDetails(
					ApprovalDetails.newBuilder()
					.setExpectedDelivery(order.getDate().toEpochMilli()).build());
			
		} else {
			orderAvro.setApprovalDetails(ApprovalDetails.newBuilder().setAcceptedAt(order.getDate().toEpochMilli())
					.setExpectedDelivery(order.getApprovalDetails().getExpectedDelivery().toEpochMilli())
					.setDecision(order.getApprovalDetails().getDecision()).build());
		}
		
		if(order.getDeliveryInfo().getDeliveryAddress() != null ) {
				
			orderAvro.setDeliveryInfo(DeliveryInfo.newBuilder().setDeliveryType(order.getDeliveryInfo().getDeliveryType())
					.setDeliveryCharge(order.getDeliveryInfo().getDeliveryCharge())
					.setDeliveryNotes(order.getDeliveryInfo().getDeliveryNotes())
					.setDeliveryAddress(Address.newBuilder()
							.setCustomerId(order.getDeliveryInfo().getDeliveryAddress().getCustomerId())
							.setPincode(order.getDeliveryInfo().getDeliveryAddress().getPincode())
							.setHouseNoOrBuildingName(
									order.getDeliveryInfo().getDeliveryAddress().getHouseNoOrBuildingName())
							.setRoadNameAreaOrStreet(
									order.getDeliveryInfo().getDeliveryAddress().getRoadNameAreaOrStreet())
							.setCity(order.getDeliveryInfo().getDeliveryAddress().getCity())
							.setState(order.getDeliveryInfo().getDeliveryAddress().getState())
							.setLandmark(order.getDeliveryInfo().getDeliveryAddress().getLandmark())
							.setPhone(order.getDeliveryInfo().getDeliveryAddress().getPhone())
							.setAlternatePhone(order.getDeliveryInfo().getDeliveryAddress().getAlternatePhone())
							.build())
					.build());
			
		} 
		com.diviso.graeshoppe.order.avro.Order message =orderAvro.build();
		return messageChannel.orderOut().send(MessageBuilder.withPayload(message).build());
	}

	private com.diviso.graeshoppe.order.avro.OrderLine toAvroOrderLine(OrderLine orderline) {
		return com.diviso.graeshoppe.order.avro.OrderLine.newBuilder().setProductId(orderline.getProductId())
				.setQuantity(orderline.getQuantity()).setPricePerUnit(orderline.getPricePerUnit())
				.setTotal(orderline.getTotal()).setAuxilaryOrderLines(orderline.getRequiedAuxilaries().stream()
						.map(this::toAvroAuxilaryLine).collect(Collectors.toList()))
				.build();
	}

	private com.diviso.graeshoppe.order.avro.AuxilaryOrderLine toAvroAuxilaryLine(AuxilaryOrderLine auxilaryOrderLine) {
		return com.diviso.graeshoppe.order.avro.AuxilaryOrderLine.newBuilder()
				.setProductId(auxilaryOrderLine.getProductId()).setQuantity(auxilaryOrderLine.getQuantity())
				.setPricePerUnit(auxilaryOrderLine.getPricePerUnit()).setTotal(auxilaryOrderLine.getTotal()).build();
	}
}
