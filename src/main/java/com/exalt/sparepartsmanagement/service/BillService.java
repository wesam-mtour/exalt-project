package com.exalt.sparepartsmanagement.service;

import com.exalt.sparepartsmanagement.dto.PostBillDTO;
import com.exalt.sparepartsmanagement.model.Bill;

import java.util.List;

public interface BillService {
    List<Bill> getAll(int page , int pageSize);

    List<Bill> getBills(String customerName);

    List<Bill> getBillsByDate(String customerName, String date);

    List<Bill> getBillsInMonth(String customerName, int month, int year);

    void save(List<PostBillDTO> postBillDTOS, String customerName);

    void delete(String customerName);
}
