package com.exalt.sparepartsmanagement.service;


import com.exalt.sparepartsmanagement.dto.EmployeeDTO;
import com.exalt.sparepartsmanagement.model.Employee;

import java.util.List;


public interface EmployeeService {

    List<EmployeeDTO> getAll(int page , int pageSize);

    EmployeeDTO get(String name);

    void save(EmployeeDTO employeeDTO);

    void update(String name, EmployeeDTO employeeDTO);

    void delete(String name);
}
