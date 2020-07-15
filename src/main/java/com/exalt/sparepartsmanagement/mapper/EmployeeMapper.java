package com.exalt.sparepartsmanagement.mapper;


import com.exalt.sparepartsmanagement.dto.EmployeeDTO;
import com.exalt.sparepartsmanagement.dto.RoleDTO;
import com.exalt.sparepartsmanagement.model.Employee;
import com.exalt.sparepartsmanagement.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    @Mapping(source = "rolesDTOS",target = "roles")

    Employee DTOToEmployee(EmployeeDTO employeeDTO);


    List<EmployeeDTO> EmployeesToDTOS(List<Employee> employee);

    @Mapping(source = "roles",target = "rolesDTOS")
    EmployeeDTO EmployeeToDTO(Employee employee);

    default RoleDTO roleToDto(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setName(role.getName());
        return roleDTO;
    }

    default Role dtoToRole(RoleDTO roleDTO) {
        Role role = new Role();
        role.setName(roleDTO.getName());
        return role;
    }

}
