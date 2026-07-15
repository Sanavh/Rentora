package com.sana.rentora.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sana.rentora.dto.RoomDTO;
import com.sana.rentora.dto.RoomDetailsDTO;
import com.sana.rentora.dto.RoomHistoryResponseDTO;
import com.sana.rentora.entity.Building;
import com.sana.rentora.entity.Room;
import com.sana.rentora.mapper.RoomMapper;
import com.sana.rentora.service.BuildingService;
import com.sana.rentora.service.RoomService;

import jakarta.validation.Valid;

//This is NOT for HTML pages , This returns JSON data
@RestController

/*Base URL for all APIs in this controller ---> So all methods become: 
POST   /api/rooms
GET    /api/rooms
PUT    /api/rooms/{id}
DELETE /api/rooms/{id} */
@RequestMapping("/api/rooms")
public class RoomRestController {

	private final RoomService roomServ;
	private final BuildingService buildServ;
	
	
	//We are giving controller access to: Room DB operations and Building DB operations
	public RoomRestController(RoomService roomServ, BuildingService buildServ) {
		this.roomServ = roomServ;
		this.buildServ = buildServ;
	}
	
	@GetMapping("/{id}/details")
	public RoomDetailsDTO getRoomDetails(@PathVariable Long id) {
	    return roomServ.getRoomDetails(id);
	}
	
	//Used to CREATE data
	@PostMapping
	public RoomDTO createRoom(@Valid @RequestBody RoomDTO dto) //Convert JSON from Postman → Java object (RoomDTO)
	{
		//We only got buildingId from frontend We now fetch full Building object from DB
		Building building = buildServ.getBuildingById(dto.getBuildingId());
		
		Room room = new Room();
		
		room.setRoomNumber(dto.getRoomNumber());
		room.setFloor(dto.getFloor());
		room.setStatus(dto.getStatus());
		room.setBuilding(building);
		
		Room saved = roomServ.saveRoom(room);
		
		dto.setId(saved.getId());
		return dto;
	}
	
	@GetMapping
	public List<RoomDTO> getAllRooms(){
		
		return roomServ.getAllRooms()
				.stream().map(room -> {
					RoomDTO dto = new RoomDTO();
					dto.setId(room.getId());
					dto.setBuildingId(room.getBuilding().getId());
					dto.setFloor(room.getFloor());
					dto.setRoomNumber(room.getRoomNumber());
					dto.setStatus(room.getStatus());
					return dto;
				}).toList();
	}
	
	
	@PutMapping("/{id}")
	//@PathVariable Long id : Take ID from URL
	//@RequestBody RoomDTO dto : Take JSON body and convert into Java object
	public RoomDTO updateRoom(@PathVariable Long id, @Valid @RequestBody RoomDTO dto) {
		
		Room updatedRoom = roomServ.updateRoom(id,dto);
		RoomDTO response = new RoomDTO();
		
		response.setId(id);
		response.setBuildingId(updatedRoom.getBuilding().getId());
		response.setFloor(updatedRoom.getFloor());
		response.setRoomNumber(updatedRoom.getRoomNumber());
		response.setStatus(updatedRoom.getStatus());
		
		return response;
	}
	
	@DeleteMapping("{id}")
	public String deleteRoom(@PathVariable Long id) {
		roomServ.deleteRoom(id);
		return "Room deleted successfully";
	}
	
	@GetMapping("/status/{status}")
	public List<RoomDTO> getByStatus(@PathVariable String status){
		return roomServ.getRoomsByStatus(status)
				.stream()
				.map(RoomMapper::convertToDTO)
				.toList();
	}
	
	@GetMapping("/floor/{floor}")
	public List<RoomDTO> getByFloor(@PathVariable int floor){
		return roomServ.getRoomsByFloor(floor)
				.stream()
				.map(RoomMapper::convertToDTO)
				.toList();
	}
	
	@GetMapping("/buildingId/{id}")
	public List<RoomDTO> getbyBuildingId(@PathVariable Long id){
		return roomServ.getRoomsByBuilding(id)
				.stream()
				.map(RoomMapper::convertToDTO)
				.toList();
	}
	
	@GetMapping("/paginated")
	public Page<RoomDTO> getRoomsPaginated(@RequestParam int page, 
			@RequestParam int size, 
			@RequestParam(defaultValue="roomNumber") String sortBy, 
			@RequestParam(defaultValue="desc") String dir ){
		return roomServ.getRoomsPaginated(page, size, sortBy, dir)
	            .map(RoomMapper::convertToDTO);
	
	}
	
	@GetMapping("/search")
	public Page<RoomDTO> searchRooms(
	        @RequestParam(required = false) String status,
	        @RequestParam(required = false) Integer floor,
	        @RequestParam int page,
	        @RequestParam int size,
	        @RequestParam(defaultValue = "id") String sortBy,
	        @RequestParam(defaultValue = "asc") String direction) {

	    return roomServ.searchRooms(status, floor, page, size, sortBy, direction)
	            .map(RoomMapper::convertToDTO);
	}
	
	@GetMapping("{id}/history")
	public RoomHistoryResponseDTO getHistory(@PathVariable Long id) {
		return roomServ.getRoomHistory(id);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
// **************** STREAMS ***************************
	@GetMapping("/available")
	public List<RoomDTO> getAvailRooms(){
		
		return roomServ.getAllRooms()
				.stream()
				.filter(room -> room.getStatus().equals("AVAILABLE"))
				.map(room ->
				{
					RoomDTO dto = new RoomDTO();
					dto.setStatus(room.getStatus());
					dto.setBuildingId(room.getBuilding().getId());
					dto.setFloor(room.getFloor());
					dto.setId(room.getId());
					dto.setRoomNumber(room.getRoomNumber());
					return dto;
				}).toList();
	}
	
	@GetMapping("/available-on-floor-2")
	public List<RoomDTO> getAvailRoomsFloor2(){
		
		return roomServ.getAllRooms()
				.stream()
				.filter(room -> ("AVAILABLE".equals(room.getStatus())) && (2==room.getFloor()))
				.map(room ->
				{
					RoomDTO dto = new RoomDTO();
					dto.setBuildingId(room.getBuilding().getId());
					dto.setFloor(room.getFloor());
					dto.setId(room.getId());
					dto.setRoomNumber(room.getRoomNumber());
					dto.setStatus(room.getStatus());
					return dto;
				}).toList();
	}
	
	@GetMapping("/available-room-numbers")
	public List<Integer> getAvailRoomNumbers(){
		
		return roomServ.getAllRooms()
				.stream()
				.filter(room -> ("AVAILABLE".equals(room.getStatus())))
				.map(room -> {
					int roomNumbers = room.getRoomNumber();
					return roomNumbers;
				}).toList();
	}
	
	@GetMapping("/available-rooms-count")
	public long getAvailRoomCount() {
		return roomServ.getAllRooms()
				.stream()
				.filter(room -> "AVAILABLE".equals(room.getStatus()))
				.count();
	}
	
	@GetMapping("/any-status-occupied")
	public boolean getAnyOccupied() {
		return roomServ.getAllRooms()
				.stream()
				.anyMatch(room -> "OCCUPIED".equals(room.getStatus()));
	}
	
	@GetMapping("/occupied-room-numbers-floor-22")
	public List<Integer> getOccupiedRoomNumbersOnFloor22(){
		return roomServ.getAllRooms()
				.stream()
				.filter(room -> "OCCUPIED".equals(room.getStatus()))
				.filter(room -> 22==room.getFloor())
				.map(room -> room.getRoomNumber()
				)
				.toList();
	}
	
	
}
