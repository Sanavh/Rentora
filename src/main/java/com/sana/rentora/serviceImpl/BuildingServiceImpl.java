package com.sana.rentora.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sana.rentora.entity.Building;
import java.lang.RuntimeException;
import com.sana.rentora.repository.BuildingRepository;
import com.sana.rentora.service.BuildingService;


@Service
public class BuildingServiceImpl implements BuildingService{

    private final BuildingRepository buildRepo;

	private static final Logger logger = LoggerFactory.getLogger(BuildingServiceImpl.class);
	
	public BuildingServiceImpl(BuildingRepository buildRepo) {
		this.buildRepo = buildRepo;
	} 

	@Override
	public Building saveBuilding(Building building) {
		logger.info("Entered saveBuilding method");
		Building savedBuilding = buildRepo.save(building);
		logger.info("Saved Building with ID: " + savedBuilding.getId());
		logger.info("Saved Building Details: {}",savedBuilding);
		return savedBuilding;
	}

	@Override
	public List<Building> getAllBuildings() {
		return buildRepo.findAll();
	}

	@Override
	public Building getBuildingById(Long id) {
		return buildRepo.findById(id).orElseThrow(()->new RuntimeException("Building not found with id: " + id));
	}

	@Override
	public void deleteBuilding(Long id) {
		 buildRepo.deleteById(id);
	}
	
	
	
	
}
