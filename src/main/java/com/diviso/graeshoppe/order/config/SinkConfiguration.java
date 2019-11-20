package com.diviso.graeshoppe.order.config;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;

import com.diviso.graeshoppe.payment.avro.Payment;

public interface SinkConfiguration {
	
	String PAYMENT="payment";

	//@Input(PAYMENT)
	KStream<String, Payment> payment();
}
