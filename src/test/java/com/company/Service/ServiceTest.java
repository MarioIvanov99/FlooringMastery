package com.company.Service;

import jdk.jfr.Description;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {
    BigDecimal area = new BigDecimal("10");
    BigDecimal costPerSquareFoot = new BigDecimal("0.25");
    BigDecimal laborCostPerSquareFoot = new BigDecimal("0.35");
    BigDecimal materialCost = new BigDecimal("2.50");
    BigDecimal laborCost = new BigDecimal("3.50");
    BigDecimal taxRate = new BigDecimal("25.00");
    BigDecimal tax = new BigDecimal("1.50");
    @Test
    @Description("Should correctly calculate material cost using the formula")
    void calculateMaterialCostTest() {
        assertEquals(materialCost.stripTrailingZeros(), Service.calculateMaterialCost(area, costPerSquareFoot).stripTrailingZeros());
    }

    @Test
    @Description("Should correctly calculate labor cost using the formula")
    void calculateLaborCostTest() {
        assertEquals(laborCost.stripTrailingZeros(), Service.calculateLaborCost(area, laborCostPerSquareFoot).stripTrailingZeros());
    }

    @Test
    @Description("Should correctly calculate tax using the formula")
    void calculateTaxTest() {
        assertEquals(tax.stripTrailingZeros(), Service.calculateTax(materialCost, laborCost, taxRate).stripTrailingZeros());
    }

    @Test
    @Description("Should correctly calculate total cost using the formula")
    void calculateTotalTest() {
        assertEquals(new BigDecimal("7.50").stripTrailingZeros(), Service.calculateTotal(materialCost, laborCost, tax).stripTrailingZeros());
    }
}