package com.assessment.nidhi.assessmentshippingapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assessment.nidhi.assessmentshippingapp.model.Container;

@Repository
public interface ContainerRepository extends CrudRepository<Container, Integer>{
	
	public List<Container> findByOwnerId(int containerOwnerId);
		
	

}
