package com.diviso.graeshoppe.order.config;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface MessageBinderConfiguration {

	String ORDER="order";
	
	String NOTIFICATION="notificaton";
	
	@Output(ORDER)
	MessageChannel orderOut();
	
	@Output(NOTIFICATION)
	MessageChannel notificationOut();
}
