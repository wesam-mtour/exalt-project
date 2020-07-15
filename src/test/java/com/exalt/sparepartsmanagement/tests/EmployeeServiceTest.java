//package com.exalt.sparepartsmanagement.tests;
//
//import com.exalt.sparepartsmanagement.dto.EmployeeDTO;
//import com.exalt.sparepartsmanagement.model.Employee;
//import com.exalt.sparepartsmanagement.model.Role;
//import com.exalt.sparepartsmanagement.repository.EmployeeRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@SpringBootTest
//public class EmployeeServiceTest {
//
//    @Autowired
//    EmployeeRepository employeeRepository;
//    @BeforeEach
////    void createEmployee(){
////        EmployeeDTO employeeDTO =new EmployeeDTO();
////        employeeDTO.setEmail("email");
////        employeeDTO.setName("wesam");
////        employeeDTO.setPhoneNumber("+970590001001");
////
////        employeeDTO.setRolesDTOS();
////    }
//
//
//    @Test
//    public void testDeleteEmployee() {
//
//        Employee employee = employeeRepository.findByName("wesam");
//        employeeRepository.delete(employee);
//    }
//
//    @Test
//    public void testGetEmployee() {
//
//        Employee employee = employeeRepository.findByName("ali");
//        for (int i = 0; i < employee.getRoles().size(); ++i) {
//            System.out.println(employee.getRoles().get(i));
//        }
//    }
//}
