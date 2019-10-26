package com.diviso.graeshoppe.order.repository;

import com.diviso.graeshoppe.order.domain.OrderLine;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the OrderLine entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {
	
	Set<OrderLine> findByOrder_OrderId(String orderId);
	
	Optional<OrderLine> findByProductIdAndOrder_Id(Long productId,Long orderId);

}
