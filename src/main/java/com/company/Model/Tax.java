package com.company.Model;


import java.math.BigDecimal;

public class Tax {
    private String stateAbbreviation;
    private String stateName;
    private BigDecimal taxRate;

    //Constructor that sets all values
    public Tax(String stateAbbreviation, String stateName, BigDecimal taxRate) {
        this.stateAbbreviation = stateAbbreviation;
        this.stateName = stateName;
        this.taxRate = taxRate;
    }

    //Getters
    public String getStateAbbreviation() {
        return stateAbbreviation;
    }

    public String getStateName() {
        return stateName;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    //Setters
    public void setStateAbbreviation(String stateAbbreviation) {
        this.stateAbbreviation = stateAbbreviation;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    @Override
    public String toString() {
        String taxString = this.stateAbbreviation + "," + this.stateName + "," + this.taxRate.toString();
        return taxString;
    }
}
