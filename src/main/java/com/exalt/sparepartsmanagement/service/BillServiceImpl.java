package com.exalt.sparepartsmanagement.service;


import com.exalt.sparepartsmanagement.dto.PostBillDTO;
import com.exalt.sparepartsmanagement.error.NotFoundExceptions;
import com.exalt.sparepartsmanagement.model.Bill;
import com.exalt.sparepartsmanagement.model.Customer;
import com.exalt.sparepartsmanagement.model.Product;
import com.exalt.sparepartsmanagement.repository.BillRepository;
import com.exalt.sparepartsmanagement.repository.CustomerRepository;
import com.exalt.sparepartsmanagement.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillRepository billRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Bill> getAll(int page, int pageSize) {
        if (page < 1) {
            throw new NotFoundExceptions("Invalid page number");
        }
        if (pageSize < 1) {
            throw new NotFoundExceptions("Invalid page size");
        }
        Pageable paging = PageRequest.of(page - 1, pageSize);
        Page<Bill> pagedResult = billRepository.findAll(paging);
        return pagedResult.toList();
    }

    public List<Bill> getBills(String customerName) {
        Customer customer = customerRepository.findByName(customerName);
        if (customer != null) {
            return billRepository.getBillsByName(customerName);

        } else {
            throw new NotFoundExceptions("Customer not found");
        }

    }

    @Override
    public List<Bill> getBillsByDate(String customerName, String date) {
        Customer customer = customerRepository.findByName(customerName);
        if (customer != null) {
            return billRepository.getBillsOnDate(customerName, date);

        } else {
            throw new NotFoundExceptions("Customer not found");
        }
    }

    @Override
    public List<Bill> getBillsInMonth(String customerName, int month, int year) {
        Customer customer = customerRepository.findByName(customerName);
        if (customer != null) {
            return billRepository.getBillsInMonth(customerName, month, year);

        } else {
            throw new NotFoundExceptions("Customer not found");
        }
    }

    @Override
    public void save(List<PostBillDTO> postBillDTOS, String customerName) {
        Customer customer = customerRepository.findByName(customerName);
        if (customer != null) {
            List<Bill> myCustomerBills = billRepository.getBillsByName(customerName);
            for (PostBillDTO bill : postBillDTOS) {
                Product product = productRepository.findByOem(bill.getProductOem());
                if (product != null) {
                    if (bill.getQuantity() <= product.getQuantity()) {
                        Bill newBill = new Bill(product.getName(), product.getOem(), bill.getRecipient(),
                                product.getSellingPrice(), bill.getQuantity());
                    /*
                     assigning the foreign key "customer_id" to new bill
                    */
                        newBill.setCustomer(customer);
                        myCustomerBills.add(newBill);
                        billRepository.saveAll(myCustomerBills);
                        product.setQuantity(product.getQuantity() - bill.getQuantity());
                        productRepository.save(product);
                    } else {
                        throw new NotFoundExceptions(String.format("product with OEM  (%s) has maximum quantity ( %d ) ",
                                bill.getProductOem(), product.getQuantity()));

                    }
                } else {
                    throw new NotFoundExceptions(String.format("product with OEM  (%s) not found ", bill.getProductOem()));
                }
            }
        } else {
            throw new NotFoundExceptions("customer not found");
        }

    }

    @Override
    public void delete(String customerName) {

    }

}
