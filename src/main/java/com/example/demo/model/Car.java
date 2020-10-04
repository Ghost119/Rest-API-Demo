package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	private int cid;
	private String type;
	private String company;
	private String model;
	private int price;
	private int seats;
	
	public Car() {
		
	}
	
	public Car(int cid, String type, String company, String model, int price, int seats) {
		this.cid = cid;
		this.type = type;
		this.company = company;
		this.model = model;
		this.price = price;
		this.type = type;
		this.seats = seats;
	}
	
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getSeats() {
		return seats;
	}
	public void setSeats(int seats) {
		this.seats = seats;
	}
	
	@Override
	public String toString() {
		return "Car [cid=" + cid + ", type=" + type + ", company="+company+", model="+model
				+", price="+price+", seats="+seats+"]";
	}
}
