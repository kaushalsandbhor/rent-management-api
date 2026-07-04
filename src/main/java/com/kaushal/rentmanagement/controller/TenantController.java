package com.kaushal.rentmanagement.controller;

import com.kaushal.rentmanagement.dto.*;
import com.kaushal.rentmanagement.service.TenantService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tenants")
@CrossOrigin(origins = "http://localhost:4200")
public class TenantController {

    private final TenantService tenantService;

    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @PostMapping
    public ApiResponse assignTenant(@RequestBody CreateTenantDto dto) {

        tenantService.assignTenant(dto);

        return new ApiResponse(
                true,
                "Tenant assigned successfully."
        );

    }

    @PutMapping("/{id}")
    public ApiResponse updateTenant(

            @PathVariable Long id,

            @RequestBody UpdateTenantDto dto

    ) {

        tenantService.updateTenant(id, dto);

        return new ApiResponse(
                true,
                "Tenant updated successfully."
        );

    }

    @PutMapping("/{id}/vacate")
    public ApiResponse vacateTenant(

            @PathVariable Long id,

            @RequestBody VacateTenantDto dto

    ) {

        tenantService.vacateTenant(id, dto);

        return new ApiResponse(
                true,
                "Tenant vacated successfully."
        );

    }

    @GetMapping
    public List<TenantDto> getAllTenants() {

        return tenantService.getAllTenants();

    }

}