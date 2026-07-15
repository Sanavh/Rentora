package com.sana.rentora.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sana.rentora.entity.TenantDocument;

@Repository
public interface TenantDocumentRepository extends JpaRepository<TenantDocument,Long>{
		List<TenantDocument> findByTenantId(Long tenantId);
}
