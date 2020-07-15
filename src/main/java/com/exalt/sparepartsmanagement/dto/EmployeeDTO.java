package com.exalt.sparepartsmanagement.dto;

import com.exalt.sparepartsmanagement.model.Role;
import com.exalt.sparepartsmanagement.phoneValidation.PhoneValidator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

public class EmployeeDTO {

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private double salary;

    @NotNull
    @PhoneValidator
    @Column(name = "phone_number")
    private String phoneNumber;

    private List<RoleDTO> rolesDTOS;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<RoleDTO> getRolesDTOS() {
        return rolesDTOS;
    }

    public void setRolesDTOS(List<RoleDTO> rolesDTOS) {
        this.rolesDTOS = rolesDTOS;
    }
}
