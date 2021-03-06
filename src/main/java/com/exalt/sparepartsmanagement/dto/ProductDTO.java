package com.exalt.sparepartsmanagement.dto;
import javax.validation.constraints.NotNull;

public class ProductDTO {
    @NotNull
    private String oem;

    @NotNull
    private String name;

    private double sellingPrice;

    private double costPrice;

    private String carType;

    private int quantity;

    private String producers;

    public ProductDTO() {
    }

    public String getOem() {
        return oem;
    }

    public void setOem(String oem) {
        this.oem = oem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProducers() {
        return producers;
    }

    public void setProducers(String producers) {
        this.producers = producers;
    }

    @Override
    public String toString() {
        return "Product(oem=" + this.getOem() + ", name=" + this.getName() + ", sellingPrice=" + this.getSellingPrice() +
                ", costPrice=" + this.getCostPrice() + ", carType=" + this.getCarType() + ", quantity=" +
                this.getQuantity() + ", producers=" + this.getProducers() + ")";
    }
}
