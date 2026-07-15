package com.sana.rentora.service;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.sana.rentora.dto.TenantDTO;
import com.sana.rentora.dto.TenantDocumentDTO;


public interface TenantService {
	
	TenantDTO saveTenant(TenantDTO tenDto);
	
	List<TenantDTO> getAllTenants();
	
	TenantDTO getTenantById(Long id);
	
	TenantDTO updateTenant(Long id,TenantDTO tenDto);
	
	void deleteTenant(Long id);
	
	TenantDTO assignRoom(Long tenantId, Long roomId);
	
	TenantDTO removeRoom(Long tenantId);
	
	String uploadTenantDoc(Long id, MultipartFile file, String type);
	
	List<TenantDocumentDTO> getAllTenantDocs(Long id);
	
	Resource downloadTenantDocument(Long tenantId);
	
	
}
