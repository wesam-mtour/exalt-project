package com.exalt.sparepartsmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;
    /*
    fetch type = EAGER, this enables to access each role and who's work on it
     */
    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    /*
    @JsonIgnoreProperties is used to tell Jackson to ignore the field "roles" of a Java object.
    The property is ignored both when reading JSON into Java objects, and when writing Java objects into JSON
     */
    @JsonIgnoreProperties("roles")
    private List<Employee> employees;

    public Role(@NotNull String name) {
        this.name = name;
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

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Role(id=" + this.getId() + ", name=" + this.getName() + ", employees=" + this.getEmployees() + ")";
    }
}
