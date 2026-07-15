package com.sana.rentora.entity;

import java.time.LocalDate;


import com.sana.rentora.enums.ContractStatusEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

/*
 * Entity layer:

👉 uses objects for relationships

DTO layer:

👉 uses IDs for references
 * 
 */

@Entity
@Data
public class Contract {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate startDate;
	
	private LocalDate endDate;
	
	private Double monthlyRent;
	
	private Double securityDeposit;
	
	@Enumerated(EnumType.STRING)
	private ContractStatusEnum status;
	
	@OneToOne
	@JoinColumn(name = "room_id")
	private Room room;
	
	@OneToOne
	@JoinColumn(name = "tenant_id")
	private Tenant tenant;
}
