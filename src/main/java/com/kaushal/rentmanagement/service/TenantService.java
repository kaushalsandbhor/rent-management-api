package com.kaushal.rentmanagement.service;

import com.kaushal.rentmanagement.dto.CreateTenantDto;
import com.kaushal.rentmanagement.dto.TenantDto;
import com.kaushal.rentmanagement.dto.UpdateTenantDto;
import com.kaushal.rentmanagement.dto.VacateTenantDto;
import com.kaushal.rentmanagement.entity.Flat;
import com.kaushal.rentmanagement.entity.Tenant;
import com.kaushal.rentmanagement.repository.FlatRepository;
import com.kaushal.rentmanagement.repository.TenantRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TenantService {

    private final TenantRepository tenantRepository;
    private final FlatRepository flatRepository;

    public TenantService(
            TenantRepository tenantRepository,
            FlatRepository flatRepository
    ) {
        this.tenantRepository = tenantRepository;
        this.flatRepository = flatRepository;
    }

    public void assignTenant(CreateTenantDto dto) {

        Flat flat = flatRepository.findById(dto.getFlatId())
                .orElseThrow(() -> new RuntimeException("Flat not found"));

        // Prevent assigning a tenant to an already occupied flat
        if (tenantRepository.findBillingTenant(flat, dto.getJoiningDate()).isPresent()) {
            throw new RuntimeException("Flat is already occupied.");
        }

        Tenant tenant = new Tenant();

        tenant.setName(dto.getName());
        tenant.setPhone(dto.getPhone());
        tenant.setDeposit(dto.getDeposit());
        tenant.setJoiningDate(dto.getJoiningDate());
        tenant.setLeavingDate(null);
        tenant.setFlat(flat);

        tenantRepository.save(tenant);
    }

    public void updateTenant(Long id, UpdateTenantDto dto) {

        Tenant tenant = tenantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tenant not found"));

        tenant.setName(dto.getName());
        tenant.setPhone(dto.getPhone());
        tenant.setDeposit(dto.getDeposit());

        tenantRepository.save(tenant);

    }

    public void vacateTenant(Long tenantId, VacateTenantDto dto) {

        Tenant tenant = tenantRepository.findById(tenantId)
                .orElseThrow(() -> new RuntimeException("Tenant not found"));

        if (dto.getLeavingDate() == null) {
            throw new RuntimeException("Leaving date is required.");
        }

        if (dto.getLeavingDate().isBefore(tenant.getJoiningDate())) {
            throw new RuntimeException("Leaving date cannot be before joining date.");
        }

        tenant.setLeavingDate(dto.getLeavingDate());

        tenantRepository.save(tenant);

    }

    public List<TenantDto> getAllTenants() {

        List<Tenant> tenants = tenantRepository.findAll();

        List<TenantDto> response = new ArrayList<>();

        for (Tenant tenant : tenants) {

            TenantDto dto = new TenantDto();

            dto.setId(tenant.getId());
            dto.setName(tenant.getName());
            dto.setPhone(tenant.getPhone());
            dto.setDeposit(tenant.getDeposit());
            dto.setJoiningDate(tenant.getJoiningDate());
            dto.setLeavingDate(tenant.getLeavingDate());
            dto.setFlatNumber(tenant.getFlat().getFlatNumber());

            response.add(dto);

        }

        return response;

    }
}