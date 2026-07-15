package com.sana.rentora.service;

import org.springframework.web.multipart.MultipartFile;

import com.sana.rentora.dto.ElectricityBillDTO;

public interface ElectricityBillService {
	
	void initialPaymentForTenant(Long tenantId);
	
	ElectricityBillDTO getPayment(Long tenantId);
	
	void markAsPaid(Long tenantId, MultipartFile file);
	

}
