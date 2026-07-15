package com.sana.rentora.mapper;

import com.sana.rentora.dto.RoomDTO;
import com.sana.rentora.entity.Room;

public class RoomMapper {

	public static RoomDTO convertToDTO(Room room) {
		
		RoomDTO dto = new RoomDTO();
		dto.setFloor(room.getFloor());
		dto.setId(room.getId());
		dto.setRoomNumber(room.getRoomNumber());
		dto.setStatus(room.getStatus());
		dto.setBuildingId(room.getBuilding().getId());
		
		return dto;
		
	}
}
