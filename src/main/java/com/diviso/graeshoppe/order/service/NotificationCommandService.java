package com.diviso.graeshoppe.order.service;

import com.diviso.graeshoppe.order.service.dto.NotificationDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Notification.
 */
public interface NotificationCommandService {

	
	  Boolean publishNotificationToMessageBroker(NotificationDTO notification);
    /**
     * Save a notification.
     *
     * @param notificationDTO the entity to save
     * @return the persisted entity
     */
	
    NotificationDTO save(NotificationDTO notificationDTO);

    /**
     * Get all the notifications.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<NotificationDTO> findAll(Pageable pageable);


    /**
     * Get the "id" notification.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<NotificationDTO> findOne(Long id);

    /**
     * Delete the "id" notification.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the notification corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<NotificationDTO> search(String query, Pageable pageable);
}
