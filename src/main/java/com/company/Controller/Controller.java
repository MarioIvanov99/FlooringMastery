package com.company.Controller;

import com.company.DAO.OrderDAO;
import com.company.DAO.ProductDAO;
import com.company.DAO.TaxDAO;
import com.company.DTO.OrderDTO;
import com.company.Model.Orders;
import com.company.Service.Service;
import com.company.View.*;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Controller {

    //Creating objects for all classes that are required
    private UserIO uio = new UserIO();
    private TaxDAO taxDao = new TaxDAO();
    private ProductDAO productDAO = new ProductDAO();
    private OrderDAO orderDAO = new OrderDAO();
    private OrderDTO orderDTO = new OrderDTO(orderDAO);
    private boolean userIsDone = false;

    public void runProgram() throws FileNotFoundException { //The top layer program, called in main

        taxDao.readFile("Taxes.txt"); //These are string literals because the names of the files do not change
        productDAO.readFile("Products.txt");
        orderDAO.populateMap(); //File names for orders are automatically got, by going through a provided folder.

        do{
            int userAction = uio.getUserAction(); //Is the main menu
            selector(userAction); //Function that calls the switch statement
        }while(!userIsDone);

    }

    private void selector(int userAction){ //Just a switch statement
        switch (userAction){ //One method for all required actions
            //Break is required so that the program stops whenever it needs to
            case 1:
                this.display();
                break;
            case 2:
                this.add();
                break;
            case 3:
                this.edit();
                break;
            case 4:
                this.remove();
                break;
            case 5:
                this.export();
                break;
            case 6:
                System.exit(0);
        }
    }

    private void display(){

        String date = uio.displayOrders(); //Calls the UI function to display orders

        if(orderDTO.getOrdersDTO().containsKey(date)){ //Checks map for date

            ArrayList<Orders> ordersAtDate = orderDTO.getOrdersDTO().get(date);//Gets the orders from that date

            for(Orders o: ordersAtDate){ //Displays all orders in a particular date
                System.out.println(o.toString());
            }

        }
        else{ //Message if date doesn't exist
            System.out.println("There are no orders made for that date.");
        }

    }

    private void add(){

        String[] newOrderString = uio.addOrder(); //Gets the data from the UI

        String dateKey = newOrderString[0]; //Key

        String customerName = newOrderString[1];
        String state = newOrderString[2].trim(); //Trim to remove leading and trailing spaces

        boolean stateExists = false;//Quick check for state existence
        BigDecimal area = new BigDecimal(newOrderString[3]);

        System.out.println("These are the available products: "); //List the products
        //This is done in the Controller instead of the UI because the UI does not have access to the products information

        for(int i = 0; i < productDAO.getAllProducts().size(); i++){ //Lists all products and their indeces
            System.out.println(i + ": " + productDAO.getAllProducts().get(i).toString());
        }

        //Select product
        int index = IOHandler.checkInputCorrectness(0, productDAO.getAllProducts().size()-1, "Please select a product: ");
        int taxIndex = 0;

        for(int i = 0; i < taxDao.getAllTaxes().size(); i++){ //Set the index to use the tax array list
            if(state.equals(taxDao.getAllTaxes().get(i).getStateAbbreviation())){
                stateExists = true;
                taxIndex = i;
            }
        }

        if(!stateExists){ //Returns to menu if state doesn't exist in the list
            System.out.println("We do not do business in that state");
            return;
        }

        int orderNumber;
        ArrayList<Orders> orderList = new ArrayList(); //Creates new array list

        if(orderDTO.getOrdersDTO().containsKey(dateKey)){ //If there are orders for that date, set the list to the existing one and set the orderNumber to current highest+1
            orderList = orderDTO.getOrdersDTO().get(dateKey);
            orderNumber = orderList.get(orderList.size()-1).getOrderNumner()+1;
        }
        else{ //If it's the first order for the date, set order number to 1
            orderNumber = 1;
        }

        //Set variables based on Tax and Product data
        String productType = productDAO.getAllProducts().get(index).getProductType();
        BigDecimal costPerSquareFoot = productDAO.getAllProducts().get(index).getCostPerSquareFoot();
        BigDecimal laborCostPerSquareFoot = productDAO.getAllProducts().get(index).getLaborCostPerSquareFoot();
        BigDecimal taxRate = taxDao.getAllTaxes().get(taxIndex).getTaxRate();

        //Calculate variables that are derived from existing data
        BigDecimal materialCost = Service.calculateMaterialCost(area, costPerSquareFoot);
        BigDecimal laborCost = Service.calculateLaborCost(area, laborCostPerSquareFoot);
        BigDecimal tax = Service.calculateTax(materialCost, laborCost, taxRate);
        BigDecimal total = Service.calculateTotal(materialCost, laborCost, tax);

        //Create a new Order object
        Orders newOrder = new Orders(orderNumber, customerName, state, taxRate, productType, area, costPerSquareFoot, laborCostPerSquareFoot, materialCost, laborCost, tax, total);

        //Shows order to customer
        System.out.println(newOrder.toString());

        //Yes or No question
        if(uio.yesORno()){
            orderList.add(newOrder);
            orderDTO.put(dateKey, orderList);
        }

    }

    private void edit(){

        //Gets date and order number
        String dateKey = IOHandler.getDateInput();
        int orderNumber = IOHandler.checkInputCorrectness(1, 1000, "Which order would you want to edit?");

        //Creates orders list
        ArrayList<Orders> orders = new ArrayList();
        if(orderDTO.getOrdersDTO().containsKey(dateKey)){
            orders = orderDTO.getOrdersDTO().get(dateKey);
        }

        boolean doesOrderExist = false;
        int index = 0;

        //Checks for existence
        for(int i = 0; i<orders.size(); i++){
            if(orderNumber == orders.get(i).getOrderNumner()){
                doesOrderExist = true;
                index = i;
            }
        }

        if(!doesOrderExist){
            System.out.println("No such order has been made");
            return;
        }

        //gets editted data
        String[] newOrderString = uio.editOrder();

        //Prompts user to choose a product
        System.out.println("These are the available products: ");
        for(int i = 0; i < productDAO.getAllProducts().size(); i++){
            System.out.println(i + ": " + productDAO.getAllProducts().get(i).toString());
        }


        String prodIndex = IOHandler.checkNumbersEditted(0, productDAO.getAllProducts().size()-1, "Please select a product: ");
        if(prodIndex.length()>0){ //If there's a change in product, update information
            int prodIndexNum = Integer.parseInt(prodIndex);
            orders.get(index).setProductType(productDAO.getAllProducts().get(prodIndexNum).getProductType());
            orders.get(index).setCostPerSquareFoot(productDAO.getAllProducts().get(prodIndexNum).getCostPerSquareFoot());
            orders.get(index).setLaborCostPerSquareFoot(productDAO.getAllProducts().get(prodIndexNum).getLaborCostPerSquareFoot());
        }

        if(newOrderString[0].length() > 0){ //A change in customer name does not change anything else
            orders.get(index).setCustomerName(newOrderString[0]);
        }

        //Check if new state exists in state list
        BigDecimal taxRate = orders.get(index).getTaxRate();
        int taxIndex = 0;
        if(newOrderString[1].length() > 0){
            String state = newOrderString[1];

            boolean stateExists= false;
            for(int i = 0; i < taxDao.getAllTaxes().size(); i++){
                if(state.equals(taxDao.getAllTaxes().get(i).getStateAbbreviation())){
                    stateExists = true;
                    taxIndex = i;
                }
            }

            if(!stateExists){
                System.out.println("We do not do business in that state");
                return;
            }

            //If there's a change in data, updates the list
            orders.get(index).setState(taxDao.getAllTaxes().get(taxIndex).getStateAbbreviation());
            orders.get(index).setTaxRate(taxDao.getAllTaxes().get(taxIndex).getTaxRate());
        }

        //If there's a change in area, updates the list
        if(newOrderString[2].length() > 0){
            orders.get(index).setArea(new BigDecimal(newOrderString[2]));
        }

        //Calculates new values for derivative data
        Orders edittedOrder = orders.get(index);
        edittedOrder.setMaterialCost(Service.calculateMaterialCost(edittedOrder.getArea(), edittedOrder.getCostPerSquareFoot()));
        edittedOrder.setLaborCost(Service.calculateMaterialCost(edittedOrder.getArea(), edittedOrder.getLaborCostPerSquareFoot()));
        edittedOrder.setTax(Service.calculateTax(edittedOrder.getMaterialCost(), edittedOrder.getLaborCost(), edittedOrder.getTaxRate()));
        edittedOrder.setTotal(Service.calculateTotal(edittedOrder.getMaterialCost(), edittedOrder.getLaborCost(), edittedOrder.getTax()));


        System.out.println(edittedOrder.toString());

        if(uio.yesORno()){
            orderDTO.put(dateKey, orders);
        }

    }

    private void remove(){

        //Gets date and number and checks if such an order exists
        String dateKey = IOHandler.getDateInput();
        int orderNumber = IOHandler.checkInputCorrectness(1, 1000, "Which order would you want to remove?");

        ArrayList<Orders> orders = new ArrayList();
        if(orderDTO.getOrdersDTO().containsKey(dateKey)){
            orders = orderDTO.getOrdersDTO().get(dateKey);
        }

        boolean doesOrderExist = false;
        int index = 0;

        for(int i = 0; i<orders.size(); i++){
            if(orderNumber == orders.get(i).getOrderNumner()){
                doesOrderExist = true;
                index = i;
            }
        }

        if(!doesOrderExist){
            System.out.println("No such order has been made");
            return;
        }

        System.out.println(orders.get(index));

        if(uio.yesORno()){ //Removes order if user says yes
            orders.remove(index);
            orderDTO.put(dateKey, orders);
        }


    }

    private void export(){

        orderDAO.setHashMap(orderDTO.getOrdersDTO()); //Load DTO data into the DAO
        orderDAO.writeAll(); //Writes whole map

    }

}
