package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Car;
import com.example.demo.repo.CarRepo;

@Service
public class CarService {

	@Autowired
	private CarRepo repo;
	
	public List<Car> getAllCars(){
		return repo.findAll();
	}
	
	public Optional<Car> getCarById(int cid){
		return repo.findById(cid);
	}
	
	public void deleteCarById(int cid) {
		repo.deleteById(cid);
	}
	
	public Car addCar(Car newCar) {
		return repo.save(newCar);
	}
}
