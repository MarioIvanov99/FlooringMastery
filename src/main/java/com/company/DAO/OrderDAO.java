package com.company.DAO;

import com.company.Model.Orders;
import com.company.Model.Tax;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderDAO{

    //HashMap for unique values that represent dates.
    //ArrayList to contain the orders in a singular file
    private HashMap<String, ArrayList<Orders>> ordersMap = new HashMap<>();

    public OrderDAO(){
        this.populateMap();
    }

    public HashMap<String, ArrayList<Orders>> getHashMap() {
        return ordersMap;
    }

    public void setHashMap(HashMap<String, ArrayList<Orders>> hashMap) {
        this.ordersMap = hashMap;
    }

    //This is not void, unlike the other DAOs because the Orders need to be put into an array list that is then put into a hashmap
    public ArrayList<Orders> readFile(String filename){

        //Basic file reader
        File allOrders = new File(filename);
        FileReader fileReader;
        String[] lineValue;
        ArrayList<Orders> ordersList = new ArrayList();

        try {
            fileReader = new FileReader(allOrders); // ... FileReader instantiated here
            BufferedReader bufferedReader = new BufferedReader(fileReader); // Read line per line
            String lineFromFile = " ";
            bufferedReader.readLine();

            while (lineFromFile != null) {
                lineFromFile = bufferedReader.readLine(); // Reading from file line per line
                if (lineFromFile != null) {
                    lineValue = lineFromFile.split(",");

                    //One by one isertion of data into an order
                    int orderNumber = Integer.parseInt(lineValue[0]);
                    String customerName = lineValue[1];
                    String state = lineValue[2];
                    BigDecimal taxRate = new BigDecimal(lineValue[3]);
                    String productType = lineValue[4];
                    BigDecimal area = new BigDecimal(lineValue[5]);
                    BigDecimal costPerSquareFoot = new BigDecimal(lineValue[6]);
                    BigDecimal laborCostPerSquareFoot = new BigDecimal(lineValue[7]);
                    BigDecimal materialCost = new BigDecimal(lineValue[8]);
                    BigDecimal laborCost = new BigDecimal(lineValue[9]);
                    BigDecimal tax = new BigDecimal(lineValue[10]);
                    BigDecimal total = new BigDecimal(lineValue[11]);

                    // Now create an object of that type
                    Orders tempOrder = new Orders(orderNumber, customerName, state, taxRate, productType, area, costPerSquareFoot, laborCostPerSquareFoot
                    , materialCost, laborCost, tax, total); // These are added to an ArrayList in the Product Constructor
                    // Adds tempProduct to the ArrayList allStock
                    ordersList.add(tempOrder);

                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

        return ordersList;

    }

    public void populateMap(){

        //Reads folder Orders
        File folder = new File("Orders");
        //Puts every file in the folder into an array
        File[] files = folder.listFiles();

       for(File file : files){ //For every file
           System.out.println(file.getName());

           //Get only the date of the file name
           Pattern pattern = Pattern.compile("[0-9]+");
           Matcher matcher = pattern.matcher(file.getName());
           String dateKey = "";

           while (matcher.find()) {
               dateKey += matcher.group();
           }

           //Use date as key, then read the file in the Orders folder
           this.ordersMap.put(dateKey, this.readFile("Orders/"+file.getName()));

       }


    }

    private void writeFile(String date){

        String fileName = "Orders_" + date + ".txt"; //Creates a formatted file name from the date
        //Note, this will not put the files in the Orders folder, but in the the working directory
        //This is done for more consistent testing
        //To put the files in the orders folder instead, use:
        //String fileName = "Orders/Orders_" + date + ".txt";

        try {

            FileWriter writer = new FileWriter(fileName);
            //Write the header
            writer.write("OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total\n");

            ArrayList<Orders> outputList = new ArrayList();
            outputList = this.ordersMap.get(date);

            //Write every order to file
            for(Orders o:outputList){
                writer.write(o.toString());
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void writeAll(){

        //Stream and lambda to write every file in the map
        ordersMap.forEach((key, value) -> {
            this.writeFile(key);
        });
    }

}
