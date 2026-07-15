package com.sana.rentora.service;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.sana.rentora.dto.ContractDTO;
import com.sana.rentora.dto.ContractDocumentDTO;

public interface ContractService {
	
	List<ContractDTO>getAllContracts();
	
	ContractDTO getContractById(Long id);
	
	ContractDTO createContract(ContractDTO dto);
	
	ContractDTO updateContract(Long id, ContractDTO dto);
	
	void deleteContract(Long id);
	
	ContractDTO closeContract(Long ContractId);
	
	String uploadContractDoc(Long id, MultipartFile file, String type);
	
	List<ContractDocumentDTO> getAllContracts(Long contractId);
	
	Resource downloadContractDocument(Long contractDocId);
}
