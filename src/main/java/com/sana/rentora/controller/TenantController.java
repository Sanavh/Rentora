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

import com.sana.rentora.dto.TenantDTO;
import com.sana.rentora.dto.TenantDocumentDTO;
import com.sana.rentora.service.TenantService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tenants")
public class TenantController {

	private final TenantService tenServ;
	
	public TenantController(TenantService tenServ) {
		this.tenServ = tenServ;
	}
	
	@PostMapping
	public TenantDTO createTenant(@Valid @RequestBody TenantDTO dto) {
		return tenServ.saveTenant(dto);
	}
	
	@GetMapping
	public List<TenantDTO> showTenants() {
		return tenServ.getAllTenants();
	}
	
	@GetMapping("/{id}")
	public TenantDTO getTenantById(@PathVariable Long id) {
		return tenServ.getTenantById(id);
	}
	
	@PutMapping("/{id}")
	public TenantDTO updateTenantInfo(@PathVariable Long id,@Valid @RequestBody TenantDTO dto) {
		return tenServ.updateTenant(id, dto);
	}
	
	@DeleteMapping("/{id}")
	public void deleteTenant(@PathVariable Long id) {
		tenServ.deleteTenant(id);
	}
	
	@PostMapping("/{id}/documents")
	public String uploadTenantDoc(@PathVariable Long id, 
			@RequestParam("file") MultipartFile file,
			@RequestParam("type") String type) {
				
			return tenServ.uploadTenantDoc(id, file, type);	
	}
	
	@GetMapping("{id}/documents")
	public List<TenantDocumentDTO> getTenantDocs(@PathVariable Long id){
		return tenServ.getAllTenantDocs(id);
	}
	
	@GetMapping("/documents/{id}/download")
	public ResponseEntity<Resource> downloadTenantDoc(@PathVariable Long id) {
		
		Resource resource = tenServ.downloadTenantDocument(id);
		
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
		
}
