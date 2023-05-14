package com.company.View;

import java.util.InputMismatchException;
import java.util.Scanner;

public class IOHandler {

    private static Scanner scanner = new Scanner(System.in); //Scanner to do all inputs

    public static void renderMenu(){ //The menu as specified

        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        System.out.println("* <<Flooring Program>>");
        System.out.println("* 1. Display Orders");
        System.out.println("* 2. Add an Order");
        System.out.println("* 3. Edit an Order");
        System.out.println("* 4. Remove an Order");
        System.out.println("* 5. Export All Data");
        System.out.println("* 6. Quit");
        System.out.println("*");
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");

    }

    public static int checkInputCorrectness(int minBound, int maxBound, String introMessage){ //Integer input verifier

        int validNumber = 0;
        boolean isValid = false;

        do { //Do until there is a valid input
            try {

                System.out.print(introMessage); //The message that prompts the user
                validNumber = scanner.nextInt(); //Scanner for next line

                if(validNumber >= minBound && validNumber <= maxBound){ //Bounds check
                    isValid = true;
                }
                else{
                    System.out.println("Input is invalid. Please enter an integer between " //Error message
                            + minBound + " and " + maxBound + ", inclusive");
                }

            } catch (InputMismatchException e) { //Input data type checker

                System.out.println("Input is invalid. Please enter an integer between "
                        + minBound + " and " + maxBound + ", inclusive");
                scanner.nextLine();

            }
        } while (!isValid);

        return validNumber;

    }

    public static String checkNumbersEditted(int minBound, int maxBound, String introMessage){ //Checks if editted
    //Returns string because blank input is possible

        String num;
        boolean isValid = false;

        do{ //Repeat until valid input

            System.out.print(introMessage);
            num = scanner.nextLine();

            if(num.equals("")) //if it's blank, return as it is valid
                return num;

            try {

                int validNumber = Integer.parseInt(num); //Get an int out of the string

                if(validNumber >= minBound && validNumber <= maxBound){
                    isValid = true;
                }
                else{
                    System.out.println("Input is invalid. Please enter an integer higher than " + minBound);
                }

            } catch (NumberFormatException e) { //Check if input is actually an int

                System.out.println("Input is invalid. Please enter an integer higher than " + minBound);
                //scanner.nextLine();

            }
        } while (!isValid);

        return num;

    }

    public static String getDateInput(){ //Gets date input

        System.out.println("Please input the date of the order in the order Month, Day, Year");
        int month = IOHandler.checkInputCorrectness(1, 12, "Please enter month (1-12): ");
        int day = checkInputCorrectness(1, 31, "Please enter day (1-31): ");
        int year = checkInputCorrectness(2000, 2025, "Please enter year (2000-2025): ");

        String date = String.format("%02d", month) //Creates string while adding 0s to single digit numbers
                + String.format("%02d", day)
                + year;

        return date;
    }

    public static String getStringInput(boolean edittable){ //Get string input, for names

        String name;
        boolean isValid = false;

        scanner.nextLine(); //Required to eat leftover \n

        do{ //Repeat until valid input
            System.out.print("Please enter client name:");
            name = scanner.nextLine();
            if(name.equals("") && edittable){ //If it's blank and the method is set to editable, blank inputs are valid
                isValid = true;
            }
            else if(name.matches("[a-zA-Z0-9,.\\s]+")){ //If method is not editable, allow only names that have letters, deigits, commas, periods and spaces
                isValid = true;
            }
            else{
                System.out.println("\nName must only include letters, digits, commas and periods.");
            }

        }while(!isValid);

        return name;
    }

    public static String getStateInput(boolean edittable){

        //State input functions like name input, except only strings of length 2, comprised of capital letters are allowed

        String abb;
        boolean isValid = false;

        do{
            System.out.print("Please enter state abbreviation:");
            abb = scanner.nextLine();

            if(abb.equals("") && edittable){
                isValid = true;
            }
            else if(abb.matches("^[A-Z]{2}$")){
                isValid = true;
            }
            else{
                System.out.println("\nAbbreviation must be 2 capital letters.");
            }

        }while(!isValid);

        return abb;
    }

    public static char getSingleChar(){

        //Same checks for string correctness, but only input of length 1 that is n, N, y or Y is accepted
        String yn; //yes or no
        boolean isValid = false;
        scanner.nextLine(); //Required to eat leftover \n

        do{
            System.out.print("Look through your order. Are you sure you would like to continue?:");
            yn = scanner.nextLine();

            if(yn.matches("^[nyNY]$")){
                isValid = true;
            }
            else{
                System.out.println("\nAnswer only with y or n.");
            }

        }while(!isValid);

        return yn.charAt(0);
    }

}
