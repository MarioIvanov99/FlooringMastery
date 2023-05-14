package com.company.DAO;

import com.company.Model.Products;
import com.company.Model.Tax;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;

public class ProductDAO implements DAO{

    private String filename = "Products.txt"; //Unchanging
    private ArrayList<Products> allProducts = new ArrayList();

    public ProductDAO() {}

    public String getFilename() {
        return filename;
    }

    public ArrayList<Products> getAllProducts() {
        return allProducts;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setAllProducts(ArrayList<Products> allProducts) {
        this.allProducts = allProducts;
    }
    @Override
    public void readFile(String filename) throws FileNotFoundException {

        File allProducts = new File(filename);
        FileReader fileReader;
        String[] lineValue;

        try {

            //Basic file reader
            fileReader = new FileReader(allProducts);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String lineFromFile = " ";
            bufferedReader.readLine();

            while (lineFromFile != null) {
                lineFromFile = bufferedReader.readLine(); // Reading from file line per line
                if (lineFromFile != null) {
                    lineValue = lineFromFile.split(",");

                    String productName = lineValue[0];
                    BigDecimal costPerSquareFoot = new BigDecimal(lineValue[1]);
                    BigDecimal laborCostPerSquareFoot = new BigDecimal(lineValue[2]);

                    // Now create an object of that type
                    Products tempProduct = new Products(productName, costPerSquareFoot, laborCostPerSquareFoot); // These are added to an ArrayList in the Product Constructor
                    // Adds tempProduct to the ArrayList allProducts
                    this.allProducts.add(tempProduct);

                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new FileNotFoundException("File not found");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    //Products file isn't changed by the application
    @Override
    public void writeFile(String filename){

    }

}
