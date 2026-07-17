package com.kaushal.rentmanagement.dto;

import java.time.LocalDate;

public class CurrentRentDto {

    private Double rentAmount;

    private LocalDate effectiveFrom;

    public CurrentRentDto() {
    }

    public CurrentRentDto(
            Double rentAmount,
            LocalDate effectiveFrom
    ) {
        this.rentAmount = rentAmount;
        this.effectiveFrom = effectiveFrom;
    }

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