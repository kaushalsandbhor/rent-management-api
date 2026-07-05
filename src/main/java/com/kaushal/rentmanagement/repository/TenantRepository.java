package com.kaushal.rentmanagement.repository;

import com.kaushal.rentmanagement.entity.Flat;
import com.kaushal.rentmanagement.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TenantRepository extends JpaRepository<Tenant, Long> {

    @Query("""
            SELECT t
            FROM Tenant t
            WHERE t.flat = :flat
            AND t.joiningDate <= :billingDate
            AND (
                t.leavingDate IS NULL
                OR t.leavingDate >= :billingDate
            )
            """)
    Optional<Tenant> findBillingTenant(
            @Param("flat") Flat flat,
            @Param("billingDate") LocalDate billingDate
    );

    @Query("""
            SELECT t
            FROM Tenant t
            WHERE t.joiningDate <= :billingDate
            AND (
                t.leavingDate IS NULL
                OR t.leavingDate >= :billingDate
            )
            """)
    List<Tenant> findBillingTenants(
            @Param("billingDate") LocalDate billingDate
    );

}