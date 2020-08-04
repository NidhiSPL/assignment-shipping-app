package com.assessment.nidhi.assessmentshippingapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assessment.nidhi.assessmentshippingapp.model.Container;
import com.assessment.nidhi.assessmentshippingapp.utils.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MQMessageProcessingServiceImpl implements MQMessageProcessingService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private JsonUtils jsonUtils;

	@Autowired
	private JMSOutboundMessagingService jmsOutboundMessagingService;

	@Autowired
	private ContainerService containerService;

	@Override
	public void processMessage(String message) {
		if (!jsonUtils.isValidJson(message)) {
			logger.warn("processMessage() eror in Json String");
			jmsOutboundMessagingService.sendMessagesToStatusInboundQueueDLQ(message);
			return;
		}
		parseMessage(message);
	}

	public void parseMessage(String message) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Container containerStatus = objectMapper.readValue(message, Container.class);
			Integer containerId = containerStatus.getContainerId();
			Long timestamp = containerStatus.getStatusTimestamp();
			if (containerId != null && timestamp != null) {
				Container containerFromDB = containerService.getContainer(containerId);
				if (timestamp > containerFromDB.getStatusTimestamp()) {
					containerService.updateContainer(containerStatus);
					jmsOutboundMessagingService.sendMessagesToStatusOutboundQueue(containerStatus);
					return;
				}
			}
		} catch (JsonMappingException e) {
			logger.error("parseMessage() JsonMappingException : {}", e);
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			logger.error("parseMessage() JsonProcessingException : {}", e);
		}

		logger.warn("Stale message redirected to STATUS_INBOUND_QUEUE_DLQ");
		jmsOutboundMessagingService.sendMessagesToStatusInboundQueueDLQ(message);
	}

}
