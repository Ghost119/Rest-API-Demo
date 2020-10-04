package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.model.Car;
import com.example.demo.service.CarService;

@WebMvcTest(value = CarController.class)
public class CarControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CarService service;
	
	
	
	@Test
	public void retrieveCarById() throws Exception {
		//given
		Car mockCar = new Car(1, "Sedan", "Hyundai", "Verna", 1159000, 5);
		Optional<Car> mockCarOptional = Optional.of(mockCar);
		String expected = "{cid:1,type:Sedan,company:Hyundai,model:Verna,price:1159000,seats:5}";
		
		Mockito.when(service.getCarById(1)).thenReturn(mockCarOptional);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/cars/1")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		
		
		//assertions
		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
		
		//verify
		verify(service).getCarById(1);
	}
	
	@Test
	public void addNewCar() throws Exception {
		//given
		Car mockCar = new Car(6, "SUV", "Maruti", "Brezza", 893000, 5);
		String exampleCarJson = "{\"cid\":6,\"type\":\"SUV\",\"company\":\"Maruti\",\"model\":\"Brezza\",\"price\":893000,\"seats\":5}";
		
		Mockito.when(service.addCar(Mockito.any(Car.class))).thenReturn(mockCar);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/cars")
				.accept(MediaType.APPLICATION_JSON)
				.content(exampleCarJson)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		//assertions
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		
		assertEquals("http://localhost/cars/6",
				response.getHeader(HttpHeaders.LOCATION));
		
		//verify
		verify(service).addCar(Mockito.any(Car.class));
	}
	
	@Test
	public void updateNonExistingCar() throws Exception {
		//given
		String exampleCarJson = "{\"cid\":100,\"type\":\"SUV\",\"company\":\"Maruti\",\"model\":\"Brezza\",\"price\":893000,\"seats\":5}";
		
		Optional<Car> mockCarOptional = Optional.ofNullable(null);
		
		Mockito.when(service.getCarById(100)).thenReturn(mockCarOptional);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("/cars/100")
				.accept(MediaType.APPLICATION_JSON)
				.content(exampleCarJson)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		//assertions
		assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
		
		//verify
		verify(service).getCarById(100);
	}
	
	@Test
	public void updateExistingCar() throws Exception {
		//given
		String exampleCarJson = "{\"cid\":4,\"type\":\"Sedan\",\"company\":\"Maruti\",\"model\":\"Ciaz\",\"price\":1169000,\"seats\":5}";
		
		Car oldMockCar = new Car(4, "SUV", "Hyundai", "Creta", 1189000, 5);
		Optional<Car> mockCarOptional = Optional.of(oldMockCar);
		
		Car updatedMockCar = new Car(4, "Sedan", "Maruti", "Ciaz", 1169000, 5);; 
		
		Mockito.when(service.getCarById(4)).thenReturn(mockCarOptional);
		Mockito.when(service.addCar(Mockito.any(Car.class))).thenReturn(updatedMockCar);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("/cars/4")
				.accept(MediaType.APPLICATION_JSON)
				.content(exampleCarJson)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		//assertions
		assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
		
		//verify
		verify(service).getCarById(4);
		verify(service).addCar(Mockito.any(Car.class));
	}
	
}
