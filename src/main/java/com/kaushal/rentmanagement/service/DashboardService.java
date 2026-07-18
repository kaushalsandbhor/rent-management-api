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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardService {

    private final FlatRepository flatRepository;
    private final TenantRepository tenantRepository;
    private final PaymentRepository paymentRepository;
    private final RentService rentService;

    public DashboardService(
            FlatRepository flatRepository,
            TenantRepository tenantRepository,
            PaymentRepository paymentRepository,
            RentService rentService
    ) {
        this.flatRepository = flatRepository;
        this.tenantRepository = tenantRepository;
        this.paymentRepository = paymentRepository;
        this.rentService = rentService;
    }

    public List<DashboardDto> getDashboard(Integer month, Integer year) {

        LocalDate billingDate = LocalDate.of(year, month, 1);

        List<Flat> flats = flatRepository.findAll();

        List<Tenant> tenants =
                tenantRepository.findBillingTenants(billingDate);

        List<Payment> payments =
                paymentRepository.findByBillingMonthAndBillingYear(
                        month,
                        year
                );

        Map<Long, Tenant> tenantMap = new HashMap<>();

        for (Tenant tenant : tenants) {
            tenantMap.put(
                    tenant.getFlat().getId(),
                    tenant
            );
        }

        Map<Long, Payment> paymentMap = new HashMap<>();

        for (Payment payment : payments) {
            paymentMap.put(
                    payment.getTenant().getId(),
                    payment
            );
        }

        List<DashboardDto> response = new ArrayList<>();

        for (Flat flat : flats) {

            DashboardDto dto = new DashboardDto();

            dto.setFlatId(flat.getId());
            dto.setFlatNumber(flat.getFlatNumber());

            Tenant tenant = tenantMap.get(flat.getId());

            if (tenant != null) {

                dto.setTenantId(tenant.getId());
                dto.setTenantName(tenant.getName());

                Double rent = rentService.getRentForDate(billingDate);

                Payment payment = paymentMap.get(tenant.getId());

                if (payment != null) {

                    dto.setRent(payment.getRentAmount());
                    dto.setPaid(payment.getPaidAmount());
                    dto.setBalance(
                            payment.getRentAmount() - payment.getPaidAmount()
                    );

                } else {

                    dto.setRent(rent);
                    dto.setPaid(0.0);
                    dto.setBalance(rent);

                }

            } else {

                dto.setTenantId(null);
                dto.setTenantName(null);
                dto.setRent(null);
                dto.setPaid(null);
                dto.setBalance(null);

            }

            dto.setBillingMonth(month);
            dto.setBillingYear(year);

            response.add(dto);

        }

        return response;

    }

}