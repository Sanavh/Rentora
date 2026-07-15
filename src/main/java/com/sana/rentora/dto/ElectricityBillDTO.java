package com.sana.rentora.dto;

import java.time.LocalDate;

public class ElectricityBillDTO {
	
	private Long id;
	
	private LocalDate lastPaidDate;
	
	private String status;
	
	private String proofFileName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProofFileName() {
		return proofFileName;
	}

	public void setProofFileName(String proofFileName) {
		this.proofFileName = proofFileName;
	}

	public LocalDate getLastPaidDate() {
		return lastPaidDate;
	}

	public void setLastPaidDate(LocalDate lastPaidDate) {
		this.lastPaidDate = lastPaidDate;
	}
	
	
	
	
	
	
	
}
