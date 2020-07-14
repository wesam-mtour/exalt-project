package com.exalt.sparepartsmanagement.dto;

import com.exalt.sparepartsmanagement.model.Bill;
import com.exalt.sparepartsmanagement.phoneValidation.PhoneValidator;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotNull;
import java.util.List;

public class CustomerDTO {
    @NotNull
    private String name;

    @NotNull
    @PhoneValidator
    private String phoneNumber;
    /*
    annotation @JsonIgnore is used to tell Jackson to ignore a certain property (field) of a Java object.
    The property is ignored both when reading JSON into Java objects, and when writing Java objects into JSON
     */
    @JsonIgnore
    private List<Bill> bills;

    public CustomerDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }
}
