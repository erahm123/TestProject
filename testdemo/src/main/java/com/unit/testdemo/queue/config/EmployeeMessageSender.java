package com.unit.testdemo.queue.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.unit.testdemo.dto.EmployeeRequestDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmployeeMessageSender {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	@Value("message.exchange")
	private String exchange;

	@Value("emp.routingkey")
	private String routingkey;

	public void send(EmployeeRequestDTO message) {
		
		log.info("Send Message start:" + message.getId());
		rabbitTemplate.convertAndSend(exchange, routingkey, message);
		log.info("Send Message end:" + message.getId());
	}
}
