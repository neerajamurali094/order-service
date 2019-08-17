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
package com.diviso.graeshoppe.order.web.rest;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diviso.graeshoppe.order.client.product.model.Product;
import com.diviso.graeshoppe.order.client.store.domain.Store;
import com.diviso.graeshoppe.order.domain.Address;
import com.diviso.graeshoppe.order.domain.Order;
import com.diviso.graeshoppe.order.domain.OrderLine;
import com.diviso.graeshoppe.order.service.ReportQueryService;

import com.diviso.graeshoppe.order.service.dto.AddressDTO;

import com.diviso.graeshoppe.order.service.dto.OrderMaster;
import com.diviso.graeshoppe.order.service.dto.ReportOrderLine;

import io.github.jhipster.web.util.ResponseUtil;
import io.searchbox.core.search.aggregation.TermsAggregation.Entry;

/**
 * TODO Provide a detailed description here
 * 
 * @author MayaSanjeev mayabytatech, maya.k.k@lxisoft.com
 */
@RestController
@RequestMapping("/api")
public class ReportQueryResource {

	private final Logger log = LoggerFactory.getLogger(ReportQueryResource.class);

	@Autowired
	ReportQueryService reportService;

	Long count;

	@GetMapping("/main-report/{orderId}")
	public ResponseEntity<OrderMaster> getOrderMaster(@PathVariable String orderId, Pageable pageable) {

		OrderMaster orderMaster = new OrderMaster();

		Order order = reportService.findOrderByOrderId(orderId);
		log.info("..................order......................" + order);
		if (order != null) {
			orderMaster.setStoreName(order.getStoreId());

			orderMaster.setNotes(order.getDeliveryInfo().getDeliveryNotes());

			orderMaster.setTotalDue(order.getGrandTotal());

			orderMaster.setCustomerId(order.getCustomerId());

			log.info(".........order.getDeliveryInfo()..........." + order.getDeliveryInfo());

			orderMaster.setMethodOfOrder(order.getDeliveryInfo().getDeliveryType());

			log.info("...........order.getApprovalDetails()..........." + order.getApprovalDetails());

			Instant insatantDate = order.getApprovalDetails().getExpectedDelivery();

			String stringDate = Date.from(insatantDate).toString();

			// date to string conversion for report format

			orderMaster.setOrderNumber(orderId);
			orderMaster.setDueDate(stringDate.substring(4, 10));

			orderMaster.setDueTime(stringDate.substring(11, 16));

			orderMaster.setDeliveryCharge(order.getDeliveryInfo().getDeliveryCharge());

			// date to string conversion for report format

		//	String orderDate = Date.from(order.getDate()).toString();

			//orderMaster.setOrderPlaceAt(orderDate.substring(4, 16));

			String orderAcceptDate = Date.from(order.getApprovalDetails().getAcceptedAt()).toString();

			orderMaster.setOrderAcceptedAt(orderAcceptDate.substring(4, 16));

			if (order.getStatus() != null) {

				orderMaster.setOrderStatus(order.getStatus().getName());

			}

			List<OrderLine> orderLines = reportService.findOrderLinesByOrderId(orderId);

			log.info(".................orderLines............" + orderLines);
			List<ReportOrderLine> orderList = new ArrayList<ReportOrderLine>();
			orderLines.forEach(orderline -> {

				ReportOrderLine reportOrderLine = new ReportOrderLine();

				Product product = reportService.findProductByProductId(orderline.getProductId());

				reportOrderLine.setItem(product.getName());

				reportOrderLine.setQuantity(orderline.getQuantity());

				reportOrderLine.setTotal(orderline.getTotal());

				orderList.add(reportOrderLine);
			});

			orderMaster.setOrderLine(orderList);

			Address orderAddress = reportService.findOrderAddressById(order.getDeliveryInfo().getId());

			log.info(".................orderAddress............" + orderAddress);

			if (orderAddress != null) {

				orderMaster.setAddressType(orderAddress.getAddressType());

				orderMaster.setAlternatePhone(orderAddress.getAlternatePhone());

				orderMaster.setCity(orderAddress.getCity());

				orderMaster.setHouseNoOrBuildingName(orderAddress.getAddressType());

				orderMaster.setLandmark(orderAddress.getAddressType());

				orderMaster.setRoadNameAreaOrStreet(orderAddress.getRoadNameAreaOrStreet());

				orderMaster.setName(orderAddress.getName());
			}

			Store store = reportService.findStoreByStoreId(order.getStoreId());

			if (store != null) {

				log.info(".................store............" + store);

				orderMaster.setStorePhone(store.getContactNo());

				orderMaster.setServiceCharge(store.getStoreSettings().getServiceCharge());
			}
		}
		orderMaster.setCustomersOrder(reportService.findOrderCountByCustomerId(order.getCustomerId(), pageable));

		List<Entry> orderFromCustomerAndStore = reportService.findOrderCountByCustomerIdAndStoreId(order.getStoreId(),
				pageable);

		orderFromCustomerAndStore.forEach(ordersBystore -> {
			orderMaster.setOrderFromCustomer(ordersBystore.getCount());
		});

		return ResponseEntity.ok().body(orderMaster);
	}

	// ..........test methods........................
	/*	@GetMapping("/order-from-customer/{customerId}")
	public Long findOrderCountByCustomerId(@PathVariable String customerId, Pageable pageable) {

	return reportService.findOrderCountByCustomerId(customerId,pageable);

	entry.forEach(e -> {

			if (e.getKey().equals(customerId)) {
				count = e.getCount();
			}

		});
		//return count;
	}

	@GetMapping("/order-from-customer-storeid/{storeId}")
	public List<Entry> findOrderCountByCustomerIdAndStoreId(@PathVariable String storeId, Pageable pageable) {
		return reportService.findOrderCountByCustomerIdAndStoreId(storeId, pageable);
	}*/
}
