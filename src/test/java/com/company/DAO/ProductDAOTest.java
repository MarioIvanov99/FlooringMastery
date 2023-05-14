package com.company.DAO;

import com.company.Model.Products;
import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductDAOTest {

    static ProductDAO dao = new ProductDAO();

    @BeforeAll
    static void init() throws FileNotFoundException {
        dao.readFile ("Products.txt");
    }

    @Test
    @Description("Test should return the correct number of items, 4.")
    void arrayLengthTest() {
        assertEquals(4, dao.getAllProducts().size());
    }

    @Test
    @Description("Test should return the correct data at index 1")
    void dataCorrectnessTest() {
        assertEquals("Laminate", dao.getAllProducts().get(1).getProductType());
        assertEquals(new BigDecimal("1.75"), dao.getAllProducts().get(1).getCostPerSquareFoot());
        assertEquals(new BigDecimal("2.10"), dao.getAllProducts().get(1).getLaborCostPerSquareFoot());
    }

    @Test
    @Description("Test should throw FileNotFound exception if the file does not exist")
    void exceptionTest() {
        assertThrows(FileNotFoundException.class, () -> {
            ProductDAO dao = new ProductDAO();
            dao.readFile("Productss.txt");
        });
    }
}