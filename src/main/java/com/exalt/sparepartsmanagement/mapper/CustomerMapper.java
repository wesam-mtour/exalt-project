package com.exalt.sparepartsmanagement.mapper;

import com.exalt.sparepartsmanagement.dto.CustomerDTO;
import com.exalt.sparepartsmanagement.model.Customer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    List<CustomerDTO> customersToDTOs(List<Customer> customers);
    CustomerDTO customerToDTO(Customer customer);
    Customer DTOToCustomer(CustomerDTO customerDTO);

}
