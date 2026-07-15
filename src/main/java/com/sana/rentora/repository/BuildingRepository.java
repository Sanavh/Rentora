package com.sana.rentora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sana.rentora.entity.Building;

	@Repository
	public interface BuildingRepository extends JpaRepository<Building,Long> {
		
	}

