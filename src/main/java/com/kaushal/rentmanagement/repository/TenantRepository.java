package com.kaushal.rentmanagement.repository;

import com.kaushal.rentmanagement.entity.Flat;
import com.kaushal.rentmanagement.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;

public interface TenantRepository extends JpaRepository<Tenant, Long> {

    @Query("""
SELECT t
FROM Tenant t
WHERE t.flat = :flat
AND t.joiningDate <= :joiningDate
AND (t.leavingDate IS NULL OR t.leavingDate >= :joiningDate)
""")
    Optional<Tenant> findActiveTenant(
            Flat flat,
            LocalDate joiningDate
    );

    @Query("""
            SELECT t
            FROM Tenant t
            WHERE t.flat = :flat
            AND t.joiningDate <= :billingDate
            AND (t.leavingDate IS NULL OR t.leavingDate >= :billingDate)
            """)
    Optional<Tenant> findBillingTenant(
            Flat flat,
            LocalDate billingDate
    );

}