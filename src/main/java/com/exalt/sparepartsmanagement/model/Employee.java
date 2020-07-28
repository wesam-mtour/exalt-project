package com.exalt.sparepartsmanagement.model;

import com.exalt.sparepartsmanagement.phoneValidation.PhoneValidator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@NoArgsConstructor
@Entity
public class Employee {
    @Id
    /*
    GenerationType.IDENTITY This special type column is populated internally by
     the table itself without using a separate "sequence table"    .
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    @NotNull
    @Pattern(regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", message = "Email be valid")
    private String email;

    @NotNull
    private double salary;

    @NotNull
    private String password;

    @NotNull
    @PhoneValidator
    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    /*
    foreign Keys and column reference  for each key "id"
     */
    @JoinTable(name = "employee_role", joinColumns = @JoinColumn(name = "emp_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    /*
    @JsonIgnoreProperties is used to tell Jackson to ignore the field "employees" of a Java object.
    The property is ignored both when reading JSON into Java objects, and when writing Java objects into JSON
     */
    @JsonIgnoreProperties("employees")
    private List<Role> roles;


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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Employee(id=" + this.getId() + ", name=" + this.getName() + ", email=" + this.getEmail() +
                ", phoneNumber=" + this.getPhoneNumber() + ")";
    }
}
