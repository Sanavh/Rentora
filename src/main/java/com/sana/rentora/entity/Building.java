package com.sana.rentora.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
//@ToString(exclude = "rooms")
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Building {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@EqualsAndHashCode.Include
	private Long id;
	
	private String name;
	private String address;
	private int totalRooms;
	
	
	public Building() {}
	
	public Building(String name,String address,int totalRooms) {
		this.name = name;
		this.address = address;
		this.totalRooms = totalRooms;
	}

//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getAddress() {
//		return address;
//	}
//
//	public void setAddress(String address) {
//		this.address = address;
//	}
//
//	public int getTotalRooms() {
//		return totalRooms;
//	}
//
//	public void setTotalRooms(int totalRooms) {
//		this.totalRooms = totalRooms;
//	}
}
