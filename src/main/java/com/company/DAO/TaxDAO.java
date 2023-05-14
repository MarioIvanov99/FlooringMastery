package com.company.DAO;

import com.company.Model.Tax;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;

public class TaxDAO implements DAO{

    private String filename = "Taxes.txt"; //Unchanging
    private ArrayList<Tax> allTaxes = new ArrayList();

    public TaxDAO() {}

    public String getFilename() {
        return filename;
    }

    public ArrayList<Tax> getAllTaxes() {
        return allTaxes;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setAllTaxes(ArrayList<Tax> allTaxes) {
        this.allTaxes = allTaxes;
    }

    //Basic file reader
    @Override
    public void readFile(String filename) throws FileNotFoundException{

        File allTaxes = new File(filename);
        FileReader fileReader;
        String[] lineValue;

        try {
            fileReader = new FileReader(allTaxes);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String lineFromFile = " ";

            bufferedReader.readLine();

            while (lineFromFile != null) {
                lineFromFile = bufferedReader.readLine(); // Reading from file line per line
                if (lineFromFile != null) {
                    lineValue = lineFromFile.split(",");

                    String stateAb = lineValue[0];
                    String stateName = lineValue[1];
                    BigDecimal taxRate = new BigDecimal(lineValue[2]);

                    Tax tempProduct = new Tax(stateAb, stateName, taxRate);
                    this.allTaxes.add(tempProduct);

                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new FileNotFoundException("File not found");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    //writeFile not implemented because the taxes file cannot be changed by the application
    @Override
    public void writeFile(String filename){

    }


}
