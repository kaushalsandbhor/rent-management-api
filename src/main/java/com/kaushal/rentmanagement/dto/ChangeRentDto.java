package com.kaushal.rentmanagement.dto;

import java.time.LocalDate;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class ChangeRentDto {

    @NotNull
    @Min(1)
    private Double rentAmount;

    @NotNull
    private LocalDate effectiveFrom;

    public Double getRentAmount() {
        return rentAmount;
    }

    public void setRentAmount(Double rentAmount) {
        this.rentAmount = rentAmount;
    }

    public LocalDate getEffectiveFrom() {
        return effectiveFrom;
    }

    public void setEffectiveFrom(LocalDate effectiveFrom) {
        this.effectiveFrom = effectiveFrom;
    }

}