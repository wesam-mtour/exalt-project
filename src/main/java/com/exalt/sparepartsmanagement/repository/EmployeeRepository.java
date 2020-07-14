package com.exalt.sparepartsmanagement.repository;

import com.exalt.sparepartsmanagement.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Employee findByName(String name);

    @Query(value = "select e.name from employee e where e.name=:name", nativeQuery = true)
    String findByNameNQ(String name);

    @Query(value = "select e.name from employee e where e.email=:email", nativeQuery = true)
    String findByEmailNQ(String email);

    @Query(value = "select e.name from employee e where e.phone_number=:phoneNumber", nativeQuery = true)
    String findByPhoneNumberNQ(String phoneNumber);





}
