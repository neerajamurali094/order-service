package com.diviso.graeshoppe.order.service;

import com.diviso.graeshoppe.order.resource.assembler.CommandResource;
import com.diviso.graeshoppe.order.service.dto.ApprovalDetailsDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing ApprovalDetails.
 */
public interface ApprovalDetailsService {

    /**
     * Save a approvalDetails.
     *
     * @param approvalDetailsDTO the entity to save
     * @param taskId 
     * @return the persisted entity
     */
    CommandResource save(ApprovalDetailsDTO approvalDetailsDTO, String taskId);
    
    ApprovalDetailsDTO update(ApprovalDetailsDTO approvalDetailsDTO);

    /**
     * Get all the approvalDetails.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ApprovalDetailsDTO> findAll(Pageable pageable);


    /**
     * Get the "id" approvalDetails.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ApprovalDetailsDTO> findOne(Long id);

    /**
     * Delete the "id" approvalDetails.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the approvalDetails corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ApprovalDetailsDTO> search(String query, Pageable pageable);
}
