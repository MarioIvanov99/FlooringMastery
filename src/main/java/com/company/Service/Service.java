package com.company.Service;

import java.math.BigDecimal;

public class Service {

    //Calculates material cost area*costPerSquareFoot
    public static BigDecimal calculateMaterialCost(BigDecimal area, BigDecimal costPerSquareFoot)
    {
        return area.multiply(costPerSquareFoot);
    }

    //Calculates labor cost area*laborCostPerSquareFoot
    public static BigDecimal calculateLaborCost(BigDecimal area, BigDecimal laborCostPerSquareFoot)
    {
        return area.multiply(laborCostPerSquareFoot);
    }

    //Calculates tax (labor+material)*(taxRate/100)
    public static BigDecimal calculateTax(BigDecimal materialCost, BigDecimal laborCost, BigDecimal taxRate)
    {
        return ((materialCost.add(laborCost)).multiply(taxRate.divide(new BigDecimal(100))));
    }

    //Calculates total material+labor+tax
    public static BigDecimal calculateTotal(BigDecimal materialCost, BigDecimal laborCost, BigDecimal tax)
    {
        return materialCost.add(laborCost).add(tax);
    }
}
