package com.company.DTO;

import com.company.DAO.OrderDAO;
import com.company.Model.Orders;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderDTO {

    private HashMap<String, ArrayList<Orders>> ordersDTO = new HashMap();

    public OrderDTO(OrderDAO dao){
        this.ordersDTO = dao.getHashMap();
    } //Constructor sets the DTO's hash map to the DAO's

    public HashMap<String, ArrayList<Orders>> getOrdersDTO() {
        return ordersDTO;
    }

    public void setOrdersDTO(HashMap<String, ArrayList<Orders>> ordersDTO) {
        this.ordersDTO = ordersDTO;
    }

    //Adds key and value to map
    public void put(String key, ArrayList<Orders> value){
        ordersDTO.put(key, value);
    }
}
