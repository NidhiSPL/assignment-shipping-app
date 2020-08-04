package com.assessment.nidhi.assessmentshippingapp.components;

import javax.jms.JMSException;
import javax.jms.Message;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.assessment.nidhi.assessmentshippingapp.service.MQMessageProcessingService;

@Component
public class MQMessageReceiver {

	@Autowired
	private MQMessageProcessingService mqMessageProcessingService;

	@JmsListener(destination = "STATUS.INBOUND.QUEUE")
	public void onMessage(final Message message) {
		if (message instanceof ActiveMQTextMessage) {
			try {
				String textMessage = ((ActiveMQTextMessage) message).getText();
				mqMessageProcessingService.processMessage(textMessage);
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}
}
