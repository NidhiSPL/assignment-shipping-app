package com.assessment.nidhi.assessmentshippingapp.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.assessment.nidhi.assessmentshippingapp.model.Container;
import com.assessment.nidhi.assessmentshippingapp.vos.ContainerVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@Service
public class JMSOutboundMessagingServiceImpl implements JMSOutboundMessagingService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private static final String STATUS_INBOUND_QUEUE_DLQ = "STATUS.INBOUND.QUEUE.DLQ";
	private static final String STATUS_OUTBOUND_QUEUE = "STATUS.OUTBOUND.QUEUE";

	@Autowired
	private JmsTemplate jmsTemplate;

	@Override
	public void sendMessagesToStatusInboundQueueDLQ(String message) {
		jmsTemplate.convertAndSend(STATUS_INBOUND_QUEUE_DLQ, message);
	}

	@Override
	public void sendMessagesToStatusOutboundQueue(Container container) {
		// send message to STATUS.OUTBOUND.QUEUE
		XmlMapper xmlMapper = new XmlMapper();
		try {

			ContainerVo containerVO = new ContainerVo();
			containerVO.setContainerId(container.getContainerId());
			containerVO.setContainerOwnerId(container.getContainerOwnerId());
			containerVO.setStatus(container.getStatus());
			containerVO.setCustomerId(container.getCustomerId());

			Date date = new Date(container.getStatusTimestamp());
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			containerVO.setStatusTimestamp(format.format(date));

			String message = xmlMapper.writeValueAsString(containerVO);
			logger.info("Route message to STATUS.OUTBOUND.QUEUE  " + message);
			jmsTemplate.convertAndSend(STATUS_OUTBOUND_QUEUE, message);

		} catch (JsonProcessingException e) {
			logger.error("sendMessagesToStatusOutboundQueue() JsonProcessingException" + e.getMessage());
		}
	}

}
