package com.company.View;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserIO {

    //Orders list

    private void displayMainMenu(){ //Displays menu
        IOHandler.renderMenu();
    }

    public String displayOrders(){ //display orders
        return IOHandler.getDateInput();
    }

    public String[] addOrder(){

        LocalDate date;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy"); //Uses data data to create date object
        LocalDate today = LocalDate.now();
        String dateString;

        do{ //Checks if input date is in the future
            dateString = IOHandler.getDateInput();
            date = LocalDate.parse(dateString, formatter);
            if(!date.isAfter(today)){
                System.out.println("You must enter a date in the future");
            }
        }while(!date.isAfter(today));

        String customer = IOHandler.getStringInput(false); //Gets customer name

        //Checks for a 2 capital letter input
        String state = IOHandler.getStateInput(false); //Gets state

        //6,636,360 is the area of the Pentagon, which should be the largest possible area for an order
        int area = IOHandler.checkInputCorrectness(100, 6636360, "Enter an area. Minimum 100 sq ft.");

        //Products are selected in the controller

        //Uses tabs as separators instead of commas because commas are allowed in the name.
        String fullOrder = dateString + "   " + customer + "    " + state + "   " + area;
        String[] newOrder = fullOrder.split("   ");
        System.out.println(fullOrder);
        return newOrder;
    }

    public String[] editOrder(){

        //Edited functions that allow for blank inputs
        String customer = IOHandler.getStringInput(true);
        String state = IOHandler.getStateInput(true);
        String area = IOHandler.checkNumbersEditted(100, 6636360, "Enter an area. Minimum 100 sq ft.");

        String[] fullEdit = {customer, state, area}; //Not using split because empty strings have complex behaviour;

        return fullEdit;
    }

    public boolean yesORno(){
        char choice = IOHandler.getSingleChar();

        if(choice == 'y') //Returns true if yes, and false if no
            return true;
        else
            return false;
    }

    public int getUserAction(){ //User chooses what to do out of the 6 options.

        this.displayMainMenu();

        return IOHandler.checkInputCorrectness(1, 6, "Please select your desired action: ");
    }


}
