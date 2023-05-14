package com.company.DAO;

import com.company.Model.Tax;
import jdk.jfr.Description;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class TaxDAOTest {
    static TaxDAO dao = new TaxDAO();

    @BeforeAll
    static void init() throws FileNotFoundException{
        dao.readFile ("Taxes.txt");
    }

    @Test
    @Description("Test should return the correct number of items, 4.")
    void arrayLengthTest() {
        assertEquals(4, dao.getAllTaxes().size());
    }

    @Test
    @Description("Test should return the correct data at index 2")
    void dataCorrectnessTest() {
        assertEquals("KY", dao.getAllTaxes().get(2).getStateAbbreviation());
        assertEquals("Kentucky", dao.getAllTaxes().get(2).getStateName());
        assertEquals(new BigDecimal("6.00"), dao.getAllTaxes().get(2).getTaxRate());
    }

    @Test
    @Description("Test should throw FileNotFound exception if the file does not exist")
    void exceptionTest() {
        assertThrows(FileNotFoundException.class, () -> {
            TaxDAO dao = new TaxDAO();
            dao.readFile("Taxess.txt");
        });
    }
}