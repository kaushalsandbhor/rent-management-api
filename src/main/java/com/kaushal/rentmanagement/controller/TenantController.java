package com.kaushal.rentmanagement.controller;

import com.kaushal.rentmanagement.dto.CreateTenantDto;
import com.kaushal.rentmanagement.dto.UpdateTenantDto;
import com.kaushal.rentmanagement.dto.VacateTenantDto;
import com.kaushal.rentmanagement.service.TenantService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tenants")
@CrossOrigin(origins = "http://localhost:4200")
public class TenantController {

    private final TenantService tenantService;

    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @PostMapping
    public String assignTenant(@RequestBody CreateTenantDto dto) {

        tenantService.assignTenant(dto);

        return "Tenant assigned successfully.";

    }

    @PutMapping("/{id}")
    public String updateTenant(

            @PathVariable Long id,

            @RequestBody UpdateTenantDto dto

    ) {

        tenantService.updateTenant(id, dto);

        return "Tenant updated successfully.";

    }

    @PutMapping("/{id}/vacate")
    public String vacateTenant(

            @PathVariable Long id,

            @RequestBody VacateTenantDto dto

    ) {

        tenantService.vacateTenant(id, dto);

        return "Tenant vacated successfully.";

    }

}