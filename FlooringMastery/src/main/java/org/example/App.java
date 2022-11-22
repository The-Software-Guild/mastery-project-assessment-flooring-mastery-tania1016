package org.example;

import org.example.UserIO.UserIO;
import org.example.UserIO.UserIOConsoleImp;
import org.example.controller.FlooringMasteryController;
import org.example.dao.*;
import org.example.service.FlooringMasteryService;
import org.example.service.FlooringMasteryServiceImp;
import org.example.view.FlooringMasteryView;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {

     /*   UserIO myIO = new UserIOConsoleImp();
         FlooringMasteryView myView = new FlooringMasteryView(myIO);
         FlooringMasteryOrderDao myOrderDao = new FlooringMasteryOrderDaoImp();
        FlooringMasteryProductsDao myProductsDao = new FlooringMasteryProductsDaoImp();
        FlooringMasteryTaxDao myTaxDao = new FlooringMasteryTaxDaoImp();
         FlooringMasteryService myService = new FlooringMasteryServiceImp(myOrderDao, myProductsDao, myTaxDao);
         FlooringMasteryController myController = new FlooringMasteryController(myService, myView);
         myController.run();*/

        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        FlooringMasteryController controller = ctx.getBean("controller", FlooringMasteryController.class);
        controller.run();

    }
}