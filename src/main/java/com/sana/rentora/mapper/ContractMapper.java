package com.sana.rentora.mapper;

import com.sana.rentora.dto.ContractDTO;
import com.sana.rentora.dto.ContractHistoryDTO;
import com.sana.rentora.entity.Contract;
import com.sana.rentora.entity.Room;
import com.sana.rentora.entity.Tenant;

public class ContractMapper {

	public static Contract toEntity(ContractDTO dto,Tenant tenant,Room room) {
		
		Contract contract = new Contract();
		
		contract.setId(dto.getId());
		contract.setEndDate(dto.getEndDate());
		contract.setMonthlyRent(dto.getMonthlyRent());
		contract.setSecurityDeposit(dto.getSecurityDeposit());
		contract.setStartDate(dto.getStartDate());
		contract.setStatus(dto.getStatus());
		
		contract.setTenant(tenant);
		contract.setRoom(room);
		
		return contract;
	}
	
	
	
	public static ContractDTO toDTO(Contract contract) {
		
		ContractDTO dto = new ContractDTO();
		
		dto.setEndDate(contract.getEndDate());
		dto.setMonthlyRent(contract.getMonthlyRent());
		dto.setId(contract.getId());

		dto.setStartDate(contract.getStartDate());
		dto.setStatus(contract.getStatus());

		if(contract.getTenant() != null) {
			dto.setTenantId(contract.getTenant().getId());
		}
		
		if(contract.getRoom() != null) {
			dto.setRoomId(contract.getRoom().getId());
		}
		
		return dto;
	}
	
	
	public  static ContractHistoryDTO mapToHistoryDTO(Contract contract) {

	    ContractHistoryDTO dto = new ContractHistoryDTO();

	    dto.setTenantName(contract.getTenant().getName());
	    dto.setStartDate(contract.getStartDate());
	    dto.setEndDate(contract.getEndDate());
	    dto.setMonthlyRent(contract.getMonthlyRent());
	    dto.setStatus(contract.getStatus().name());

	    return dto;
	}

}
