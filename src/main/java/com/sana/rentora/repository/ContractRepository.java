package com.sana.rentora.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sana.rentora.entity.Contract;
import com.sana.rentora.entity.Room;
import com.sana.rentora.enums.ContractStatusEnum;

@Repository
public interface ContractRepository extends JpaRepository<Contract,Long>{

	Optional<Contract> findTopByRoomAndStatus(Room room, ContractStatusEnum status);
	
	List<Contract> findByRoomOrderByStartDateDesc(Room room);

}
