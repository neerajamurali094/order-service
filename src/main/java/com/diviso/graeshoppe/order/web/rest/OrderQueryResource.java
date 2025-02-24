package com.diviso.graeshoppe.order.web.rest;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.diviso.graeshoppe.order.client.bpmn.api.HistoryApi;
import com.diviso.graeshoppe.order.client.bpmn.api.TasksApi;
import com.diviso.graeshoppe.order.client.bpmn.model.DataResponse;
import com.diviso.graeshoppe.order.models.OpenTask;
import com.diviso.graeshoppe.order.service.OrderQueryService;
import com.diviso.graeshoppe.order.service.dto.OrderDTO;

@RestController
@RequestMapping("/api")
@SuppressWarnings("unchecked")

public class OrderQueryResource {

	@Autowired
	private TasksApi tasksApi;

	private final Logger log = LoggerFactory.getLogger(OrderQueryResource.class);

	@Autowired
	private HistoryApi historyApi;
	
	@Autowired
	private OrderQueryService orderQueryService;
	
	
	@GetMapping("/taskDetails/{taskName}/{orderId}/{storeId}")
	public OpenTask getTaskDetails(@PathVariable String taskName,@PathVariable String orderId,@PathVariable String storeId) {
		return getTasks(taskName, null, storeId, null, null, null, null, null, null, null)
				.stream().filter(openTask->openTask.getOrderId().equals(orderId)).findFirst().get();
		
	}

	@GetMapping("/tasks")
	public List<OpenTask> getTasks(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "nameLike", required = false) String nameLike,
			@RequestParam(value = "assignee", required = false) String assignee,
			@RequestParam(value = "assigneeLike", required = false) String assigneeLike,
			@RequestParam(value = "candidateUser", required = false) String candidateUser,
			@RequestParam(value = "candidateGroup", required = false) String candidateGroup,
			@RequestParam(value = "candidateGroups", required = false) String candidateGroups,
			@Valid @RequestParam(value = "createdOn", required = false) String createdOn,
			@Valid @RequestParam(value = "createdBefore", required = false) String createdBefore,
			@Valid @RequestParam(value = "createdAfter", required = false) String createdAfter/*Pageable pageable*/) {

		
		ResponseEntity<DataResponse> response = tasksApi.getTasks(name, nameLike, null, null, null, null, assignee,
				assigneeLike, null, null, null, null, candidateUser, candidateGroup, candidateGroups, null, null, null,
				null, null, null, null, null, null, null, null, null, createdOn, createdBefore, createdAfter, null,
				null, null, null, null, null, null, null, null, null, null, null, null, /*pageable.getPageNumber()+""*/"0",null, "desc",/* pageable.getPageSize()+""*/"150");
		List<LinkedHashMap<String, String>> myTasks = (List<LinkedHashMap<String, String>>) response.getBody()
				.getData();
		
		List<OpenTask> tasks=new ArrayList<OpenTask>();
		//Page<OpenTask> page=new PageImpl<>(tasks, pageable, response.getBody().getTotal());
		myTasks.forEach(task -> {
			OpenTask openTask = new OpenTask();
			String taskProcessInstanceId = task.get("processInstanceId");
			String taskName = task.get("name");
			String taskId = task.get("id");
			openTask.setTaskId(taskId);
			openTask.setTaskName(taskName);
			openTask.setOrderId(getOrder(taskProcessInstanceId));
			tasks.add(openTask);
			System.out.println(
					"TaskName is " + taskName + " taskid is " + taskId + " processinstanceId " + taskProcessInstanceId);
		});

		return tasks;
	}

	public String getOrder(String taskProcessInstanceId) {
		log.info("Process Instance id is  " + taskProcessInstanceId);
		List<LinkedHashMap<String, String>> initiateOrderTask = (List<LinkedHashMap<String, String>>) getHistoricTaskusingProcessInstanceIdAndName(
				taskProcessInstanceId, "Initiate Order").getBody().getData();

		Long taskId = initiateOrderTask.stream().mapToLong(obj -> Long.parseLong(obj.get("id"))).max().getAsLong();

		ResponseEntity<DataResponse> orderData = historyApi.getHistoricDetailInfo(null, taskProcessInstanceId, null,
				null, taskId.toString(), true, false);

		List<LinkedHashMap<String, String>> orderDetailsForm = (List<LinkedHashMap<String, String>>) orderData.getBody()
				.getData();

		log.info("Number of tasks in the collection " + initiateOrderTask.size());
		log.info("Task Id of the initiateorder is " + taskId);
		String orderId = null;
		for(LinkedHashMap<String, String> formMap:orderDetailsForm) {
			String propertyId = formMap.get("propertyId");
			if (propertyId.equals("orderId")) {
				orderId = formMap.get("propertyValue");
				log.info("Order Id is ##################" + orderId);
			}
		}
		return orderId;
	}

	public ResponseEntity<DataResponse> getHistoricTaskusingProcessInstanceIdAndName(String processInstanceId,
			String name) {
		return historyApi.listHistoricTaskInstances(null, processInstanceId, null, null, null, null, null, null, null,
				null, null, name, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
				null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

	}
	
	
	@GetMapping("/count-by-customerid-statusname/{customerId}/{statusName}")
	public ResponseEntity<Long> countByCustomerIdAndStatusName(@PathVariable String customerId,@PathVariable String statusName) {
		return new ResponseEntity<Long>(orderQueryService.countByCustomerIdAndStatusName(customerId,statusName), HttpStatus.OK);
	}
	
	@GetMapping("/findByDeliveryInfoId/{id}")
	public ResponseEntity<OrderDTO> findByDeliveryInfoId(@PathVariable Long id){
		return new ResponseEntity<OrderDTO>(orderQueryService.findByDeliveryInfoId(id), HttpStatus.OK);
	}
	
	@GetMapping("/count-by-storeid-customerid/{storeId}/{customerId}")
	public ResponseEntity<Long> countByStoreIdAndCustomerId(@PathVariable String storeId,@PathVariable String customerId) {
		return new ResponseEntity<Long>(orderQueryService.countByStoreIdAndCustomerId(storeId,customerId),HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	

}
