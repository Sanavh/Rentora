package com.sana.rentora.dto;

public class TenantDocumentDTO {
	
	private Long tenantDocumentId;
	private String fileName;
	private String docType;
	
	public Long getId() {
		return tenantDocumentId;
	}
	public void setId(Long id) {
		this.tenantDocumentId = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}
	
	

}
