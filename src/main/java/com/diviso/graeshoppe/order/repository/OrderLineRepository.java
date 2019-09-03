package com.diviso.graeshoppe.order.repository;

import com.diviso.graeshoppe.order.domain.OrderLine;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the OrderLine entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {
	
	Set<OrderLine> findByOrder_Id(Long id);

}
