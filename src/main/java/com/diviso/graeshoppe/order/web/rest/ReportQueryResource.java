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

import com.diviso.graeshoppe.order.client.product.model.ComboLineItem;
import com.diviso.graeshoppe.order.client.product.model.Product;
import com.diviso.graeshoppe.order.client.store.domain.Store;
import com.diviso.graeshoppe.order.domain.Address;
import com.diviso.graeshoppe.order.domain.AuxilaryOrderLine;
import com.diviso.graeshoppe.order.domain.Offer;
import com.diviso.graeshoppe.order.domain.Order;
import com.diviso.graeshoppe.order.domain.OrderLine;
import com.diviso.graeshoppe.order.repository.OfferRepository;
import com.diviso.graeshoppe.order.repository.OrderLineRepository;
import com.diviso.graeshoppe.order.repository.OrderRepository;
import com.diviso.graeshoppe.order.service.OrderService;
import com.diviso.graeshoppe.order.service.ReportQueryService;
import com.diviso.graeshoppe.order.service.dto.AuxItem;
import com.diviso.graeshoppe.order.service.dto.ComboItem;
import com.diviso.graeshoppe.order.service.dto.OrderMaster;
import com.diviso.graeshoppe.order.service.dto.ReportOrderLine;
import com.diviso.graeshoppe.order.service.dto.Reportsummary;

/**
 * TODO Provide a detailed description here
 * 
 * @author MayaSanjeev mayabytatech, maya.k.k@lxisoft.com
 */
@RestController
@RequestMapping("/api/order")
public class ReportQueryResource {

	private final Logger log = LoggerFactory.getLogger(ReportQueryResource.class);

	@Autowired
	ReportQueryService reportService;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	OrderLineRepository orderLineRepository;

	@Autowired
	OfferRepository offerRepository;
	
	@Autowired
	OrderService orderService;
	
	
	Long count;

	

