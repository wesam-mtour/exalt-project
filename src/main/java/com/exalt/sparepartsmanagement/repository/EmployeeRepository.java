package com.exalt.sparepartsmanagement.repository;

import com.exalt.sparepartsmanagement.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Employee findByName(String name);

    @Query(value = "select e.name from employee e where e.name=:name", nativeQuery = true)
    String findByNameNQ(String name);

    @Query(value = "select e.name from employee e where e.email=:email", nativeQuery = true)
    String findByEmailNQ(String email);

    @Query(value = "select e.name from employee e where e.phone_number=:phoneNumber", nativeQuery = true)
    String findByPhoneNumberNQ(String phoneNumber);

    /*
    This queries used only in security session
     */
    @Query(value = "select * from employee e where e.name=:name", nativeQuery = true)
    Employee findUserByNameNQ(String name);

    @Transactional
    @Modifying
    @Query(value = "SELECT r.name FROM employee e JOIN employee_role er join role r WHERE e.name=:name AND e.id=er.emp_id AND er.role_id=r.id AND e.id=er.emp_id AND er.role_id=r.id", nativeQuery = true)
    List<String> findRolesByNameNQ(String name);

}
