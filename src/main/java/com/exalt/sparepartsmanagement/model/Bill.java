package com.exalt.sparepartsmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_oem")
    private String productOem;

    private String recipient;

    private double price;

    private int quantity;

    private double total;

    @Column(name = "received_date")
    private LocalDate receivedDate;

    @ManyToOne
    @JoinColumn(name = "customer_id") // foreign Key
    @JsonIgnore
    private Customer customer;


    public Bill() {
        receivedDate = LocalDate.now().plusDays(1);
    }

    public Bill(String productName, String productOem, String recipient, int quantity) {
        this.productName = productName;
        this.productOem = productOem;
        this.recipient = recipient;
        this.quantity = quantity;
        receivedDate = LocalDate.now().plusDays(1);
    }


    public Bill(String productName, String productOem, String recipient, double price, int quantity) {
        this.productName = productName;
        this.productOem = productOem;
        this.recipient = recipient;
        this.price = price;
        this.quantity = quantity;
        this.total = price * quantity;
        this.receivedDate = LocalDate.now().plusDays(1);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductOem() {
        return productOem;
    }

    public void setProductOem(String productOem) {
        this.productOem = productOem;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDate getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(LocalDate receivedDate) {
        this.receivedDate = receivedDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Bill(id=" + this.getId() + ", ProductName=" + this.getProductName() + ", ProductOem=" + this.getProductOem() +
                ", recipient=" + this.getRecipient() + ", price=" + this.getPrice() + ", quantity=" + this.getQuantity() +
                ", total=" + this.getTotal() + ", ReceivedDate=" + this.getReceivedDate() + ")";
    }
}


