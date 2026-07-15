package com.sana.rentora.dto;

import com.sana.rentora.enums.RoomStatusEnum;

public class RoomDetailsDTO {

	private Long roomId;
	private int roomNumber;
	private Integer floor;
	private String status;
	
	private TenantDTO tenant;
	private ContractDTO contract;
	
	public Long getRoomId() {
		return roomId;
	}
	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}
	public int getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	public Integer getFloor() {
		return floor;
	}
	public void setFloor(Integer floor) {
		this.floor = floor;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public TenantDTO getTenant() {
		return tenant;
	}
	public void setTenant(TenantDTO tenant) {
		this.tenant = tenant;
	}
	public ContractDTO getContract() {
		return contract;
	}
	public void setContract(ContractDTO contract) {
		this.contract = contract;
	}
	
	
}
