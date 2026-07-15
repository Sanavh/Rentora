package com.sana.rentora.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sana.rentora.entity.Room;


@Repository
public interface RoomRepository extends JpaRepository<Room,Long> {
	
	Optional<Room> findByRoomNumberAndFloorAndBuilding_Id(int roomNum, int floor, Long buildId);
	
	List<Room> findByStatus(String status);
	
	List<Room> findByFloor(int floor);
	
	List<Room> findByBuilding_Id(Long buildingId);
	
}
