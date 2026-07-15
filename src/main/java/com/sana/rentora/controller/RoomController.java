package com.sana.rentora.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.sana.rentora.entity.Building;
import com.sana.rentora.entity.Room;
import com.sana.rentora.service.BuildingService;
import com.sana.rentora.service.RoomService;

@Controller
public class RoomController {
	
	private static final Logger logger =  LoggerFactory.getLogger(RoomController.class);
	
	private final RoomService roomServ;
	private final BuildingService buildServ;

	private Long buildingId;
	
	//constructor injection
	public RoomController(RoomService roomServ,BuildingService buildServ) {
		this.roomServ = roomServ;
		this.buildServ = buildServ;
	}
	
	
	@GetMapping("/rooms/new")
	public String showAddRoomForm(Model model) {
		model.addAttribute("room",new Room());
		model.addAttribute("buildings", buildServ.getAllBuildings());
		return "room_form";
	}
	
	@PostMapping("/rooms/save")
	public String saveRoom(Room room) {
		logger.info("inside save room method");
		
		buildingId = room.getBuilding().getId();
		
		logger.info("building id fetched is **********: " + buildingId);
		
		Building building = buildServ.getBuildingById(buildingId);
		logger.info("building details for room : {} " ,building);
		room.setBuilding(building);
		
		
		roomServ.saveRoom(room);
		
		return "redirect:/rooms";
	}
	
	
	@GetMapping("/rooms")
	public String listRooms(Model model) {
		model.addAttribute("rooms",roomServ.getAllRooms());
		return "room_list";
	}
	
	@GetMapping("rooms/edit/{id}")
	public String showEditForm(@PathVariable Long id,Model model) {
		Room room = roomServ.getRoomById(id);
		logger.info("building details of room to be edited are : {}",room.getBuilding());
		model.addAttribute("room",room);
//		model.addAttribute("roomBuilding",room.getBuilding());
		model.addAttribute("buildings",buildServ.getAllBuildings());
		return "room_form";
	}
	
	@GetMapping("/rooms/delete/{id}")
	public String deleteRoom(@PathVariable Long id) {
		roomServ.deleteRoom(id);
		return "redirect:/rooms";
	}
	
}
