package com.exalt.sparepartsmanagement.repository;

import com.exalt.sparepartsmanagement.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Integer> {
    @Query(value = "SELECT b.* FROM bill b JOIN customer c WHERE c.id=b.customer_id AND c.name=:customerName", nativeQuery = true)
    List<Bill> getBillsByName(@Param("customerName") String customerName);

    @Query(value = "SELECT * FROM bill b WHERE b.received_date=:date and b.customer_id  IN (SELECT id FROM customer c " +
            "WHERE c.name=:customerName)", nativeQuery = true)
    List<Bill> getBillsOnDate(@Param("customerName") String customerName, @Param("date") String date);

    @Query(value = "SELECT * FROM bill b WHERE MONTH(b.received_date)=:month AND YEAR(b.received_date)=:year" +
            " AND b.customer_id  IN (SELECT id FROM customer c WHERE c.name=:customerName);", nativeQuery = true)
    List<Bill> getBillsInMonth(@Param("customerName") String customerName, @Param("month") int month, @Param("year") int year);

}
