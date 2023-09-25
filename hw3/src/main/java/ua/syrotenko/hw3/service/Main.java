package ua.syrotenko.hw3.service;

import ua.syrotenko.hw3.controller.MainController;

import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException {


        MainController mainController = new MainController();
        mainController.start();


    }
}
