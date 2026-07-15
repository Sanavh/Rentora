package com.sana.rentora.mapper;

import com.sana.rentora.dto.ElectricityBillDTO;
import com.sana.rentora.entity.ElectricityBill;

public class ElectricityBillMapper {
	
	public static ElectricityBillDTO toDto(ElectricityBill pay) {
		
		ElectricityBillDTO dto = new ElectricityBillDTO();
		
		dto.setId(pay.getId());
		dto.setProofFileName(pay.getProofFileName());
		dto.setStatus(pay.getStatus().name());
		dto.setLastPaidDate(pay.getLastPaidDate());
		return dto;
		
	}

}
