package com.diviso.graeshoppe.order.service;

import java.util.Optional;

import org.springframework.http.HttpStatus;

import com.diviso.graeshoppe.order.domain.DeliveryInfo;
import com.diviso.graeshoppe.order.service.dto.OrderDTO;

public interface OrderQueryService {

	
	public OrderDTO findByOrderId(String orderId);
	public Long countByCustomerIdAndStatusName(String customerId,String statusName);
	public OrderDTO findByDeliveryInfoId(Long id);
	public long countByStoreIdAndCustomerId(String storeId, String customerId);
	public DeliveryInfo findDeliveryInfoByOrderId(String orderId);
	
}
