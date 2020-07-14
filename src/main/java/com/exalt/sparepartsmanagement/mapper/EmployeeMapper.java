package com.exalt.sparepartsmanagement.mapper;


import com.exalt.sparepartsmanagement.dto.EmployeeDTO;
import com.exalt.sparepartsmanagement.model.Employee;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee DTOToEmployee (EmployeeDTO employeeDTO);
    List<EmployeeDTO> EmployeesToDTOS(List<Employee> employee);
    EmployeeDTO EmployeeToDTO(Employee employee);

}
