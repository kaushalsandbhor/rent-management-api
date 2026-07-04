package com.kaushal.rentmanagement.dto;

public class UpdateTenantDto {

    private String name;
    private String phone;
    private Double deposit;

    public UpdateTenantDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getDeposit() {
        return deposit;
    }

    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }
}