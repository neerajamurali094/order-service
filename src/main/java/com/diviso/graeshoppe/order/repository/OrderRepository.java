package com.diviso.graeshoppe.order.repository;

import com.diviso.graeshoppe.order.domain.Order;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Order entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	Order findByOrderId(String orderId);
	
	List<Order> findByStoreId(String storeId);
	
	Optional<Order> findByOrderIdAndStatus_Name(String orderId,String status);
	

}
