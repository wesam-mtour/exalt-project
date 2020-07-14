package com.exalt.sparepartsmanagement.tests;

import com.exalt.sparepartsmanagement.model.Employee;
import com.exalt.sparepartsmanagement.model.Role;
import com.exalt.sparepartsmanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class EmployeeServiceTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    public void testCreateEmployee() {
        Employee employee = new Employee();
        employee.setName("ali");
        employee.setEmail("@gmail");
        employee.setPhoneNumber("+970590001000");
        List<Role> roles = new ArrayList<Role>();
        roles.add(new Role("sales"));
        employee.setRoles(roles);
        employeeRepository.save(employee);
    }

    @Test
    public void testDeleteEmployee() {

        Employee employee = employeeRepository.findByName("ali");
        employeeRepository.delete(employee);
    }

    @Test
    public void testGetEmployee() {

        Employee employee = employeeRepository.findByName("ali");
        for (int i = 0; i < employee.getRoles().size(); ++i) {
            System.out.println(employee.getRoles().get(i));
        }
    }
}
