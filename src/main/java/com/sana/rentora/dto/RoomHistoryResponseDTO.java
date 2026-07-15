package com.sana.rentora.dto;

import java.util.List;

public class RoomHistoryResponseDTO {
	
	private Long roomId;
	private int roomNum;
	
	private List<ContractHistoryDTO> history;

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public List<ContractHistoryDTO> getDto() {
		return history;
	}

	public void history(List<ContractHistoryDTO> history) {
		this.history = history;
	}
	
	

}
