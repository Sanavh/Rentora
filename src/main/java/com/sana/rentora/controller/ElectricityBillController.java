package com.sana.rentora.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sana.rentora.dto.ElectricityBillDTO;
import com.sana.rentora.service.ElectricityBillService;

@RestController
@RequestMapping("/api/electricity-bills")
public class ElectricityBillController {
	
	private final ElectricityBillService billServ;
	
	public ElectricityBillController(ElectricityBillService billServ) {
		this.billServ = billServ;
	}
	
	@PostMapping("/{tenantId}/init")
	public ResponseEntity<String> initBill(@PathVariable Long tenantId){
		
		billServ.initialPaymentForTenant(tenantId);
		return ResponseEntity.ok("Electricity Bill Tracking Initialized");
	}
	
	@GetMapping("/{tenantId}/bill-details")
	public ElectricityBillDTO getBillDetails(@PathVariable Long tenantId) {
		
		return billServ.getPayment(tenantId);
	}
	
	@PutMapping("/{tenantId}/pay")
	public ResponseEntity<String> markPaid(@PathVariable Long tenantId, @RequestParam(required=false)MultipartFile file){
		
		billServ.markAsPaid(tenantId, file);
		return ResponseEntity.ok("Bill Marked as paid");
	}

}