	@GetMapping("/findOrder/{orderId}/{status}")
	public OrderMaster getOrderByOrderIdAndStatusName(@PathVariable String orderId,@PathVariable String status){
		OrderMaster orderMaster = new OrderMaster(); 
		 Order order=orderRepository.findByOrderIdAndStatus_Name(orderId,status).get();
		 if(order==null) {
			 return null;
		 }
		 List<OrderLine> orderLines= orderLineRepository.findByOrder_Id(order.getId());
		 List<Offer> offers= offerRepository.findByOrder_Id(order.getId());
		 if(order.getDeliveryInfo()!=null) {
			 orderMaster.setDeliveryCharge(order.getDeliveryInfo().getDeliveryCharge());
			 orderMaster.setNotes(order.getDeliveryInfo().getDeliveryNotes());
			 orderMaster.setMethodOfOrder(order.getDeliveryInfo().getDeliveryType());
			 orderMaster.setCustomerId(order.getCustomerId());
			 Store store = reportService.findStoreByStoreId(order.getStoreId());

				if (store != null) {

					log.info(".................store............" + store);

					orderMaster.setStorePhone(store.getContactNo());

					orderMaster.setServiceCharge(store.getStoreSettings().getServiceCharge());
				}
			 if(order.getDeliveryInfo().getDeliveryAddress()!=null) {
				 Address address= order.getDeliveryInfo().getDeliveryAddress();
				 orderMaster.setAddressType(address.getAddressType());
				 orderMaster.setAlternatePhone(address.getAlternatePhone());
				 orderMaster.setPhone(address.getPhone());
				 orderMaster.setCity(address.getCity());
				 orderMaster.setHouseNoOrBuildingName(address.getHouseNoOrBuildingName());
				 orderMaster.setRoadNameAreaOrStreet(address.getRoadNameAreaOrStreet());
				 orderMaster.setLandmark(address.getLandmark());
				 orderMaster.setName(address.getName());
				 orderMaster.setPincode(address.getPincode());
			 }
			 if(order.getApprovalDetails()!=null) {
					Instant instantDate = order.getApprovalDetails().getExpectedDelivery();

					String stringDate = Date.from(instantDate).toString();

					// date to string conversion for report format

					orderMaster.setDueDate(stringDate.substring(4, 10));

					orderMaster.setDueTime(stringDate.substring(11, 16));
			 }

		 }
		 List<ReportOrderLine> orderList = new ArrayList<ReportOrderLine>();
			orderLines.forEach(orderline -> {

				ReportOrderLine reportOrderLine = new ReportOrderLine();

				Product product = reportService.findProductByProductId(orderline.getProductId());

				List<ComboLineItem> comboItemList = reportService.findCombosByProductId(product.getId());
				if (comboItemList != null) {
					List<ComboItem> comItemList = new ArrayList<ComboItem>();
					comboItemList.forEach(com -> {
						ComboItem comboItem = new ComboItem();
						comboItem.setcomboItem(com.getComboItem().getName());
						comboItem.setQuantity(com.getQuantity());
						comItemList.add(comboItem);
					});
				}

				List<AuxilaryOrderLine> auxilaryList = reportService.findAuxItemsByOrderLineId(orderline.getId());
				if (auxilaryList != null) {
					List<AuxItem> aux = new ArrayList<AuxItem>();
					auxilaryList.forEach(a -> {
						AuxItem auxItem = new AuxItem();
						auxItem.setAuxItem(reportService.findProductByProductId(a.getProductId()).getName());
						auxItem.setQuantity(a.getQuantity());
						auxItem.setTotal(a.getTotal());
						aux.add(auxItem);
					});
				}
				reportOrderLine.setItem(product.getName());

				reportOrderLine.setQuantity(orderline.getQuantity());

				reportOrderLine.setTotal(orderline.getTotal());

				orderList.add(reportOrderLine);
			});

			orderMaster.setOrderLine(orderList);
			String orderDate = Date.from(order.getDate()).toString();
	
		 return orderMaster;
	}
	
	
	@GetMapping("/main-report/{orderId}/{statusName}")
	public ResponseEntity<OrderMaster> getOrderMaster(@PathVariable String orderId, @PathVariable String statusName,
			Pageable pageable) {

		
		OrderMaster orderMaster = new OrderMaster();

//		Order order = reportService.findOrderByOrderIdandStatusName(orderId, statusName);
	
		Order order = reportService.findOrderByOrderId(orderId);
		
		
		log.info("..................order......................" + order);
		
		if (order != null) {
			orderMaster.setStoreName(order.getStoreId());

			orderMaster.setNotes(order.getDeliveryInfo().getDeliveryNotes());

			orderMaster.setTotalDue(order.getGrandTotal());

			orderMaster.setCustomerId(order.getCustomerId());

			log.info(".........order.getDeliveryInfo()..........." + order.getDeliveryInfo());

			orderMaster.setMethodOfOrder(order.getDeliveryInfo().getDeliveryType());

			orderMaster.setOrderNumber(orderId);

			if (order.getApprovalDetails() != null) {

				log.info("...........order.getApprovalDetails()..........." + order.getApprovalDetails());

				Instant instantDate = order.getApprovalDetails().getExpectedDelivery();

				String stringDate = Date.from(instantDate).toString();

				// date to string conversion for report format

				orderMaster.setDueDate(stringDate.substring(4, 10));

				orderMaster.setDueTime(stringDate.substring(11, 16));

			}
			orderMaster.setDeliveryCharge(order.getDeliveryInfo().getDeliveryCharge());

			// date to string conversion for report format

			String orderDate = Date.from(order.getDate()).toString();

			orderMaster.setOrderPlaceAt(orderDate.substring(4, 16));

			if (order.getApprovalDetails() != null) {
				String orderAcceptDate = Date.from(order.getApprovalDetails().getAcceptedAt()).toString();

				orderMaster.setOrderAcceptedAt(orderAcceptDate.substring(4, 16));
			}
			if (order.getStatus() != null) {

				orderMaster.setOrderStatus(order.getStatus().getName());

			}

			List<OrderLine> orderLines = reportService.findOrderLinesByOrderId(orderId);

			log.info(".................orderLines............" + orderLines);
			List<ReportOrderLine> orderList = new ArrayList<ReportOrderLine>();
			orderLines.forEach(orderline -> {

				ReportOrderLine reportOrderLine = new ReportOrderLine();

				Product product = reportService.findProductByProductId(orderline.getProductId());

				List<ComboLineItem> comboItemList = reportService.findCombosByProductId(product.getId());
				if (comboItemList != null) {
					List<ComboItem> comItemList = new ArrayList<ComboItem>();
					comboItemList.forEach(com -> {
						ComboItem comboItem = new ComboItem();
						comboItem.setcomboItem(com.getComboItem().getName());
						comboItem.setQuantity(com.getQuantity());
						comItemList.add(comboItem);
					});
				}

				List<AuxilaryOrderLine> auxilaryList = reportService.findAuxItemsByOrderLineId(orderline.getId());
				if (auxilaryList != null) {
					List<AuxItem> aux = new ArrayList<AuxItem>();
					auxilaryList.forEach(a -> {
						AuxItem auxItem = new AuxItem();
						auxItem.setAuxItem(reportService.findProductByProductId(a.getProductId()).getName());
						auxItem.setQuantity(a.getQuantity());
						auxItem.setTotal(a.getTotal());
						aux.add(auxItem);
					});
				}
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
		orderMaster.setCustomersOrder(
				reportService.getOrderCountByCustomerIdAndStatusFilter(statusName, order.getCustomerId(), pageable));

		orderMaster.setOrderFromCustomer(
				reportService.getOrderCountByCustomerIdAndStoreId(order.getCustomerId(), order.getStoreId(), pageable));

		return ResponseEntity.ok().body(orderMaster);
	}

	@GetMapping("/count-by-customerid/{customerId}")
	public Long findOrderCountByCustomerId(@PathVariable String customerId, Pageable pageable) {

		return reportService.findOrderCountByCustomerId(customerId, pageable);

	}

	@GetMapping("/count-by-customerid-status/{statusName}/{customerId}")
	public Long findOrderCountByCustomerIdAndStatusName(@PathVariable String statusName,
			@PathVariable String customerId, Pageable pageable) {

		return reportService.findOrderCountByCustomerIdAndStatusFilter(statusName, customerId, pageable);

	}

	@GetMapping("/count-by-customerid-statusid/{customerId}/{storeId}")
	public Long findOrderCountByCustomerIdAndStoreId(@PathVariable String customerId, @PathVariable String storeId,
			Pageable pageable) {

		return reportService.findOrderCountByCustomerIdAndStoreId(customerId, storeId, pageable);
	}

	@GetMapping("/count/{statusName}/{customerId}")
	public Long getOrderCountByCustomerIdAndStatusName(@PathVariable String statusName, @PathVariable String customerId,
			Pageable pageable) {

		return reportService.getOrderCountByCustomerIdAndStatusFilter(statusName, customerId, pageable);

	}

	@GetMapping("/order-line/{orderId}")
	public List<OrderLine> findOrderLinesByOrderId(@PathVariable String orderId) {

		return reportService.findOrderLinesByOrderId(orderId);
	}

	@GetMapping("/orderid-status/{orderId}/{status}")
	public Order findOrderByOrderIdandStatusName(@PathVariable String orderId, @PathVariable String status) {

		return reportService.findOrderByOrderIdandStatusName(orderId, status);

	}

	@GetMapping("/{orderId}")
	public Order findOrderByOrderId(@PathVariable String orderId) {

		return reportService.findOrderByOrderId(orderId);
	}

	@GetMapping("/reportsummary/{storeId}")
	public Reportsummary getOrderByStoreIdAndCurrentDate(@PathVariable String storeId) {

		List<Order> order = orderService.findByStoreId(storeId);

		List<Order> storeBased = new ArrayList<Order>();

		Double total = 0.0;
		log.info(".........................................................." + order.size());
		for (int i = 0; i < order.size(); i++) {
			// log.info("..............................................." +
			// Date.from(order.get(i).getDate()).toString());
			if (Date.from(order.get(i).getDate()).toString().substring(4, 10).equals("Aug 20")) {
				// log.info(".................." + order.get(i));
				order.add(order.get(i));
				total += order.get(i).getGrandTotal();
				// log.info(".................." + total);
			}
		}

		Reportsummary report = new Reportsummary();
		report.setCount((long) storeBased.size());
		report.setTotal(total);

		return report;
	}
	
	
	/*
	 * 
	 */
	@GetMapping("/countAllOrder/{dateBegin}/{dateEnd}/{storeId}")
	public long countAllOrdersByDateAndStoreId(@PathVariable Instant dateBegin, @PathVariable Instant dateEnd, @PathVariable String storeId) {
		return orderService.countAllOrdersByDateAndStoreId(dateBegin, dateEnd, storeId);
	}
	/*
	 * 
	 */
	@GetMapping("/countOrdersByDeliveryType/{dateBegin}/{dateEnd}/{storeId}/{deliveryType}")
	public Integer countOrdersByStoreIdAndDeliveryType(@PathVariable Instant dateBegin, @PathVariable Instant dateEnd, @PathVariable String storeId,
			String deliveryType) {
		return orderService.countOrdersByStoreIdAndDeliveryType(dateBegin, dateEnd, storeId, deliveryType);
	}
	/*
	 * 
	 */
	@GetMapping("/findPaymentReference/{dateBegin}/{dateEnd}/{storeId}")
	public List<String> findAllPaymentReferenceByDateAndStoreId(@PathVariable Instant dateBegin, @PathVariable Instant dateEnd,@PathVariable String storeId) {
		return orderService.findAllPaymentReferenceByDateAndStoreId(dateBegin, dateEnd, storeId);
	}
	
	
	
	
}
