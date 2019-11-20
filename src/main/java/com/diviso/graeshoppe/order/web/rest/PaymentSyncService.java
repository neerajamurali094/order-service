package com.diviso.graeshoppe.order.web.rest;

import java.util.Optional;

import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.diviso.graeshoppe.order.config.MessageBinderConfiguration;
import com.diviso.graeshoppe.order.service.OrderCommandService;
import com.diviso.graeshoppe.order.service.dto.OrderDTO;
import com.diviso.graeshoppe.payment.avro.Payment;
import com.esotericsoftware.minlog.Log;

@EnableBinding(MessageBinderConfiguration.class)
public class PaymentSyncService {
	private final Logger LOG = LoggerFactory.getLogger(PaymentSyncService.class);

	@Autowired
	private OrderCommandService orderService;

	@StreamListener(MessageBinderConfiguration.PAYMENT)
	public void listenToPayment(KStream<String, Payment> message) {
		message.foreach((key,value) -> {
			LOG.info("message consumed payment "+value);
			Optional<OrderDTO> orderDTO = orderService.findByOrderID(value.getTargetId());
			if(orderDTO.isPresent()) {
				orderDTO.get().setPaymentMode(value.getPaymentType().toUpperCase());
				orderDTO.get().setPaymentRef(value.getRef());
				orderDTO.get().setStatusId(4l);
				orderService.update(orderDTO.get());
				Log.info("Order updated with payment ref");
			}
			
		});
		
		
	}
	
}
