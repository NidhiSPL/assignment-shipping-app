package com.assessment.nidhi.assessmentshippingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.nidhi.assessmentshippingapp.model.Container;
import com.assessment.nidhi.assessmentshippingapp.service.ContainerService;

@RestController
@RequestMapping(value = "/api/v1")
public class ContainerRestController {

	@Autowired
	private ContainerService service;

	/**
	 * <p>
	 * get list of containers by owner id from database.
	 * </p>
	 * 
	 * @param containerOwnerId
	 * @return list of containers tagged by given containerOwnerId
	 */
	@GetMapping("/container/{containerOwnerId}")
	public List<Container> getContainersByOwner(@PathVariable int containerOwnerId) {
		return service.getContainersByOwner(containerOwnerId);
	}

	/**
	 * <p>
	 * Add a new container by supplying containerOwnerId (customerId should default
	 * to 0, status should default to AVAILABLE)
	 * <p>
	 * 
	 * @param containerOwnerId
	 * @return Container
	 * 
	 */
	@PostMapping("/container/{containerOwnerId}")
	public Container addContainer(@PathVariable int containerOwnerId) {
		return service.addContainer(containerOwnerId);
	}

	/**
	 * <p>
	 * update the container with container id.
	 * </p>
	 * 
	 * @param containerId
	 * @param status
	 * @return Container
	 */
	@PutMapping("/container/{containerId}")
	public Container updateContainer(@PathVariable int containerId, @RequestParam String status) {
		return service.updateContainer(containerId, status);
	}

	/**
	 * <p>
	 * Delete container with container id.
	 * </p>
	 * 
	 * @param containerId
	 * @return string
	 */
	@DeleteMapping("/container/{containerId}")
	public String deleteContainer(@PathVariable int containerId) {
		return service.deleteContainer(containerId);
	}
}
