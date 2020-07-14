package com.exalt.sparepartsmanagement.service;


import com.exalt.sparepartsmanagement.dto.CustomerDTO;
import com.exalt.sparepartsmanagement.mapper.CustomerMapper;
import com.exalt.sparepartsmanagement.repository.CustomerRepository;
import com.exalt.sparepartsmanagement.error.ConflictExceptions;
import com.exalt.sparepartsmanagement.error.NotFoundExceptions;
import com.exalt.sparepartsmanagement.model.Customer;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);


    public List<CustomerDTO> getAll(int page , int pageSize) {

        if (page < 1){
            throw  new NotFoundExceptions("Invalid page number");
        }
        if (pageSize < 1){
            throw  new NotFoundExceptions("Invalid page size number");
        }
        Pageable paging = PageRequest.of(page-1, pageSize);
        Page<Customer> pagedResult = customerRepository.findAll(paging);
        return customerMapper.customersToDTOs(pagedResult.toList());

    }

    public CustomerDTO get(String name) {
        Customer customer = customerRepository.findByName(name);
        if (customer != null) {
            return customerMapper.customerToDTO(customer);
        } else
            throw new NotFoundExceptions("customer not Found");
    }

    public void save(CustomerDTO customerDTO) {

        String temp = customerRepository.findByNameNQ(customerDTO.getName());
        if (temp != null) {
            throw new ConflictExceptions(String.format("The name ( %s ) exists by another customer ", temp));
        }

        temp = customerRepository.findByPhoneNumberNQ(customerDTO.getPhoneNumber());
        if (temp != null) {
            throw new ConflictExceptions(String.format("This phone number owned by the customer ( %s ) ", temp));
        }
        customerRepository.save(customerMapper.DTOToCustomer(customerDTO));
    }


    public void delete(String name) {
        Customer customer = customerRepository.findByName(name);
        if (customer != null) {
            customerRepository.delete(customer);
        } else {
            throw new NotFoundExceptions("customer not found to deleting ");
        }
    }

    public void update(String name, CustomerDTO customerDTO) {
        Customer updatingCustomer = customerRepository.findByName(name);
        /*
        check if the customer not present at all
         */
        if (updatingCustomer != null) {
            String temp = customerRepository.findByNameNQ(customerDTO.getName());
            /*
             * check if the name and phone number remains the same then no conflict
             * if they are changed, but there should be no conflict with and existing name or phone number
             */
            if (temp != null && (!temp.equals(updatingCustomer.getName()))) {
                throw new ConflictExceptions(String.format("The name ( %s ) exists for another customer ", temp));
            }
            temp = customerRepository.findByPhoneNumberNQ(customerDTO.getPhoneNumber());
            if (temp != null && (!temp.equals(updatingCustomer.getName()))) {
                throw new ConflictExceptions(String.format("This phone number owned by the customer ( %s ) ", temp));
            }
            /*
            updating customer..
             */
            updatingCustomer.setName(customerDTO.getName());
            updatingCustomer.setPhoneNumber(customerDTO.getPhoneNumber());
            updatingCustomer.setBills(customerDTO.getBills());
            customerRepository.save(updatingCustomer);
        } else {
            throw new NotFoundExceptions("customer not found to updating ");
        }
    }
}
