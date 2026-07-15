package com.sana.rentora.mapper;

import com.sana.rentora.dto.ContractDocumentDTO;
import com.sana.rentora.entity.ContractDocument;

public class ContractDocumentMapper {

	public static ContractDocumentDTO toDTO(ContractDocument doc) {
		
		ContractDocumentDTO dto = new ContractDocumentDTO();
		dto.setId(doc.getId());
		dto.setName(doc.getFileName());
		
		return dto;
	}
}
