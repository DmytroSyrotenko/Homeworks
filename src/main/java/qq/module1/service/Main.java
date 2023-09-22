package qq.module1.service;

import qq.module1.controller.MainController;
import qq.module1.entity.Group;
import qq.module1.entity.GroupStudent;

import java.io.IOException;

public class Main  {


    public static void main(String[] args) throws IOException {

        MainController mainController = new MainController();

        mainController.start();



    }

}
