package com.sana.rentora.serviceImpl;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sana.rentora.RentoraApplication;
import com.sana.rentora.dto.TenantDTO;
import com.sana.rentora.dto.TenantDocumentDTO;
import com.sana.rentora.entity.Room;
import com.sana.rentora.entity.Tenant;
import com.sana.rentora.entity.TenantDocument;
import com.sana.rentora.enums.RoomStatusEnum;
import com.sana.rentora.mapper.TenantDocumentMapper;
import com.sana.rentora.mapper.TenantMapper;
import com.sana.rentora.repository.RoomRepository;
import com.sana.rentora.repository.TenantDocumentRepository;
import com.sana.rentora.repository.TenantRepository;
import com.sana.rentora.service.TenantService;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

//service works with entity--> controller with dto
@Service
public class TenantServiceImpl implements TenantService {

    private final RentoraApplication rentoraApplication;
	
	private final TenantRepository tenantRepo;
	private final RoomRepository roomRepo;
	private final FileStorageService fileStore;
	private final TenantDocumentRepository tenantDocRepo;
	private static final Logger logger = LoggerFactory.getLogger(TenantServiceImpl.class);
	
	public TenantServiceImpl(TenantRepository tenantRepo,RoomRepository roomRepo, RentoraApplication rentoraApplication,TenantDocumentRepository tenantDocRepo,FileStorageService fileStore) {
		this.tenantRepo = tenantRepo;
		this.roomRepo = roomRepo;
		this.rentoraApplication = rentoraApplication;
		this.fileStore = fileStore;
		this.tenantDocRepo = tenantDocRepo;
	}

	@Override
	public TenantDTO saveTenant(TenantDTO tenantDto) {
		logger.info("In saveTenant method");
		Tenant tenant = TenantMapper.toEntity(tenantDto);
		
//		if(tenantDto.getRoomId() != null) {
//			Room room = roomRepo.findById(tenantDto.getRoomId()).orElseThrow(()->new RuntimeException("Room id not found for this: " + tenantDto.getRoomId()));
//			
//			if(RoomStatusEnum.OCCUPIED.equals(room.getStatus())) {
//				throw new RuntimeException("Room already Occupied");
//			}
//			
//			tenant.setRoom(room);
//			room.setStatus(RoomStatusEnum.OCCUPIED);
//			roomRepo.save(room);
//		}
		Tenant savedTenant = tenantRepo.save(tenant);
		return TenantMapper.toDTO(savedTenant);
	}

	@Override
	public List<TenantDTO> getAllTenants() {
		return tenantRepo.findAll()
				.stream()
				.map(TenantMapper::toDTO)
				.toList();
	}

	@Override
	public TenantDTO getTenantById(Long id) {
		Tenant tenant = tenantRepo.findById(id).orElseThrow(() -> new RuntimeException("Tenant not found with id: " + id));
		return TenantMapper.toDTO(tenant);
	}

	@Override
	public TenantDTO updateTenant(Long id, TenantDTO tenDto) {
		
		Tenant existingTenant = TenantMapper.toEntity(tenDto);
		
		existingTenant.setEmail(tenDto.getEmail());
		existingTenant.setGender(tenDto.getGender());
		existingTenant.setName(tenDto.getName());
		existingTenant.setPhone(tenDto.getPhone());
		
		Tenant updatedTenant =  tenantRepo.save(existingTenant);
		
		return TenantMapper.toDTO(updatedTenant);
	}

	@Override
	public void deleteTenant(Long id) {
		Tenant tenant = tenantRepo.findById(id).orElseThrow(()-> new RuntimeException("\"Tenant not found with id:{}" + id));
		
//		Room room = tenant.getRoom();
//		if(room != null) {
//		room.setStatus(RoomStatusEnum.AVAILABLE);
//		roomRepo.save(room);
//		}
		tenantRepo.delete(tenant);
	}

	@Override
	public TenantDTO assignRoom(Long tenantId, Long roomId) {
		
		Tenant tenant = tenantRepo.findById(tenantId).orElseThrow(() -> new RuntimeException("Tenant id not found for :"+ tenantId));
		
		Room room = roomRepo.findById(roomId).orElseThrow(() -> new RuntimeException("Room id not found for :" + roomId));
		
		if(room.getStatus().equals("OCCUPIED")) {
			throw new RuntimeException("Room with id : "+ roomId +"already occupied");
		}
		
//		tenant.setRoom(room);
//		room.setStatus(RoomStatusEnum.AVAILABLE);
		roomRepo.save(room);
		
		Tenant newTenant = tenantRepo.save(tenant);
		return TenantMapper.toDTO(newTenant);
		
	}

	@Override
	public TenantDTO removeRoom(Long tenantId) {
		Tenant tenant = tenantRepo.findById(tenantId).orElseThrow(()->new RuntimeException("Tenant not found with id: " + tenantId));
		
//		Room room = tenant.getRoom();
//		
//		if(room != null) {
//			room.setStatus(RoomStatusEnum.AVAILABLE);
//			roomRepo.save(room);
//		}
//		
//		tenant.setRoom(null);
		
		Tenant updatedTenant = tenantRepo.save(tenant);
		return TenantMapper.toDTO(updatedTenant);
	}

	@Override
	public String uploadTenantDoc(Long id, MultipartFile file, String type) {
		
		Tenant tenant = tenantRepo.findById(id).orElseThrow(()->new RuntimeException("Tenant not found for the id : " + id));
		
		String path = fileStore.saveFile(file, "/tenants/" + id);
		
		TenantDocument doc = new TenantDocument();
		doc.setDocumentType(type);
		doc.setFileName(UUID.randomUUID() + "_" +
		        file.getOriginalFilename().replaceAll("[^a-zA-Z0-9._-]", "_"));
		doc.setFilePath(path);
		doc.setTenant(tenant);
		
		tenantDocRepo.save(doc);
		
		return "File Uploaded Successfully";
	}

	@Override
	public List<TenantDocumentDTO> getAllTenantDocs(Long id) {
		
		tenantRepo.findById(id).orElseThrow(()->new RuntimeException("Tenant not found with id : " + id));
		
		return tenantDocRepo.findByTenantId(id)
				.stream()
				.map(TenantDocumentMapper::toDTO)
				.toList();
		
	}

	@Override
	public Resource downloadTenantDocument(Long tenantDocId) {

		TenantDocument doc = tenantDocRepo.findById(tenantDocId).
				orElseThrow(()->new RuntimeException("Tenant Document with specified id : " + tenantDocId + "not found"));
		try {
			
			Path path = Paths.get(doc.getFilePath());
			return new UrlResource(path.toUri());
					
		}catch(Exception e) {
			throw new RuntimeException("File download failed");
		}
		
	}

}
