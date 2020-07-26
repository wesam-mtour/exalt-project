package com.exalt.sparepartsmanagement.controller;

import com.exalt.sparepartsmanagement.dto.CustomerDTO;
import com.exalt.sparepartsmanagement.model.Customer;
import com.exalt.sparepartsmanagement.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private CustomerService customerService;

    /*
    enable pagination in getAll method
     */
    @GetMapping(value = "/api/v1/customers", params = {"page","pageSize"})
    public List<CustomerDTO> getCustomers(@RequestParam("page") int page , @RequestParam("pageSize") int pageSize) {
        logger.info("Customer controller method -getCustomers");
        return customerService.getAll(page, pageSize);
    }

    @GetMapping(value = "/api/v1/customers/{name}")
    public CustomerDTO getCustomerByName(@PathVariable String name) {
        logger.info("Customer controller method -getCustomerByName");
        return customerService.get(name);
    }

    @PostMapping(value = "/api/v1/customers")
    public String createNewCustomer( @Valid @RequestBody CustomerDTO customerDTO) {
        logger.info("Customer controller method -createNewProduct");
        customerService.save(customerDTO);
        return "customer added successfully";
    }

    @PutMapping(value = "/api/v1/customers/{name}")
    public String updateCustomer(@PathVariable String name, @RequestBody CustomerDTO customerDTO) {
        logger.info("Customer controller method -updateCustomer");
        customerService.update(name, customerDTO);
        return "customer updated successfully ";
    }

    @DeleteMapping (value = "/api/v1/customers/{name}")
    public String deleteCustomer(@PathVariable String name) {
        logger.info("Customer controller method -deleteCustomer");
         customerService.delete(name);
        return " customer deleted successfully";
    }

}
