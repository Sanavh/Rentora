package com.sana.rentora.dto;

import java.time.LocalDate;

import com.sana.rentora.enums.ContractStatusEnum;

import jakarta.validation.constraints.NotNull;

public class ContractDTO {
	
	private Long id;

	@NotNull(message = "Room is required")
	private Long roomId;
	
	@NotNull(message = "Tenant is required")
	private Long tenantId;
	
	@NotNull(message = "Start date is required")
	private LocalDate startDate;
	
	private LocalDate endDate;
	
	@NotNull(message = "Monthly rent is required")
	private Double monthlyRent;
	
	private Double securityDeposit;
	
	@NotNull(message = "Status is required")
	private ContractStatusEnum status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Double getMonthlyRent() {
		return monthlyRent;
	}

	public void setMonthlyRent(Double monthlyRent) {
		this.monthlyRent = monthlyRent;
	}

	public Double getSecurityDeposit() {
		return securityDeposit;
	}

	public void setSecurityDeposit(Double securityDeposit) {
		this.securityDeposit = securityDeposit;
	}

	public ContractStatusEnum getStatus() {
		return status;
	}

	public void setStatus(ContractStatusEnum status) {
		this.status = status;
	}
	
	
	
}
