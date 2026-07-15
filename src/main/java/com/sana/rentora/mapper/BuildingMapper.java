package com.sana.rentora.mapper;

import com.sana.rentora.dto.BuildingDTO;
import com.sana.rentora.entity.Building;

public class BuildingMapper {
	
	public static Building toEntity(BuildingDTO bDto) {
		
		Building build = new Building();
		build.setAddress(bDto.getAddress());
		build.setId(bDto.getId());
		build.setName(bDto.getName());
		build.setTotalRooms(bDto.getTotalRooms());
		
		return build;
	}
	
	public static BuildingDTO toDTO(Building build) {
		
		BuildingDTO dto = new BuildingDTO();
		dto.setAddress(build.getAddress());
		dto.setId(build.getId());
		dto.setName(build.getName());
		dto.setTotalRooms(build.getTotalRooms());
		
		return dto;
	}

}
