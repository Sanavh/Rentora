package com.sana.rentora.dto;

public class ContractDocumentDTO {
	
	private Long ContractDocId;
	private String name;
	
	public Long getId() {
		return ContractDocId;
	}
	public void setId(Long id) {
		this.ContractDocId = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
