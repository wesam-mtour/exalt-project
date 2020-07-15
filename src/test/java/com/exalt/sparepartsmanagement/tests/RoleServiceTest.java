//package com.exalt.sparepartsmanagement.tests;
//
//
//import com.exalt.sparepartsmanagement.model.Employee;
//import com.exalt.sparepartsmanagement.model.Role;
//import com.exalt.sparepartsmanagement.repository.RoleRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//@SpringBootTest
//public class RoleServiceTest {
//
//    @Autowired
//    RoleRepository roleRepository;
//
//    @Test
//    public void testCreate() {
//        Role role = new Role("F");
//        roleRepository.save(role);
//    }
//    @Test
//    public void testFindByName() {
//        List <Employee> employees = roleRepository.findByName("F").getEmployees();
//        employees.forEach(employee -> System.out.println(employee.toString()));
//    }
//    @Test
//    public void testDeleteRole() {
//        roleRepository.delete(roleRepository.findByName("B"));
//    }
//
//
//
//
//
//
//
//}
