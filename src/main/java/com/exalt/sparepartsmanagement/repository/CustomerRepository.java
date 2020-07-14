package com.exalt.sparepartsmanagement.repository;

import com.exalt.sparepartsmanagement.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Customer findByName(String name);

    @Query(value = "select c.name from customer c where c.name=:name", nativeQuery = true)
    String findByNameNQ(String name);

    @Query(value = "select c.name from customer c where c.phone_number=:phoneNumber", nativeQuery = true)
    String findByPhoneNumberNQ(String phoneNumber);
}
