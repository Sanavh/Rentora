package com.sana.rentora.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sana.rentora.entity.Building;
import com.sana.rentora.repository.BuildingRepository;
import com.sana.rentora.serviceImpl.BuildingServiceImpl;

public class BuildingServiceImplTest {

	@Mock
	private BuildingRepository buildRepo;
	
	private BuildingServiceImpl buildServ;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		buildServ = new BuildingServiceImpl(buildRepo);
	}
	
	@Test
	void testSaveBuilding() {
		Building building = new Building("Tower A","Downtown",10);
		
		when(buildRepo.save(building)).thenReturn(building);
		
		Building saved = buildServ.saveBuilding(building);
		
		assertEquals("Tower A",saved.getName());
		verify(buildRepo,times(1)).save(building);
	}
	
	
	
	@Test
	void testGetAllBuildings() {
		List<Building> MockList = Arrays.asList(
				new Building("Tower A","DXB",19),
				new Building("Tower B","AUH",25));
		
		when(buildRepo.findAll()).thenReturn(MockList);
		
		List<Building> buildingList = buildServ.getAllBuildings();
		
		assertEquals(2,buildingList.size());
		verify(buildRepo,times(1)).findAll();
	}
	
	
	
	@Test
	void testGetBuildingById() {
		Building building = new Building("Tower A","DXB",12);
		building.setId((long) 12);
		
		when(buildRepo.findById(building.getId())).thenReturn(Optional.of(building));
		
		Building result = buildServ.getBuildingById(12L);
		
		assertEquals("DXB",result.getAddress());
	}
	
	
	@Test
	void testDeleteBuilding() {
		doNothing().when(buildRepo).deleteById(1L);
		buildServ.deleteBuilding(1L);
		verify(buildRepo,times(1)).deleteById(1L);
	}
	

}
