package com.assessment.nidhi.assessmentshippingapp.service;

import java.util.List;

import com.assessment.nidhi.assessmentshippingapp.model.Container;

public interface ContainerService {

	public List<Container> getContainersByOwner(int containerOwnerId);

	public Container addContainer(int containerOwnerId);
	
	public Container getContainer(int containerId);

	public Container updateContainer(int containerId, String status);

	public String deleteContainer(int containerId);

	public Container updateContainer(Container container);

}
