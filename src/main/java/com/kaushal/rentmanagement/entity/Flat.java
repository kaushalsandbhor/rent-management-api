package com.kaushal.rentmanagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "flats")
public class Flat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String flatNumber;

    @Column(nullable = false)
    private Double currentMonthlyRent;

    public Long getId() {
        return id;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    public Double getCurrentMonthlyRent() {
        return currentMonthlyRent;
    }

    public void setCurrentMonthlyRent(Double currentMonthlyRent) {
        this.currentMonthlyRent = currentMonthlyRent;
    }
}