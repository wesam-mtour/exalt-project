package com.exalt.sparepartsmanagement.model;

import com.exalt.sparepartsmanagement.phoneValidation.PhoneValidator;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    @NotNull
    @PhoneValidator
    @Column(name = "phone_number")
    private String phoneNumber;
    /*
    mapped the object declared in the second entity and fetching type is eager to load the customer with its own bills
    */
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    /*
    annotation @JsonIgnore is used to tell Jackson to ignore a certain property (field) of a Java object.
    The property is ignored both when reading JSON into Java objects, and when writing Java objects into JSON
     */
    @JsonIgnore
    private List<Bill> bills;

    public Customer() {
    }
    public Customer(@NotNull String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Override
    public String toString() {
        return "Customer(id=" + this.getId() + ", name=" + this.getName() + ", phoneNumber=" + this.getPhoneNumber() + ", bills=" + this.getBills() + ")";
    }
}

