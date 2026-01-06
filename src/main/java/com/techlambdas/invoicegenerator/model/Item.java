package com.techlambdas.invoicegenerator.model;

public class Item {

    private String itemName;
    private int quantity;
    private double rate;
    private double taxPercentage;

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getRate() { return rate; }
    public void setRate(double rate) { this.rate = rate; }

    public double getTaxPercentage() { return taxPercentage; }
    public void setTaxPercentage(double taxPercentage) { this.taxPercentage = taxPercentage; }
}
