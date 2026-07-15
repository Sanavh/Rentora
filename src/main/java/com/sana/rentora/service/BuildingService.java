package com.sana.rentora.service;

import java.util.List;

import com.sana.rentora.entity.Building;

public interface BuildingService {
	
	Building saveBuilding(Building building);
	
	List<Building> getAllBuildings();
	
	//Update and Delete Buildings
	Building getBuildingById(Long id);
	
	void deleteBuilding(Long id);
}
