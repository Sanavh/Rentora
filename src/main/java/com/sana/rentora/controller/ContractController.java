package com.sana.rentora.controller;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sana.rentora.dto.ContractDTO;
import com.sana.rentora.dto.ContractDocumentDTO;
import com.sana.rentora.service.ContractService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/contract")
public class ContractController {
	
	private final ContractService contServ;
	
	public ContractController(ContractService contServ) {
		this.contServ = contServ;
	}

	@GetMapping
	public List<ContractDTO> getAllContracts() {
		return contServ.getAllContracts();
	}
	
	@GetMapping("{id}")
	public ContractDTO getContractById(@PathVariable Long id) {
		return contServ.getContractById(id);
	}
	
	@PostMapping
	public ContractDTO createContract(@Valid @RequestBody ContractDTO dto) {
		return contServ.createContract(dto);
	}
	
	@PutMapping("{id}")
	public ContractDTO updateContract(@PathVariable Long id,@RequestBody ContractDTO dto) {
		
		return contServ.updateContract(id, dto);
	}
	
	@DeleteMapping("{id}")
	public String deleteContract(@PathVariable Long id) {
		contServ.deleteContract(id);
		return "Contract " + id + "deleted successfully";
	}
	
	@PutMapping("/{id}/close")
	public ContractDTO closeContract(@PathVariable Long id) {
		return contServ.closeContract(id);
	}

	@PostMapping("/{id}/documents")
	public String contractDoc(@PathVariable Long id, @RequestParam("file") MultipartFile file, @RequestParam("type") String type) {
		return contServ.uploadContractDoc(id, file, type);
	}

	@GetMapping("/{id}/documents")
	public List<ContractDocumentDTO> getAllContractsPerTenant(@PathVariable Long id){
		return contServ.getAllContracts(id);
	}
	
	/*
	 * Resource represents downloadable file content,
	 *  while ResponseEntity gives full control over HTTP headers 
	 *  and response behavior required for file downloads.
	 */
	
	@GetMapping("/documents/{id}/download")
	public ResponseEntity<Resource> downloadContractDocument(@PathVariable Long id){
		
		Resource resource = contServ.downloadContractDocument(id);
		
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename = \""+resource.getFilename()+"\"")
				.body(resource);
	}
}
