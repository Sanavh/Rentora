package com.sana.rentora.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sana.rentora.entity.ElectricityBill;

@Repository
public interface ElectricityBillRepository extends JpaRepository<ElectricityBill,Long> {
	Optional<ElectricityBill> findByTenantId(Long tenantId);
}
