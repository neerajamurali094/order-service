package com.diviso.graeshoppe.order.service;

import com.diviso.graeshoppe.order.domain.Order;
import com.diviso.graeshoppe.order.service.dto.OrderDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Order.
 */
public interface OrderService {

    /**
     * Save a order.
     *
     * @param orderDTO the entity to save
     * @return the persisted entity
     */
    OrderDTO save(OrderDTO orderDTO);

    /**
     * Get all the orders.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<OrderDTO> findAll(Pageable pageable);


    /**
     * Get the "id" order.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<OrderDTO> findOne(Long id);

    /**
     * Delete the "id" order.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the order corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<OrderDTO> search(String query, Pageable pageable);
    
    List<Order> findByStoreId(String storeId);
    
    /*
     * 
     */
    long countAllOrdersByDateAndStoreId(Instant dateBegin, Instant dateEnd, String storeId);
    Integer countOrdersByStoreIdAndDeliveryType(Instant dateBegin, Instant dateEnd, String storeId,String deliveryType);
    List<String> findAllPaymentReferenceByDateAndStoreId(Instant dateBegin, Instant dateEnd, String storeId);
    
    List<String> findAllPaymentRefByDeliveryType(Instant dateBegin, Instant dateEnd, String storeId,String deliveryType);
    
    
    
    
    
}
