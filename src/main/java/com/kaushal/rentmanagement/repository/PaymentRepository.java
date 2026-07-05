package com.kaushal.rentmanagement.repository;

import com.kaushal.rentmanagement.entity.Payment;
import com.kaushal.rentmanagement.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByTenantAndBillingMonthAndBillingYear(
            Tenant tenant,
            Integer billingMonth,
            Integer billingYear
    );

    List<Payment> findByBillingMonthAndBillingYear(
            Integer billingMonth,
            Integer billingYear
    );

}