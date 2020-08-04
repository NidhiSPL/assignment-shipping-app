package com.assessment.nidhi.assessmentshippingapp.vos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContainerVo {

	@JsonProperty("ID")
	private int containerId;

	@JsonProperty("OWNER_ID")
	private int containerOwnerId;

	@JsonProperty("CUSTOMER_ID")
	private int customerId;

	@JsonProperty("STATUS")
	private String status;

	@JsonProperty("STATUS_TIMESTAMP")
	private String statusTimestamp;

	public int getContainerId() {
		return containerId;
	}

	public void setContainerId(int containerId) {
		this.containerId = containerId;
	}

	public int getContainerOwnerId() {
		return containerOwnerId;
	}

	public void setContainerOwnerId(int containerOwnerId) {
		this.containerOwnerId = containerOwnerId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusTimestamp() {
		return statusTimestamp;
	}

	public void setStatusTimestamp(String statusTimestamp) {
		this.statusTimestamp = statusTimestamp;
	}

}
