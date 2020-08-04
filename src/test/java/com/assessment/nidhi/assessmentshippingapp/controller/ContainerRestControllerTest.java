package com.assessment.nidhi.assessmentshippingapp.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.assessment.nidhi.assessmentshippingapp.controller.ContainerRestController;
import com.assessment.nidhi.assessmentshippingapp.model.Container;
import com.assessment.nidhi.assessmentshippingapp.service.ContainerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(value = ContainerRestController.class)
@WithMockUser
public class ContainerRestControllerTest {

	String sampleJson = "{\r\n" + "	\"containerId\": 10,\r\n" + "	\"containerOwnerId\": 10,\r\n"
			+ "	\"customerId\": 0,\r\n" + "	\"status\": \"AVAILABLE\",\r\n" + "	\"statusTimestamp\": 1425744000000\r\n"
			+ "}";
	
	Container container;

	@MockBean
	ContainerServiceImpl containerServiceImpl;

	@Autowired
	private MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		try {
			ObjectMapper mapper = new ObjectMapper();
			container = mapper.readValue(sampleJson, Container.class);
			System.out.println("done");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAddContainer() {
		Mockito.when(containerServiceImpl.addContainer(Mockito.anyInt())).thenReturn(container);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/container/10")
				.accept(MediaType.APPLICATION_JSON).content(sampleJson).contentType(MediaType.APPLICATION_JSON);

		try {
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();

			MockHttpServletResponse response = result.getResponse();

			int status = response.getStatus();
			String response1 = response.getContentAsString();

			assertEquals(HttpStatus.OK.value(), status);
			JSONAssert.assertEquals(sampleJson, response1, false);
		} catch (Exception e) {
		}
	}

	@Test
	public void testGetContainersByOwner() throws Exception {

		List<Container> list = new ArrayList<Container>();
		Container c1 = container;
		Container c2 = container;
		c2.setContainerId(11);
		Container c3 = container;
		c2.setContainerId(12);

		list.add(c1);
		list.add(c2);
		list.add(c3);

		Mockito.when(containerServiceImpl.getContainersByOwner(Mockito.any(Integer.class))).thenReturn(list);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/container/10")
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		String responseContent = response.getContentAsString();
		ObjectMapper mapper = new ObjectMapper();
		List<Object> containerList = (Arrays.asList(mapper.readValue(responseContent, Container[].class)));
		assertEquals(HttpStatus.OK.value(), response.getStatus());

		assertTrue(containerList.size() == 3);

	}

	@Test
	public void testUpdateContainer() throws Exception {

		container.setStatus("WAITING_FOR_PICKUP");
		Mockito.when(containerServiceImpl.updateContainer(Mockito.any(Integer.class), Mockito.any(String.class)))
				.thenReturn(container);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1/container/10?status=WAITING_FOR_PICKUP")
				.content(sampleJson).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		String responseContent = response.getContentAsString();
		ObjectMapper mapper = new ObjectMapper();
		Container containerObj = mapper.readValue(responseContent, Container.class);

		assertEquals(HttpStatus.OK.value(), response.getStatus());

		assertEquals(container.getStatus(), containerObj.getStatus());
	}

	@Test
	public void testDeleteContainer() throws Exception {

		Mockito.when(containerServiceImpl.deleteContainer(Mockito.any(Integer.class)))
				.thenReturn("Container with ownerId : 10 deleted successfully.");
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/container/10")
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();

		String responseContent = response.getContentAsString();

		assertEquals(HttpStatus.OK.value(), response.getStatus());

		assertEquals("Container with ownerId : 10 deleted successfully.", responseContent);
	}
}
