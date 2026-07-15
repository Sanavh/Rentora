package com.sana.rentora.entity;

import java.time.LocalDate;

import com.sana.rentora.enums.BillStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class ElectricityBill {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate lastPaidDate;
	
	@Enumerated(EnumType.STRING)
	private BillStatus status;
	
	private String proofFileName;
	
	private String proofFilePath;
	
	@ManyToOne
	@JoinColumn(name = "tenant_id")
	private Tenant tenant;
	
	
	
		
}
