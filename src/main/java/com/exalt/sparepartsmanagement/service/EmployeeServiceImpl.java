package com.exalt.sparepartsmanagement.service;


import com.exalt.sparepartsmanagement.repository.EmployeeRepository;
import com.exalt.sparepartsmanagement.error.ConflictExceptions;
import com.exalt.sparepartsmanagement.error.NotFoundExceptions;
import com.exalt.sparepartsmanagement.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAll(int page , int pageSize) {
        if (page < 1){
            throw  new NotFoundExceptions("Invalid page number");
        }
        if (pageSize < 1){
            throw  new NotFoundExceptions("Invalid page size");
        }
        /**
         *
         */
        Pageable paging = PageRequest.of(page-1, pageSize);
        Page<Employee> pagedResult = employeeRepository.findAll(paging);
        return pagedResult.toList();
    }

    @Override
    public Employee get(String name) {

        Employee employee = employeeRepository.findByName(name);
        if (employee == null) {
            throw new NotFoundExceptions("employee Not Found");
        } else
            return employee;
    }

    @Override
    public void save(Employee employee) {

        String temp = employeeRepository.findByNameNQ(employee.getName());
        if (temp != null) {
            throw new ConflictExceptions(String.format("The name ( %s ) exists for another employee ", temp));
        }
        temp = employeeRepository.findByEmailNQ(employee.getEmail());
        if (temp != null) {
            throw new ConflictExceptions(String.format("This email owned by the employee ( %s ) ", temp));
        }
        temp = employeeRepository.findByPhoneNumberNQ(employee.getPhoneNumber());
        if (temp != null) {
            throw new ConflictExceptions(String.format("This phone number owned by the employee ( %s ) ", temp));
        }
        employeeRepository.save(employee);
    }

    @Override
    public void update(String name, Employee employee) {
        Employee updatingEmployee = employeeRepository.findByName(name);
        /*
        check if the employee not present at all
         */
        if (updatingEmployee != null) {
            /*
             * check if the name, phone number and email remains the same then no conflict
             * if they are changed, but there should be no conflict with and existing name, phone number or email
             */
            String temp = employeeRepository.findByNameNQ(employee.getName());
            if (temp != null && (!temp.equals(updatingEmployee.getName()))) {
                throw new ConflictExceptions(String.format("The name ( %s ) exists for another employee ", temp));
            }
            temp = employeeRepository.findByEmailNQ(employee.getEmail());
            if (temp != null && (!temp.equals(updatingEmployee.getName()))) {
                throw new ConflictExceptions(String.format("This email owned by the employee ( %s ) ", temp));
            }
            temp = employeeRepository.findByPhoneNumberNQ(employee.getPhoneNumber());
            if (temp != null && (!temp.equals(updatingEmployee.getName()))) {
                throw new ConflictExceptions(String.format("This phone number owned by the employee ( %s ) ", temp));
            }
            /*
            updating employee..
             */
            updatingEmployee.setName(employee.getName());
            updatingEmployee.setEmail(employee.getEmail());
            updatingEmployee.setPhoneNumber(employee.getPhoneNumber());
            updatingEmployee.setRoles(employee.getRoles());
            employeeRepository.save(updatingEmployee);
        } else
            throw new NotFoundExceptions("Employee not found to updating ");

    }

    @Override
    public void delete(String name) {
        Employee employee = employeeRepository.findByName(name);

        if (employee != null) {
            employeeRepository.delete(employee);
        } else
            throw new NotFoundExceptions("Employee not found to deleting ");
    }
}





