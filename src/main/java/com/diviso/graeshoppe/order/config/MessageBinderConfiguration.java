package com.diviso.graeshoppe.order.config;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

import com.diviso.graeshoppe.payment.avro.Payment;

public interface MessageBinderConfiguration {

	String ORDER="order";
	
	String NOTIFICATION="notification";
	
	
	@Output(ORDER)
	MessageChannel orderOut();
	
	@Output(NOTIFICATION)
	MessageChannel notificationOut();
	
	
}
