package com.sana.rentora.repository;

import org.springframework.stereotype.Repository;

import com.sana.rentora.entity.ContractDocument;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ContractDocumentRepository extends JpaRepository<ContractDocument,Long>{
		
	List<ContractDocument> findByContractId(Long id);
}
