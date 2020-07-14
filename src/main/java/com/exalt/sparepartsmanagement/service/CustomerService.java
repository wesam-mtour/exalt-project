package com.exalt.sparepartsmanagement.service;

import com.exalt.sparepartsmanagement.dto.CustomerDTO;
import com.exalt.sparepartsmanagement.model.Customer;


import java.util.List;

public interface CustomerService {
    List<CustomerDTO> getAll(int page , int pageSize);

    CustomerDTO get(String name);

    void save(CustomerDTO customerDTO);

    void delete(String name);

    void update(String name, CustomerDTO customerDTO);
}
