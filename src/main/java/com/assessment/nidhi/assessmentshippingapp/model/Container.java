package com.assessment.nidhi.assessmentshippingapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "CONTAINER_STATUS")
public class Container {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int containerId;
	
	@Column(name = "OWNER_ID")
	private int ownerId;
	
	@Column(name = "CUSTOMER_ID")
	private int customerId;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "EVENT_TIMESTAMP")
	private Long statusTimestamp;
	@Transient
	private String statusTimestampFormat;

	public int getContainerId() {
		return containerId;
	}

	public void setContainerId(int containerId) {
		this.containerId = containerId;
	}

	public int getContainerOwnerId() {
		return ownerId;
	}

	public void setContainerOwnerId(int containerOwnerId) {
		this.ownerId = containerOwnerId;
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

	public Long getStatusTimestamp() {
		return statusTimestamp;
	}

	public void setStatusTimestamp(Long statusTimestamp) {
		this.statusTimestamp = statusTimestamp;
	}

	public String getStatusTimestampFormat() {
		return statusTimestampFormat;
	}

	public void setStatusTimestampFormat(String statusTimestampFormat) {
		this.statusTimestampFormat = statusTimestampFormat;
	}

	@Override
	public String toString() {
		return "Container{" + "containerId=" + containerId + ", containerOwnerId=" + ownerId + ", customerId="
				+ customerId + ", status='" + status + '\'' + ", statusTimestamp=" + statusTimestamp
				+ ", statusTimestampFormat='" + statusTimestampFormat + '\'' + '}';

	}
}
