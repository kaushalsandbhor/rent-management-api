package com.kaushal.rentmanagement.service;

import com.kaushal.rentmanagement.dto.DashboardDto;
import com.kaushal.rentmanagement.entity.Flat;
import com.kaushal.rentmanagement.entity.Payment;
import com.kaushal.rentmanagement.entity.Tenant;
import com.kaushal.rentmanagement.repository.FlatRepository;
import com.kaushal.rentmanagement.repository.PaymentRepository;
import com.kaushal.rentmanagement.repository.TenantRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DashboardService {

    private final FlatRepository flatRepository;
    private final TenantRepository tenantRepository;
    private final PaymentRepository paymentRepository;

    public DashboardService(
            FlatRepository flatRepository,
            TenantRepository tenantRepository,
            PaymentRepository paymentRepository
    ) {
        this.flatRepository = flatRepository;
        this.tenantRepository = tenantRepository;
        this.paymentRepository = paymentRepository;
    }

    public List<DashboardDto> getDashboard(Integer month, Integer year) {

        LocalDate billingDate = LocalDate.of(year, month, 1);

        List<Flat> flats = flatRepository.findAll();

        List<DashboardDto> response = new ArrayList<>();

        for (Flat flat : flats) {

            DashboardDto dto = new DashboardDto();

            dto.setFlatId(flat.getId());
            dto.setFlatNumber(flat.getFlatNumber());

            Optional<Tenant> tenantOptional =
                    tenantRepository.findBillingTenant(flat, billingDate);

            if (tenantOptional.isPresent()) {

                Tenant tenant = tenantOptional.get();

                dto.setTenantId(tenant.getId());
                dto.setTenantName(tenant.getName());

                Optional<Payment> paymentOptional =
                        paymentRepository.findByTenantAndBillingMonthAndBillingYear(
                                tenant,
                                month,
                                year
                        );

                if (paymentOptional.isPresent()) {

                    Payment payment = paymentOptional.get();

                    dto.setRent(payment.getRentAmount());
                    dto.setPaid(payment.getPaidAmount());
                    dto.setBalance(
                            payment.getRentAmount() - payment.getPaidAmount()
                    );

                } else {

                    dto.setRent(flat.getCurrentMonthlyRent());
                    dto.setPaid(0.0);
                    dto.setBalance(flat.getCurrentMonthlyRent());

                }

            } else {

                dto.setTenantId(null);
                dto.setTenantName(null);
                dto.setRent(null);
                dto.setPaid(null);
                dto.setBalance(null);

            }

            // Billing period
            dto.setBillingMonth(month);
            dto.setBillingYear(year);

            response.add(dto);

        }

        return response;

    }

}