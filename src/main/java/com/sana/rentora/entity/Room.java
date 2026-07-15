package com.sana.rentora.entity;


import com.sana.rentora.enums.RoomStatusEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

//@ToString(exclude = "building")
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Room {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
//		@EqualsAndHashCode.Include
		private Long id;
		
		private int roomNumber;
		private int floor;
		
		@Enumerated(EnumType.STRING)
		private RoomStatusEnum status;
		
		@ManyToOne
		private Building building;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public int getRoomNumber() {
			return roomNumber;
		}

		public void setRoomNumber(int roomNumber) {
			this.roomNumber = roomNumber;
		}

		public int getFloor() {
			return floor;
		}

		public void setFloor(int floor) {
			this.floor = floor;
		}

		public RoomStatusEnum getStatus() {
			return status;
		}

		public void setStatus(RoomStatusEnum status) {
			this.status = status;
		}

		public Building getBuilding() {
			return building;
		}

		public void setBuilding(Building building) {
			this.building = building;
		}
		
		
}		