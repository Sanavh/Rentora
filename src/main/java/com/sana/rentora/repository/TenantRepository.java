package com.sana.rentora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sana.rentora.entity.Tenant;

@Repository
public interface TenantRepository extends JpaRepository<Tenant,Long>{
}
