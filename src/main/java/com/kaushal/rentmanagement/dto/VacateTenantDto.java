package com.kaushal.rentmanagement.dto;

import java.time.LocalDate;

public class VacateTenantDto {

    private LocalDate leavingDate;

    public VacateTenantDto() {
    }

    public LocalDate getLeavingDate() {
        return leavingDate;
    }

    public void setLeavingDate(LocalDate leavingDate) {
        this.leavingDate = leavingDate;
    }
}