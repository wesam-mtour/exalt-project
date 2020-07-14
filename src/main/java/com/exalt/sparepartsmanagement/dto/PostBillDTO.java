package com.exalt.sparepartsmanagement.dto;

import com.exalt.sparepartsmanagement.model.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class PostBillDTO {

    private String productOem;

    private String recipient;

    private int quantity;

    @JsonIgnore
    private Customer customer;

    public PostBillDTO() {
    }

    public PostBillDTO( String productOem, String recipient, int quantity) {
        this.productOem = productOem;
        this.recipient = recipient;
        this.quantity = quantity;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
