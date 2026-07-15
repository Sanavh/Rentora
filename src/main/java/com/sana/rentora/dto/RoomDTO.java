package com.sana.rentora.dto;

import com.sana.rentora.enums.RoomStatusEnum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RoomDTO {
	
	private Long id;
	@NotNull(message = "Room number is required")
	private int roomNumber;
	
	@NotNull(message = "Floor is required")
	private int floor;
	
	@NotNull(message = "Status is required")
	private RoomStatusEnum status;
	
	@NotNull(message = "BuildingId is required")
	private Long buildingId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public int getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	
	public @NotNull(message = "Status is required") RoomStatusEnum getStatus() {
		return status;
	}
	public void setStatus(RoomStatusEnum status) {
		this.status = status;
	}
	
	public Long getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}
	
}
