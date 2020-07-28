package com.exalt.sparepartsmanagement.service;


import com.exalt.sparepartsmanagement.dto.EmployeeDTO;
import com.exalt.sparepartsmanagement.dto.RoleDTO;
import com.exalt.sparepartsmanagement.error.exceptions.BadRequestExceptions;
import com.exalt.sparepartsmanagement.mapper.EmployeeMapper;
import com.exalt.sparepartsmanagement.mapper.RoleMapper;
import com.exalt.sparepartsmanagement.model.Role;
import com.exalt.sparepartsmanagement.repository.EmployeeRepository;
import com.exalt.sparepartsmanagement.error.exceptions.ConflictExceptions;
import com.exalt.sparepartsmanagement.error.exceptions.NotFoundExceptions;
import com.exalt.sparepartsmanagement.model.Employee;
import com.exalt.sparepartsmanagement.repository.RoleRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private RoleRepository roleRepository;
    /*
     * creating the INSTANCE of employeeMapper and roleMapper in order to use they mappers methods
     */
    private EmployeeMapper employeeMapper = Mappers.getMapper(EmployeeMapper.class);
    private RoleMapper roleMapper = Mappers.getMapper(RoleMapper.class);

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeDTO> getAll(int page, int pageSize) {
        if (page < 1) {
            throw new BadRequestExceptions("Invalid page number");
        }
        if (pageSize < 1) {
            throw new BadRequestExceptions("Invalid page size");
        }
        Pageable paging = PageRequest.of(page - 1, pageSize);
        Page<Employee> pagedResult = employeeRepository.findAll(paging);
        return employeeMapper.EmployeesToDTOS(pagedResult.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeDTO get(String name) {
        Employee employee = employeeRepository.findByName(name);
        if (employee != null) {
            EmployeeDTO employeeDTO = employeeMapper.EmployeeToDTO(employee);
           // employeeDTO.setRolesDTOS(roleMapper.RolesToDTOs(employee.getRoles()));
            return employeeDTO;
        } else {
            throw new NotFoundExceptions("employee Not Found");
        }
    }

    @Override
    @Transactional
    public EmployeeDTO save(EmployeeDTO employeeDTO) {

        String temp = employeeRepository.findByNameNQ(employeeDTO.getName());
        if (temp != null) {
            throw new ConflictExceptions(String.format("The name ( %s ) exists for another employee ", temp));
        }
        temp = employeeRepository.findByEmailNQ(employeeDTO.getEmail());
        if (temp != null) {
            throw new ConflictExceptions(String.format("This email owned by the employee ( %s ) ", temp));
        }
        temp = employeeRepository.findByPhoneNumberNQ(employeeDTO.getPhoneNumber());
        if (temp != null) {
            throw new ConflictExceptions(String.format("This phone number owned by the employee ( %s ) ", temp));
        }
        Role role = new Role();
        List<Role> roles = new ArrayList<Role>();
        for (RoleDTO roleDTO : employeeDTO.getRolesDTOS()) {
            role = roleRepository.findByName(roleDTO.getName());
            if (role != null) {
                roles.add(role);
            } else {
                throw new NotFoundExceptions(String.format("Role with name (%S) not found", roleDTO.getName()));
            }
        }
        Employee employee = employeeMapper.DTOToEmployee(employeeDTO);
        employee.setRoles(roles);
        employeeRepository.save(employee);
        return employeeDTO;
    }

    @Override
    @Transactional
    public void update(String name, EmployeeDTO employeeDTO) {
        Employee updatingEmployee = employeeRepository.findByName(name);
        /*
        check if the employee not present at all
         */
        if (updatingEmployee != null) {
            /*
             * check if the name, phone number and email remains the same then no conflict
             * if they are changed, but there should be no conflict with and existing name, phone number or email
             */
            String temp = employeeRepository.findByNameNQ(employeeDTO.getName());
            if (temp != null && (!temp.equals(updatingEmployee.getName()))) {
                throw new ConflictExceptions(String.format("The name ( %s ) exists for another employee ", temp));
            }
            temp = employeeRepository.findByEmailNQ(employeeDTO.getEmail());
            if (temp != null && (!temp.equals(updatingEmployee.getName()))) {
                throw new ConflictExceptions(String.format("This email owned by the employee ( %s ) ", temp));
            }
            temp = employeeRepository.findByPhoneNumberNQ(employeeDTO.getPhoneNumber());
            if (temp != null && (!temp.equals(updatingEmployee.getName()))) {
                throw new ConflictExceptions(String.format("This phone number owned by the employee ( %s ) ", temp));
            }
            /*
            updating employee..
             */
            updatingEmployee.setName(employeeDTO.getName());
            updatingEmployee.setEmail(employeeDTO.getEmail());
            updatingEmployee.setPhoneNumber(employeeDTO.getPhoneNumber());
            updatingEmployee.setSalary(employeeDTO.getSalary());
            updatingEmployee.setPassword(employeeDTO.getPassword());
            Role role = new Role();
            List<Role> roles = new ArrayList<Role>();
            for (RoleDTO roleDTO : employeeDTO.getRolesDTOS()) {
                role = roleRepository.findByName(roleDTO.getName());
                if (role != null) {
                    roles.add(role);
                } else {
                    throw new NotFoundExceptions(String.format("Role with name (%S) not found", roleDTO.getName()));
                }
            }
            updatingEmployee.getRoles().addAll(roles);
            employeeRepository.save(updatingEmployee);
        } else
            throw new NotFoundExceptions("Employee not found to updating ");

    }

    @Override
    @Transactional
    public void delete(String name) {
        Employee employee = employeeRepository.findByName(name);

        if (employee != null) {
            employeeRepository.delete(employee);
        } else
            throw new NotFoundExceptions("Employee not found to deleting ");
    }
}





