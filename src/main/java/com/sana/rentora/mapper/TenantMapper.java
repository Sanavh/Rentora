package com.sana.rentora.mapper;

import com.sana.rentora.dto.TenantDTO;
import com.sana.rentora.entity.Tenant;

public class TenantMapper {
	
//	no need to create object : static, reusable anywhere
	public static TenantDTO toDTO(Tenant tenant) {
		
		TenantDTO dto = new TenantDTO();
		dto.setEmail(tenant.getEmail());
		dto.setGender(tenant.getGender());
		dto.setId(tenant.getId());
		dto.setName(tenant.getName());
		dto.setPhone(tenant.getPhone());
		return dto;
	}
	
	public static Tenant toEntity(TenantDTO dto) {

        Tenant tenant = new Tenant();
        tenant.setId(dto.getId());
        tenant.setName(dto.getName());
        tenant.setEmail(dto.getEmail());
        tenant.setPhone(dto.getPhone());
        tenant.setGender(dto.getGender());

        return tenant;
    }

}
