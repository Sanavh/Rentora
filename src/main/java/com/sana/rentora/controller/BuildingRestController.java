package com.sana.rentora.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sana.rentora.dto.BuildingDTO;
import com.sana.rentora.entity.Building;
import com.sana.rentora.mapper.BuildingMapper;
import com.sana.rentora.service.BuildingService;

@RestController
@RequestMapping("/api/buildings")
public class BuildingRestController {

	private final BuildingService buildServ;
	
	public BuildingRestController(BuildingService buildServ) {
		this.buildServ = buildServ;
	}
	
	//Create building
	@PostMapping
	public BuildingDTO createBuilding(@RequestBody Building building) {
		
		Building saved = buildServ.saveBuilding(building);
		return BuildingMapper.toDTO(saved);
	}
	
	@GetMapping
	public List<BuildingDTO> getBuildings(){
		return buildServ.getAllBuildings()
				.stream()
				.map(BuildingMapper::toDTO)
				.collect(Collectors.toList());
	}
	
	@GetMapping("/{id}")
	public BuildingDTO getBuildingById(@PathVariable Long id) {
		return BuildingMapper.toDTO(buildServ.getBuildingById(id));
	}
	
	@PutMapping("/{id}")
	public BuildingDTO update(@PathVariable Long id, @RequestParam BuildingDTO dto) {
		
		Building entity = BuildingMapper.toEntity(dto);
		entity.setId(id);
		
		return BuildingMapper.toDTO(buildServ.saveBuilding(entity));
	}
	
	@DeleteMapping("/{id}")
	public void deleteBuilding(@PathVariable Long id) {
		buildServ.deleteBuilding(id);
	}
	
}
