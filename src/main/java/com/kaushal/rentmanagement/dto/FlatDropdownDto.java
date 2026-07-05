package com.kaushal.rentmanagement.dto;

public class FlatDropdownDto {

    private Long id;

    private String flatNumber;

    public FlatDropdownDto() {
    }

    public FlatDropdownDto(Long id, String flatNumber) {
        this.id = id;
        this.flatNumber = flatNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

}