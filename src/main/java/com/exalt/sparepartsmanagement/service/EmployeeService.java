package com.exalt.sparepartsmanagement.service;


import com.exalt.sparepartsmanagement.model.Employee;

import java.util.List;


public interface EmployeeService {

    List<Employee> getAll(int page , int pageSize);

    Employee get(String name);

    void save(Employee employee);

    void update(String name, Employee employee);

    void delete(String name);
}
