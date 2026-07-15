package com.sana.rentora.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.sana.rentora.dto.ContractHistoryDTO;
import com.sana.rentora.dto.RoomDTO;
import com.sana.rentora.dto.RoomDetailsDTO;
import com.sana.rentora.dto.RoomHistoryResponseDTO;
import com.sana.rentora.entity.Building;
import com.sana.rentora.entity.Room;
import com.sana.rentora.enums.ContractStatusEnum;
import com.sana.rentora.mapper.ContractMapper;
import com.sana.rentora.mapper.TenantMapper;
import com.sana.rentora.repository.ContractRepository;
import com.sana.rentora.repository.RoomRepository;
import com.sana.rentora.service.BuildingService;
import com.sana.rentora.entity.Contract;
import com.sana.rentora.service.RoomService;

@Service
public class RoomServiceImpl implements RoomService{
	
	private final RoomRepository roomRepo;
	private final BuildingService buildServ;
	private final ContractRepository contRepo;
	
	private static final Logger logger = LoggerFactory.getLogger(RoomServiceImpl.class); 
	
	public RoomServiceImpl(RoomRepository roomRepo, BuildingService buildServ, ContractRepository contRepo) {
		this.roomRepo = roomRepo;
		this.buildServ = buildServ;
		this.contRepo = contRepo;
	}
	
	
	@Override
	public Room saveRoom(Room room) {
		logger.info("Entered saveRoom method");
		
		Optional<Room> existing =
				roomRepo.findByRoomNumberAndFloorAndBuilding_Id(
						room.getRoomNumber(),
						room.getFloor(),
						room.getBuilding().getId());
		
		if(existing.isEmpty()) {
			Room saveRoom = roomRepo.save(room);
			logger.info("Room saved is: {}",saveRoom);
			return saveRoom;
		}else {
			throw new RuntimeException("Room already exists for this building, floor or room number");
		}
	}

	@Override
	public List<Room> getAllRooms() {
		logger.info("Entered getAllRooms method");
		List<Room> room = roomRepo.findAll();
		logger.info("List of Rooms fetched are: {}",room);
		return room;
	}

	@Override
	public Room getRoomById(Long id) {
		return roomRepo.findById(id).orElseThrow(()->new RuntimeException("Room not found with id: "+id));
	}

	@Override
	public void deleteRoom(Long id) {
		Room room = roomRepo.findById(id).orElseThrow(()->new RuntimeException("Room not found with this id {}"+ id));
		roomRepo.delete(room);
		
	}

	@Override
	public Room updateRoom(Long id, RoomDTO dto) {
		Room room = roomRepo.findById(id).orElseThrow(()->new RuntimeException("Room not found with id: {}" + id));
		room.setFloor(dto.getFloor());
		room.setRoomNumber(dto.getRoomNumber());
		room.setStatus(dto.getStatus());
		
		Building building = buildServ.getBuildingById(dto.getBuildingId());
		room.setBuilding(building);
		
		return roomRepo.save(room);
	}

	@Override
	public List<Room> getRoomsByStatus(String status) {
		return roomRepo.findByStatus(status);
	}


	@Override
	public List<Room> getRoomsByBuilding(Long buildingId) {
		return roomRepo.findByBuilding_Id(buildingId);
	}


	@Override
	public List<Room> getRoomsByFloor(int floor) {
		return roomRepo.findByFloor(floor);
	}


	@Override
	public Page<Room> getRoomsPaginated(int page, int size, String sortBy, String dir) {
		Sort sort = dir.equalsIgnoreCase("desc")
				? Sort.by(sortBy).descending()
				: Sort.by(sortBy).ascending();
		Pageable pageable = PageRequest.of(page, size, sort);
		return roomRepo.findAll(pageable);
	}


	@Override
	public Page<Room> searchRooms(String status, Integer floor, int page, int size, String sortBy, String dir) {
		Sort sort = dir.equalsIgnoreCase("desc")
				? Sort.by(sortBy).descending()
				: Sort.by(sortBy).ascending();
		Pageable pageable = PageRequest.of(page, size, sort);
		
		List<Room> filtered = roomRepo.findAll();
		
		return filtered.stream()
	            .filter(r -> status == null || status.equals(r.getStatus()))
	            .filter(r -> floor == null || floor.equals(r.getFloor()))
	            .skip(page * size)
	            .limit(size)
	            .map(r -> r)
	            .collect(Collectors.collectingAndThen(
	                    Collectors.toList(),
	                    list -> new PageImpl<>(list, pageable, filtered.size())
	            ));
	}


	@Override
	public RoomDetailsDTO getRoomDetails(Long id) {
		
		Room room = roomRepo.findById(id).orElseThrow(()->new RuntimeException("room not found for the id : " + id));
		
		Contract contract = contRepo.findTopByRoomAndStatus(room,ContractStatusEnum.ACTIVE)
				.orElse(null);
				
		RoomDetailsDTO detDto = new RoomDetailsDTO();
		detDto.setRoomId(room.getId());
		detDto.setFloor(room.getFloor());
		detDto.setRoomNumber(room.getRoomNumber());
		detDto.setStatus(room.getStatus().name());
		
		if(contract != null) {
			detDto.setContract(ContractMapper.toDTO(contract));
			detDto.setTenant(TenantMapper.toDTO(contract.getTenant()));
		}
		
		return detDto;
				
	}
	
	@Override
	public RoomHistoryResponseDTO getRoomHistory(Long id) {
		
		Room room = roomRepo.findById(id).orElseThrow(()->new RuntimeException("room not found with id : " + id));
		
		List<Contract> contracts = contRepo.findByRoomOrderByStartDateDesc(room);
		
		List<ContractHistoryDTO> historyList = contracts.stream()
				.map(ContractMapper::mapToHistoryDTO)
				.toList();
		
		RoomHistoryResponseDTO dto = new RoomHistoryResponseDTO();
		dto.history(historyList);
		dto.setRoomId(id);
		dto.setRoomNum(room.getRoomNumber());
		
		
		return null;
	}
	
	
}
