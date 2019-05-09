package com.unit.testdemo.queue.config;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unit.testdemo.dto.EmployeeRequestDTO;

import java.io.File;
import java.io.FileWriter;

import lombok.extern.slf4j.Slf4j;

import freemarker.template.Configuration;

@Slf4j
@Service
@RabbitListener(queues = {"message.queue"})
public class EmployeeMessageReceiver {

	@RabbitHandler
	public void receivedMessage(@Payload EmployeeRequestDTO employeeMessage) throws JsonProcessingException
	{
		
		Map<String, Object> model = new HashMap<String, Object>();
        
		model.put("employee", employeeMessage);
        String text = geFreeMarkerTemplateContent(model);
        log.info("Template content : " + text);
		
		ObjectMapper mapper = new ObjectMapper();
        String employeeMessageJson = mapper.writeValueAsString(employeeMessage);
        
        log.info("Message Received :"+ employeeMessageJson);
        

	} 
	
	@Autowired
	Configuration freeMarkerConfigurartion;
	
	public String geFreeMarkerTemplateContent(Map<String, Object> model){
		StringBuffer content = new StringBuffer();
		try {
			
			content.append(FreeMarkerTemplateUtils.processTemplateIntoString(freeMarkerConfigurartion.getTemplate("template.ftl"), model));
			return content.toString();
			
		} catch (Exception e) {
			log.info("Exception occured while processing template:" + e.getMessage());
		}
		return "";
    }
	

}
