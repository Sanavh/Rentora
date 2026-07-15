package com.sana.rentora.dto;

import java.time.LocalDate;

import com.sana.rentora.entity.Contract;

public class ContractHistoryDTO {
	
	private String tenantName;
	private LocalDate StartDate;
	private LocalDate EndDate;
	private Double monthlyRent;
	private String status;
	public String getTenantName() {
		return tenantName;
	}
	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}
	public LocalDate getStartDate() {
		return StartDate;
	}
	public void setStartDate(LocalDate startDate) {
		StartDate = startDate;
	}
	public LocalDate getEndDate() {
		return EndDate;
	}
	public void setEndDate(LocalDate endDate) {
		EndDate = endDate;
	}
	public Double getMonthlyRent() {
		return monthlyRent;
	}
	public void setMonthlyRent(Double monthlyRent) {
		this.monthlyRent = monthlyRent;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
