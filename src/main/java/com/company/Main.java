package com.company;

import com.company.Controller.Controller;

import java.io.FileNotFoundException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        //Main calls the controller
        Controller controller = new Controller();
        controller.runProgram();
    }
}