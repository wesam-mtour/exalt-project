//package com.exalt.sparepartsmanagement.tests;
//
//import com.exalt.sparepartsmanagement.model.Bill;
//import com.exalt.sparepartsmanagement.model.Customer;
//import com.exalt.sparepartsmanagement.repository.CustomerRepository;
//import com.exalt.sparepartsmanagement.repository.ProductRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//@SpringBootTest
//public class CustomerServiceTest {
//    @Autowired
//   private CustomerRepository customerRepository;
//    @Autowired
//    private ProductRepository productRepository;
//
//    // create new customer
//    @Test
//    public void testCreateCustomer() {
//        Customer customer = new Customer("wesam", "+970590001000");
//        customerRepository.save(customer);
//
//    }
//
//    // add new bill to specific customer
//    @Test
//    public void testCreateBill() {
//        Customer customer = customerRepository.findByName("wesam");
//        List<Bill> bills = new ArrayList<Bill>();
//        Bill bill = new Bill("brake", "1", "mohammad", 5);
//        bill.setPrice(productRepository.getProductSellingPriceNQ(bill.getProductOem()));
//        bill.setTotal(bill.getQuantity() * bill.getPrice());
//        bill.setCustomer(customer);
//        bills.add(bill);
//        customer.setBills(bills);
//        productRepository.setQuantityNQ(bill.getQuantity(), "22");//Reduce the quantity in product repository based on the  quantity in the created bill
//        customerRepository.save(customer);
//    }
//    @Test
//    public void testGetCustomer() {
//        List  <Bill> bills = customerRepository.findByName("wesam").getBills();
//        bills.forEach(bill -> System.out.println(bill.toString()));
//
//
//
//    }
//
//
//
//}
//
