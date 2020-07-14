package com.exalt.sparepartsmanagement.controller;


import com.exalt.sparepartsmanagement.dto.PostBillDTO;
import com.exalt.sparepartsmanagement.service.BillService;
import com.exalt.sparepartsmanagement.model.Bill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class BillController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private BillService billService;

    @GetMapping(value = "/api/v1/bills", params = {"page", "pageSize"})
    public List<Bill> getAllBills(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
        logger.info("Bill controller method -getAllBills");
        return billService.getAll(page, pageSize);
    }

    @GetMapping(value = "/api/v1/bills/customer/{customerName}")
    public List<Bill> getCustomerBills(@PathVariable String customerName) {
        logger.info("Bill controller method -getCustomerBills");
        return billService.getBills(customerName);
    }

    @GetMapping(value = "/api/v1/bills", params = {"customerName", "date"})
    public List<Bill> getCustomerBillsByDate(@RequestParam("customerName") String customerName,
                                             @RequestParam("date") String date) {
        logger.info(date);
        return billService.getBillsByDate(customerName, date);
    }

    @GetMapping(value = "/api/v1/bills", params = {"customerName", "month", "year"})
    public List<Bill> getCustomerBillsInMonth(@RequestParam("customerName") String customerName,
                                              @RequestParam("month") int month, @RequestParam("year") int year) {
        return billService.getBillsInMonth(customerName, month, year);
    }

    @PostMapping(value = "/api/v1/bills/customer/{customerName}")
    public String createNewBill(@RequestBody List<PostBillDTO> postBillDTOS, @PathVariable String customerName) {
        logger.info("Bill controller method -createNewBill");
        billService.save(postBillDTOS, customerName);
        return "Bill added successfully";
    }

    @DeleteMapping(value = "/api/v1/bills")
    public String deleteBill(@PathVariable String name) {
        logger.info("Bill controller method -deleteBill");
        billService.delete(name);
        return "Bill deleted successfully";
    }
}
