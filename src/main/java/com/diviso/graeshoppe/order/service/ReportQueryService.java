 /*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.diviso.graeshoppe.order.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.diviso.graeshoppe.order.client.product.model.ComboLineItem;
import com.diviso.graeshoppe.order.client.product.model.Product;
import com.diviso.graeshoppe.order.client.store.domain.Store;
import com.diviso.graeshoppe.order.domain.Address;
import com.diviso.graeshoppe.order.domain.AuxilaryOrderLine;
import com.diviso.graeshoppe.order.domain.Order;
import com.diviso.graeshoppe.order.domain.OrderLine;

/**
 * TODO Provide a detailed description here 
 * @author MayaSanjeev
 * mayabytatech, maya.k.k@lxisoft.com
 */
public interface ReportQueryService {

	/**
	 * @param orderId
	 * @return
	 */
	Order findOrderByOrderIdandStatusName(String orderId, String status);

	/**
	 * @param orderId
	 * @return
	 */
	List<OrderLine> findOrderLinesByOrderId(String orderId);

	/**
	 * @param long1
	 * @return
	 */
	Address findOrderAddressById(Long long1);

	/**
	 * @param storeId
	 * @return
	 */
	Store findStoreByStoreId(String storeId);

	/**
	 * @param pageable
	 * @return
	 */
	Long findOrderCountByCustomerId(String customerId,Pageable pageable);

	/**
	 * @param pageable
	 * @return
	 */
	Long findOrderCountByCustomerIdAndStoreId(String customerId,String storeId,Pageable pageable);

	/**
	 * @param productId
	 * @return
	 */
	Product findProductByProductId(Long productId);

	/**
	 * @param id
	 * @return
	 */
	List<ComboLineItem> findCombosByProductId(Long id);

	/**
	 * @param customerId
	 * @param pageable
	 * @return
	 */
	Long findOrderCountByCustomerIdAndStatusFilter(String statusName,String customerId, Pageable pageable);

	/**
	 * @param id
	 * @return
	 */
	List<AuxilaryOrderLine> findAuxItemsByOrderLineId(Long id);

	/**
	 * @param statusName
	 * @param customerId
	 * @param pageable
	 * @return
	 */
	Long getOrderCountByCustomerIdAndStatusFilter(String statusName, String customerId, Pageable pageable);

	/**
	 * @param customerId
	 * @param storeId
	 * @param pageable
	 * @return
	 */
	Long getOrderCountByCustomerIdAndStoreId(String customerId, String storeId, Pageable pageable);

}
