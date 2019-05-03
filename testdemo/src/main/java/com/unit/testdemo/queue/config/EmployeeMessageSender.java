package com.unit.testdemo.queue.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmployeeMessageSender {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	@Value("message.exchange")
	private String exchange;

	@Value("emp.routingkey")
	private String routingkey;

	public void send(String message) {
		rabbitTemplate.convertAndSend(exchange, routingkey, message);
	}
}
