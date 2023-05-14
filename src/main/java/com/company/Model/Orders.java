package com.company.Model;

import java.math.BigDecimal;

public class Orders {

    private int orderNumner;
    private String customerName;
    private String state;
    private BigDecimal taxRate;
    private String productType;
    private BigDecimal area;
    private BigDecimal costPerSquareFoot;
    private BigDecimal laborCostPerSquareFoot;
    private BigDecimal materialCost;
    private BigDecimal laborCost;
    private BigDecimal tax;
    private BigDecimal total;

    //Constructor that sets each value
    public Orders(int orderNumner, String customerName, String state,
                  BigDecimal taxRate, String productType, BigDecimal area,
                  BigDecimal costPerSquareFoot, BigDecimal laborCostPerSquareFoot,
                  BigDecimal materialCost, BigDecimal laborCost, BigDecimal tax, BigDecimal total)
    {
        this.orderNumner = orderNumner;
        this.customerName = customerName;
        this.state = state;
        this.taxRate = taxRate;
        this.productType = productType;
        this.area = area;
        this.costPerSquareFoot = costPerSquareFoot;
        this.laborCostPerSquareFoot = laborCostPerSquareFoot;
        this.materialCost = materialCost;
        this.laborCost = laborCost;
        this.tax = tax;
        this.total = total;
    }

    public Orders(int orderNumner, String customerName, String state,
                  BigDecimal taxRate, String productType, BigDecimal area,
                  BigDecimal costPerSquareFoot, BigDecimal laborCostPerSquareFoot)
    {
        this.orderNumner = orderNumner;
        this.customerName = customerName;
        this.state = state;
        this.taxRate = taxRate;
        this.productType = productType;
        this.area = area;
        this.costPerSquareFoot = costPerSquareFoot;
        this.laborCostPerSquareFoot = laborCostPerSquareFoot;

    }

    //Getters

    public int getOrderNumner() {
        return orderNumner;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getState() {
        return state;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public String getProductType() {
        return productType;
    }

    public BigDecimal getArea() {
        return area;
    }

    public BigDecimal getCostPerSquareFoot() {
        return costPerSquareFoot;
    }

    public BigDecimal getLaborCostPerSquareFoot() {
        return laborCostPerSquareFoot;
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public BigDecimal getTotal() {
        return total;
    }

   //Setters
    public void setOrderNumner(int orderNumner) {
        this.orderNumner = orderNumner;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public void setCostPerSquareFoot(BigDecimal costPerSquareFoot) {
        this.costPerSquareFoot = costPerSquareFoot;
    }

    public void setLaborCostPerSquareFoot(BigDecimal laborCostPerSquareFoot) {
        this.laborCostPerSquareFoot = laborCostPerSquareFoot;
    }

    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost;
    }

    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public String toString() {
        String orderString = this.orderNumner + "," + this.customerName + "," + this.state + "," +
                this.taxRate + "," + this.productType + "," + this.area + "," + this.costPerSquareFoot + "," +
                this.laborCostPerSquareFoot + "," + this.materialCost + "," + this.laborCost + "," + this.tax + "," + this.total;

        return orderString;

    }
}
