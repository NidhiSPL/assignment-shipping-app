package com.assessment.nidhi.assessmentshippingapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assessment.nidhi.assessmentshippingapp.enums.ContainerEnum;
import com.assessment.nidhi.assessmentshippingapp.exception.ContainerException;
import com.assessment.nidhi.assessmentshippingapp.model.Container;
import com.assessment.nidhi.assessmentshippingapp.repository.ContainerRepository;
import com.assessment.nidhi.assessmentshippingapp.utils.DateUtils;

@Service
public class ContainerServiceImpl implements ContainerService {

	@Autowired
	ContainerRepository containerRepository;

	/*
	 * get list of containers for the given containerOwnerId
	 * 
	 */
	@Override
	public List<Container> getContainersByOwner(int containerOwnerId) {
		List<Container> containerList = (List<Container>) containerRepository.findByOwnerId(containerOwnerId);
		return containerList;
	}

	@Override
	public Container addContainer(int containerOwnerId) {
		Container container = new Container();
		container.setCustomerId(0);
		container.setContainerOwnerId(containerOwnerId);
		container.setStatus(ContainerEnum.getValue(ContainerEnum.AVAILABLE));
		container.setStatusTimestamp(System.currentTimeMillis());
		return containerRepository.save(container);
	}

	@Override
	public Container updateContainer(int containerId, String status) {
		Optional<Container> container = containerRepository.findById(containerId);
		if (!container.isPresent()) {
			throw new ContainerException(String.format("Container is not available with containerId %s", containerId));
		}
		Container containerObj = container.get();
		containerObj.setStatus(status);
		containerObj.setStatusTimestampFormat(DateUtils.convertTimeToStringFormat(containerObj.getStatusTimestamp()));
		return containerRepository.save(containerObj);
	}

	@Override
	public String deleteContainer(int containerId) throws ContainerException {
		Optional<Container> container = containerRepository.findById(containerId);

		if (!container.isPresent()) {
			throw new ContainerException(String.format("Container is not available with containerId %s", containerId));
		}
		containerRepository.deleteById(containerId);
		return String.format("Container with containerId : %s deleted successfully.", containerId);

	}

	@Override
	public Container getContainer(int containerId) {
		return containerRepository.findById(containerId).get();
	}

	@Override
	public Container updateContainer(Container container) {
		return containerRepository.save(container);
	}

}
