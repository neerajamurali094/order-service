package com.diviso.graeshoppe.order.service.impl;

import com.diviso.graeshoppe.order.service.ApprovalDetailsService;
import com.diviso.graeshoppe.order.client.bpmn.api.FormsApi;
import com.diviso.graeshoppe.order.client.bpmn.api.TasksApi;
import com.diviso.graeshoppe.order.client.bpmn.model.RestFormProperty;
import com.diviso.graeshoppe.order.client.bpmn.model.SubmitFormRequest;
import com.diviso.graeshoppe.order.domain.ApprovalDetails;
import com.diviso.graeshoppe.order.models.AcceptOrderRequest;
import com.diviso.graeshoppe.order.repository.ApprovalDetailsRepository;
import com.diviso.graeshoppe.order.repository.search.ApprovalDetailsSearchRepository;
import com.diviso.graeshoppe.order.resource.assembler.CommandResource;
import com.diviso.graeshoppe.order.resource.assembler.ResourceAssembler;
import com.diviso.graeshoppe.order.service.dto.ApprovalDetailsDTO;
import com.diviso.graeshoppe.order.service.mapper.ApprovalDetailsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing ApprovalDetails.
 */
@Service
@Transactional
public class ApprovalDetailsServiceImpl implements ApprovalDetailsService {

    private final Logger log = LoggerFactory.getLogger(ApprovalDetailsServiceImpl.class);

    @Autowired
    private FormsApi formsApi;
    
    @Autowired
    private TasksApi tasksApi;
    
    @Autowired
    private ResourceAssembler resourceAssembler;
    
    private final ApprovalDetailsRepository approvalDetailsRepository;

    private final ApprovalDetailsMapper approvalDetailsMapper;

    private final ApprovalDetailsSearchRepository approvalDetailsSearchRepository;

    public ApprovalDetailsServiceImpl(ApprovalDetailsRepository approvalDetailsRepository, ApprovalDetailsMapper approvalDetailsMapper, ApprovalDetailsSearchRepository approvalDetailsSearchRepository) {
        this.approvalDetailsRepository = approvalDetailsRepository;
        this.approvalDetailsMapper = approvalDetailsMapper;
        this.approvalDetailsSearchRepository = approvalDetailsSearchRepository;
    }

    /**
     * Save a approvalDetails.
     *
     * @param approvalDetailsDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CommandResource save(ApprovalDetailsDTO approvalDetailsDTO,String taskId) {
        log.debug("Request to save ApprovalDetails : {}", approvalDetailsDTO);
        ApprovalDetails approvalDetails = approvalDetailsMapper.toEntity(approvalDetailsDTO);
        approvalDetails = approvalDetailsRepository.save(approvalDetails);
        ApprovalDetailsDTO result = approvalDetailsMapper.toDto(approvalDetails);
        approvalDetailsSearchRepository.save(approvalDetails);
        CommandResource result1=acceptOrder(approvalDetailsDTO,taskId);
        result1.setSelfId(result.getId());
        return result1;
    }
    
    
    
	public CommandResource acceptOrder(ApprovalDetailsDTO acceptOrderRequest,String taskId) {

		String processInstanceId = tasksApi.getTask(taskId).getBody().getProcessInstanceId();
		log.info("ProcessInstanceId is+ " + processInstanceId);
		SubmitFormRequest formRequest = new SubmitFormRequest();
		List<RestFormProperty> properties = new ArrayList<RestFormProperty>();
		RestFormProperty decision = new RestFormProperty();
		decision.setId("decision");
		decision.setName("decision");
		decision.setType("String");
		decision.setValue(acceptOrderRequest.getDecision());
		properties.add(decision);

		String stringDate = Date.from(acceptOrderRequest.getExpectedDelivery()).toString();
		String date=stringDate.substring(4, 10);
		String time=stringDate.substring(11, 16);
		RestFormProperty deliveryTime = new RestFormProperty();
		deliveryTime.setId("deliveryTime");
		deliveryTime.setName("deliveryTime");
		deliveryTime.setType("String");
		deliveryTime.setValue(date+" "+time);
		properties.add(deliveryTime);

		formRequest.setProperties(properties);
		formRequest.setAction("completed");
		formRequest.setTaskId(taskId);
		formsApi.submitForm(formRequest);
		// orderRepository.findByOrderId(acceptOrderRequest.getOrderId());
		//sendNotification(acceptOrderRequest.);
		CommandResource commandResource = resourceAssembler.toResource(processInstanceId);
		return commandResource;
	}
    /**
     * Save a approvalDetails.
     *
     * @param approvalDetailsDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ApprovalDetailsDTO update(ApprovalDetailsDTO approvalDetailsDTO) {
        log.debug("Request to save ApprovalDetails : {}", approvalDetailsDTO);
        ApprovalDetails approvalDetails = approvalDetailsMapper.toEntity(approvalDetailsDTO);
        approvalDetails = approvalDetailsRepository.save(approvalDetails);
        ApprovalDetailsDTO result = approvalDetailsMapper.toDto(approvalDetails);
        approvalDetailsSearchRepository.save(approvalDetails);
        return result;
    }

    /**
     * Get all the approvalDetails.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ApprovalDetailsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ApprovalDetails");
        return approvalDetailsRepository.findAll(pageable)
            .map(approvalDetailsMapper::toDto);
    }


    /**
     * Get one approvalDetails by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ApprovalDetailsDTO> findOne(Long id) {
        log.debug("Request to get ApprovalDetails : {}", id);
        return approvalDetailsRepository.findById(id)
            .map(approvalDetailsMapper::toDto);
    }

    /**
     * Delete the approvalDetails by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ApprovalDetails : {}", id);
        approvalDetailsRepository.deleteById(id);
        approvalDetailsSearchRepository.deleteById(id);
    }

    /**
     * Search for the approvalDetails corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ApprovalDetailsDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of ApprovalDetails for query {}", query);
        return approvalDetailsSearchRepository.search(queryStringQuery(query), pageable)
            .map(approvalDetailsMapper::toDto);
    }
}
