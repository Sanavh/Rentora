package com.sana.rentora.mapper;

import com.sana.rentora.dto.TenantDocumentDTO;
import com.sana.rentora.entity.TenantDocument;

public class TenantDocumentMapper {
	
	public static TenantDocumentDTO toDTO(TenantDocument doc) {
		
		TenantDocumentDTO dto = new TenantDocumentDTO();
		dto.setDocType(doc.getDocumentType());
		dto.setFileName(doc.getFileName());
		dto.setId(doc.getId());
		
		return dto;
	}

}
