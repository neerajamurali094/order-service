package com.diviso.graeshoppe.order.repository;

import com.diviso.graeshoppe.order.domain.DeliveryInfo;
import com.diviso.graeshoppe.order.domain.Order;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import java.time.Instant;
import org.springframework.data.repository.query.Param;

/**
 * Spring Data repository for the Order entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	Optional<Order> findByOrderId(String orderId);

	List<Order> findByStoreId(String storeId);

	Optional<Order> findByOrderIdAndStatus_Name(String orderId, String status);

	public long countByDateBetweenAndStoreId(Instant dateBegin, Instant dateEnd, String storeId);
	
	public long countByStoreIdAndCustomerId(String storeId,String customerId);
	
	long countByCustomerId(String customerId);

	public long countByCustomerIdAndStatus_Name(String customerId,String statusName);
	
	@Query(value = "SELECT COUNT(c) FROM Order c  WHERE c.date BETWEEN :dateBegin AND :dateEnd AND c.storeId=:storeId AND  c.deliveryInfo.deliveryType LIKE CONCAT('%',:deliveryType,'%')")
	public Integer countBystoreIdAndDeliveryType(@Param("dateBegin") Instant dateBegin,
			@Param("dateEnd") Instant dateEnd, @Param("storeId") String storeId,
			@Param("deliveryType") String deliveryType);

	@Query(value = "SELECT c.paymentRef FROM Order c  WHERE c.date BETWEEN :dateBegin AND :dateEnd AND c.storeId=:storeId ")
	public List<String> findAllPaymentRef(@Param("dateBegin") Instant dateBegin, @Param("dateEnd") Instant dateEnd,
			@Param("storeId") String storeId);

	@Query(value = "SELECT c.paymentRef FROM Order c  WHERE c.date BETWEEN :dateBegin AND :dateEnd AND c.storeId=:storeId AND  c.deliveryInfo.deliveryType LIKE CONCAT('%',:deliveryType,'%')")
	public List<String> findAllPaymentRefByDeliveryType(@Param("dateBegin") Instant dateBegin,
			@Param("dateEnd") Instant dateEnd, @Param("storeId") String storeId,
			@Param("deliveryType") String deliveryType);
	
	@Query(value = "SELECT o.deliveryInfo from Order o WHERE o.orderId=:orderId")
	public DeliveryInfo findDeliveryInfoByOrderId(@Param("orderId") String orderId);
}
