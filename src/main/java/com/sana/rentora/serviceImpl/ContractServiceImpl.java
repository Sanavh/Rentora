package com.sana.rentora.serviceImpl;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sana.rentora.dto.ContractDTO;
import com.sana.rentora.dto.ContractDocumentDTO;
import com.sana.rentora.entity.Contract;
import com.sana.rentora.entity.ContractDocument;
import com.sana.rentora.entity.Room;
import com.sana.rentora.entity.Tenant;
import com.sana.rentora.enums.ContractStatusEnum;
import com.sana.rentora.enums.RoomStatusEnum;
import com.sana.rentora.mapper.ContractDocumentMapper;
import com.sana.rentora.mapper.ContractMapper;
import com.sana.rentora.repository.ContractDocumentRepository;
import com.sana.rentora.repository.ContractRepository;
import com.sana.rentora.repository.RoomRepository;
import com.sana.rentora.repository.TenantRepository;
import com.sana.rentora.service.ContractService;

@Service
public class ContractServiceImpl implements ContractService {
	
	private final ContractRepository contRepo;
	private final TenantRepository tenRepo;
	private final RoomRepository roomRepo;
	private final ContractDocumentRepository contDocRepo;
	private final FileStorageService fileStore;
	
	public ContractServiceImpl(ContractRepository contRepo,TenantRepository tenRepo,RoomRepository roomRepo, FileStorageService fileStore, ContractDocumentRepository contDocRepo) {
		this.contRepo=contRepo;
		this.tenRepo = tenRepo;
		this.roomRepo = roomRepo;
		this.contDocRepo = contDocRepo;
		this.fileStore = fileStore;
	}

	@Override
	public List<ContractDTO> getAllContracts() {
		return contRepo.findAll()
				.stream()
				.map(ContractMapper::toDTO)
				.toList();
				
	}

	@Override
	public ContractDTO getContractById(Long id) {
		Contract cont = contRepo.findById(id).orElseThrow(()->new RuntimeException("Contract not found with the id: " + id));
		return ContractMapper.toDTO(cont);
	}

	@Override
	public ContractDTO createContract(ContractDTO dto) {
		
		Tenant tenant = tenRepo.findById(dto.getTenantId()).orElseThrow(()->new RuntimeException("Tenant not found for the id: " + dto.getTenantId()));
		Room room = roomRepo.findById(dto.getRoomId()).orElseThrow(()->new RuntimeException("Room not found for the id: " + dto.getRoomId()));
		
		if(room.getStatus()==RoomStatusEnum.OCCUPIED) {
			throw new RuntimeException("room already occupied");
		}
		
		Contract contract = ContractMapper.toEntity(dto, tenant, room);
		
		if (room.getStatus() == RoomStatusEnum.OCCUPIED) {
		    throw new RuntimeException("Room already occupied");
		}
		room.setStatus(RoomStatusEnum.OCCUPIED);
		roomRepo.save(room);
		
		Contract savedContract = contRepo.save(contract);
		
		return ContractMapper.toDTO(savedContract);
	}

	@Override
	public ContractDTO updateContract(Long id, ContractDTO dto) {
		Contract existCont = contRepo.findById(id).orElseThrow(()->new RuntimeException("Contract not found with id "+ id));
		
		existCont.setEndDate(dto.getEndDate());
		existCont.setMonthlyRent(dto.getMonthlyRent());
		existCont.setSecurityDeposit(dto.getSecurityDeposit());
		existCont.setStartDate(dto.getStartDate());
		existCont.setStatus(dto.getStatus());
		
		Contract updatedCont = contRepo.save(existCont);
		return ContractMapper.toDTO(updatedCont);
	}

	@Override
	public void deleteContract(Long id) {
		Contract cont = contRepo.findById(id).orElseThrow(()->new RuntimeException("contract not found for id: " + id));
		
		Room room = cont.getRoom();
		
		if(room!=null) {
			room.setStatus(RoomStatusEnum.AVAILABLE);
			roomRepo.save(room);
		}
		
		cont.setStatus(ContractStatusEnum.EXPIRED);
		contRepo.save(cont);
	}

	@Override
	public ContractDTO closeContract(Long ContractId) {
		Contract contract = contRepo.findById(ContractId).orElseThrow(()->new RuntimeException("Contract id not found"));
		
		if(contract.getStatus() == ContractStatusEnum.TERMINATED) {
			throw new RuntimeException("Contract already closed");
		}
		
		contract.setStatus(ContractStatusEnum.TERMINATED);
		contract.setEndDate(LocalDate.now());
		
		Room room = contract.getRoom();
		room.setStatus(RoomStatusEnum.AVAILABLE);
		roomRepo.save(room);
		
		Contract updated = contRepo.save(contract);
		return ContractMapper.toDTO(updated);
	}

	@Override
	public String uploadContractDoc(Long id, MultipartFile file, String type) {
		
		Contract cont = contRepo.findById(id).orElseThrow(()->new RuntimeException("Contract not found" + id));
		
		String path = fileStore.saveFile(file, "/contract/" + id);
		
		ContractDocument doc = new ContractDocument();
		doc.setContract(cont);
		doc.setFileName(UUID.randomUUID() + "_" +
		        file.getOriginalFilename().replaceAll("[^a-zA-Z0-9._-]", "_"));
		doc.setFilePath(path);

		contDocRepo.save(doc);
		
		return "File Uploaded Successfully";
	}

	@Override
	public List<ContractDocumentDTO> getAllContracts(Long contractId) {
		
		contRepo.findById(contractId).orElseThrow(()->new RuntimeException("Contract id not found : " + contractId));
		return contDocRepo.findByContractId(contractId)
				.stream()
				.map(ContractDocumentMapper::toDTO)
				.toList();
				
	}

	@Override
	public Resource downloadContractDocument(Long contractDocId) {

		ContractDocument doc = new ContractDocument();
		doc = contDocRepo.findById(contractDocId).orElseThrow(()->new RuntimeException("Contract document id not found " + contractDocId));
		
		try {
			Path path = Paths.get(doc.getFilePath());
			return new UrlResource(path.toUri());
		}catch(Exception e) {
			throw new RuntimeException("File download failed" + e);
		}
		
	}

}
