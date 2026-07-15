package com.sana.rentora.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.sana.rentora.dto.RoomDTO;
import com.sana.rentora.dto.RoomDetailsDTO;
import com.sana.rentora.dto.RoomHistoryResponseDTO;
import com.sana.rentora.entity.Room;

public interface RoomService {
	
	Room saveRoom(Room room);
	
	List<Room> getAllRooms();
	
	Room getRoomById(Long id);
	
	void deleteRoom(Long id);

	Room updateRoom(Long id, RoomDTO dto);
	
	List<Room> getRoomsByStatus(String status);
	
	List<Room> getRoomsByBuilding(Long buildingId);
	
	List<Room> getRoomsByFloor(int floor);
	
	Page<Room> getRoomsPaginated(int page, int size, String sortBu, String dir);
	
	Page<Room> searchRooms(String status, Integer floor, int page, int size, String sortBy, String dir);
	
	RoomDetailsDTO getRoomDetails(Long id);
	
	RoomHistoryResponseDTO getRoomHistory(Long id);
}
