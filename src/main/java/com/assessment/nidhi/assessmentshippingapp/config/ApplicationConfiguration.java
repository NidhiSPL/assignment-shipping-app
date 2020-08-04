package com.assessment.nidhi.assessmentshippingapp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

	@Value("${activemq.broker.url}")
	String activemqBrokerUrl;

}
