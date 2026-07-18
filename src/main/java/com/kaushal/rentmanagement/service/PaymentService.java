package com.kaushal.rentmanagement.service;

import com.kaushal.rentmanagement.dto.CollectPaymentDto;
import com.kaushal.rentmanagement.entity.Payment;
import com.kaushal.rentmanagement.entity.Tenant;
import com.kaushal.rentmanagement.repository.PaymentRepository;
import com.kaushal.rentmanagement.repository.TenantRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final TenantRepository tenantRepository;
    private final RentService rentService;

    public PaymentService(
            PaymentRepository paymentRepository,
            TenantRepository tenantRepository,
            RentService rentService
    ) {
        this.paymentRepository = paymentRepository;
        this.tenantRepository = tenantRepository;
        this.rentService = rentService;
    }

    public void collectPayment(CollectPaymentDto dto) {

        Tenant tenant = tenantRepository.findById(dto.getTenantId())
                .orElseThrow(() -> new RuntimeException("Tenant not found"));

        Payment payment = paymentRepository
                .findByTenantAndBillingMonthAndBillingYear(
                        tenant,
                        dto.getBillingMonth(),
                        dto.getBillingYear()
                )
                .orElse(new Payment());

        LocalDate billingDate = LocalDate.of(
                dto.getBillingYear(),
                dto.getBillingMonth(),
                1
        );

        Double rent = rentService.getRentForDate(billingDate);

        payment.setTenant(tenant);
        payment.setBillingMonth(dto.getBillingMonth());
        payment.setBillingYear(dto.getBillingYear());

        // Historical snapshot
        payment.setRentAmount(rent);

        payment.setPaidAmount(dto.getPaidAmount());
        payment.setPaymentDate(dto.getPaymentDate());
        payment.setPaymentMode(dto.getPaymentMode());
        payment.setRemarks(dto.getRemarks());

        paymentRepository.save(payment);

    }

}