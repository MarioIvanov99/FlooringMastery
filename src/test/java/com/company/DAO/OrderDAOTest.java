package com.company.DAO;

import com.company.Model.Orders;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class OrderDAOTest {

    @Test
    @Description("Test should return correct info")
    void correctnessTest(){
        OrderDAO dao = new OrderDAO();
        ArrayList<Orders> ordersList = dao.readFile("Orders/Orders_06022013.txt");
        assertEquals("3,Albert Einstein,KY,6.00,Carpet,217.00,2.25,2.10,488.25,455.70,56.64,1000.59", ordersList.get(1).toString());
    }

    /*@Test
    @Description("Test should return correct map size")
    void sizeTest(){
        OrderDAO dao = new OrderDAO();
        dao.populateMap();
    }*/ //Used only in development

    /*@Test
    void writeTest(){
        OrderDAO dao = new OrderDAO();
        dao.populateMap();
        dao.writeAll();
    }*/ //Used only in development

}