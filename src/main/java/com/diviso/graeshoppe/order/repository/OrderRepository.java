package com.diviso.graeshoppe.order.repository;

import com.diviso.graeshoppe.order.domain.Order;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import java.time.Instant;
import java.util.List;
import org.springframework.data.repository.query.Param;

/**
 * Spring Data repository for the Order entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	Order findByOrderId(String orderId);

	List<Order> findByStoreId(String storeId);

	Optional<Order> findByOrderIdAndStatus_Name(String orderId, String status);

	long countByDateBetweenAndStoreId(Instant dateBegin, Instant dateEnd, String storeId);

	@Query(value = "SELECT COUNT(c) FROM Order c  WHERE c.date BETWEEN :dateBegin AND :dateEnd AND c.storeId=:storeId AND  c.deliveryInfo.deliveryType LIKE CONCAT('%',:deliveryType,'%')")
	public Integer countBystoreIdAndDeliveryType(@Param("dateBegin") Instant dateBegin,
			@Param("dateEnd") Instant dateEnd, @Param("storeId") String storeId,
			@Param("deliveryType") String deliveryType);

	@Query(value = "SELECT c.paymentRef FROM Order c  WHERE c.date BETWEEN :dateBegin AND :dateEnd AND c.storeId=:storeId ")
	public List<String> findAllPaymentRef(@Param("dateBegin") Instant dateBegin, @Param("dateEnd") Instant dateEnd,
			@Param("storeId") String storeId);

}
