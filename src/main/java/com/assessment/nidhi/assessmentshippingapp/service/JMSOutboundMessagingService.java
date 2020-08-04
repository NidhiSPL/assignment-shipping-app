package com.assessment.nidhi.assessmentshippingapp.service;

import com.assessment.nidhi.assessmentshippingapp.model.Container;

public interface JMSOutboundMessagingService {

	void sendMessagesToStatusInboundQueueDLQ(String message);
	void sendMessagesToStatusOutboundQueue(Container container);
}
