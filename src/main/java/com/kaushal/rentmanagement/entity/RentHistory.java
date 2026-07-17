package com.kaushal.rentmanagement.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "rent_history")
public class RentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double rentAmount;

    private LocalDate effectiveFrom;

    public RentHistory() {
    }

    public Long getId() {
        return id;
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

    public void setId(Long id) {
        this.id = id;
    }

}