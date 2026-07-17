package com.kaushal.rentmanagement.dto;

import java.time.LocalDate;

public class RentHistoryDto {

    private Long id;
    private Double rentAmount;
    private LocalDate effectiveFrom;

    public RentHistoryDto() {
    }

    public RentHistoryDto(Long id,
                          Double rentAmount,
                          LocalDate effectiveFrom) {

        this.id = id;
        this.rentAmount = rentAmount;
        this.effectiveFrom = effectiveFrom;

    }

    public Long getId() {
        return id;
    }

    public Double getRentAmount() {
        return rentAmount;
    }

    public LocalDate getEffectiveFrom() {
        return effectiveFrom;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRentAmount(Double rentAmount) {
        this.rentAmount = rentAmount;
    }

    public void setEffectiveFrom(LocalDate effectiveFrom) {
        this.effectiveFrom = effectiveFrom;
    }

}