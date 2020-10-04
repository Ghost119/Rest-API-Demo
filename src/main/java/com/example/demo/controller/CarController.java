package com.example.demo.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.model.Car;
import com.example.demo.service.CarService;

@RestController
public class CarController {
	
	@Autowired
	private CarService service;
	
	@GetMapping("/cars")
	public List<Car> retrieveAllCars(){
		return service.getAllCars();
	}
	
	@GetMapping("/cars/{cid}")
	public Optional<Car> retrieveCarById(@PathVariable("cid") int cid){
		return service.getCarById(cid);
	}
	
	@DeleteMapping("/cars/{cid}")
	public ResponseEntity<Object> deleteCarById(@PathVariable("cid") int cid) {
		Optional<Car> carOptional = service.getCarById(cid);
		
		if(!carOptional.isPresent())
		{return ResponseEntity.notFound().build();}
		
		service.deleteCarById(cid);
		
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/cars")
	public ResponseEntity<Object> createCar(@RequestBody Car newCar){
		Car savedCar = service.addCar(newCar);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedCar.getCid()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/cars/{cid}")
	public ResponseEntity<Object> updateCar(@RequestBody Car updatedCar, @PathVariable int cid){
		Optional<Car> carOptional = service.getCarById(cid);
		
		if(!carOptional.isPresent())
		{return ResponseEntity.notFound().build();}
		
		updatedCar.setCid(cid);
		service.addCar(updatedCar);
		
		return ResponseEntity.noContent().build();
	}
}
