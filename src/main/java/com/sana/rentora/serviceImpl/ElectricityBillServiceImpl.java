package com.sana.rentora.serviceImpl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sana.rentora.dto.ElectricityBillDTO;
import com.sana.rentora.entity.ElectricityBill;
import com.sana.rentora.entity.Tenant;
import com.sana.rentora.enums.BillStatus;
import com.sana.rentora.repository.ElectricityBillRepository;
import com.sana.rentora.repository.TenantRepository;
import com.sana.rentora.service.ElectricityBillService;

@Service
public class ElectricityBillServiceImpl implements ElectricityBillService {
	
	private final ElectricityBillRepository tenPayRepo;
	private final TenantRepository tenRepo;
	
	@Value("${file.upload.dir}")
    private String uploadDir;
	
	public ElectricityBillServiceImpl(ElectricityBillRepository tenPayRepo,TenantRepository tenRepo) {
		this.tenPayRepo = tenPayRepo;
		this.tenRepo = tenRepo;
	}

	@Override
	public void initialPaymentForTenant(Long tenantId) {
		Tenant tenant = tenRepo.findById(tenantId).orElseThrow(()->new RuntimeException("Tenant not found for id: " + tenantId));
		
		ElectricityBill pay = new ElectricityBill();
		
		pay.setTenant(tenant);
		pay.setStatus(BillStatus.NOT_PAID);

		tenPayRepo.save(pay);
	}

	@Override
	public ElectricityBillDTO getPayment(Long tenantId) {
		ElectricityBill pay = tenPayRepo.findByTenantId(tenantId).orElseThrow(()->new RuntimeException("Payment not found for tenant " + tenantId));
		
		ElectricityBillDTO dto = new ElectricityBillDTO();
		dto.setId(pay.getId());
		dto.setLastPaidDate(pay.getLastPaidDate());
		dto.setProofFileName(pay.getProofFileName());
		dto.setStatus(pay.getStatus().name());
		
		return dto;
	}

	@Override
	public void markAsPaid(Long tenantId, MultipartFile file) {
		
		ElectricityBill pay = tenPayRepo.findByTenantId(tenantId).orElseThrow(()->new RuntimeException("Payment not found for tenant Id " +  tenantId));
		
		try {
			
			String fileName = file.getOriginalFilename();
			String folderPath = uploadDir + "/payments" + tenantId;
			
			File folder = new File(folderPath);
			if(!folder.exists()) {
				folder.mkdirs();
			}
			
			Path path = Paths.get(folderPath, fileName);
			Files.write(path, file.getBytes());
			
			pay.setProofFileName(fileName);
			pay.setProofFilePath(path.toString());
			pay.setStatus(BillStatus.PAID);
			pay.setLastPaidDate(LocalDate.now());
			
			tenPayRepo.save(pay);
		}catch(Exception e) {
			
			throw new RuntimeException("Payment update failed");
		}
		
	}
	

}
