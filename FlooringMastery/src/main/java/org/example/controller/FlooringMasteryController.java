package org.example.controller;

import org.example.UserIO.UserIO;
import org.example.UserIO.UserIOConsoleImp;
import org.example.dao.DataPersistenceException;
import org.example.model.Orders;
import org.example.service.*;
import org.example.view.FlooringMasteryView;

import java.time.LocalDate;

public class FlooringMasteryController {

    private FlooringMasteryView view ;
    private FlooringMasteryService service;

    public FlooringMasteryController(FlooringMasteryService service, FlooringMasteryView view){
        this.view=view;
        this.service = service;
    }
    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        getOrdersByDate();
                        break;
                    case 2:
                       addOrder();
                        break;
                    case 3:
                        editOrder();
                        break;
                    case 4:
                        removeOrder();
                        break;
                    case 5:
                        System.out.println("6. Export All Data");
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        System.out.println("UNKNOWN COMMAND");
                }
            }
            exitMessage();
        } catch (DataPersistenceException e) {
                view.displayErrorMessage(e.getMessage());
            }
    }
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void exitMessage(){
        view.displayFinalMessage();
    }

    private void unknownCommand(){
        view.displayErrorMessage("UnKnown Command");;
    }

    private void getOrdersByDate() throws DataPersistenceException {
        LocalDate date = view.inputDate();
        view.displayDateBanner(date);
        try {
            view.displayDateOrders(service.getOrders(date));
            view.displayContinue();
        } catch (InvalidOrderNumberException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private void addOrder() throws DataPersistenceException {
        try {
            Orders order = service.calculateOrders(view.getOrder());
            view.displayOrder(order);
            String response = view.askSave();
            if (response.equalsIgnoreCase("Y")) {
                service.addOrder(order);
                view.displayAddOrderSuccess(true, order);
            } else if (response.equalsIgnoreCase("N")) {
                view.displayAddOrderSuccess(false, order);
            } else {
                unknownCommand();
            }
        } catch (OrderValidationException
                 | StateTaxValidationException | ProductValidationException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private void editOrder() throws DataPersistenceException {
        view.displayEditOrderBanner();
        try {
            LocalDate dateChoice = view.inputDate();
            String orderNumber = view.inputOrderNumber();
            Orders savedOrder = service.getOrder(dateChoice, orderNumber);
            Orders editedOrder = view.editOrderInfo(savedOrder);
            Orders updatedOrder = service.compareOrders(savedOrder, editedOrder);
            view.displayEditOrderBanner();
            view.displayOrder(updatedOrder);
            String response = view.askSave();
            if (response.equalsIgnoreCase("Y")) {
                service.editOrder(updatedOrder);
                view.displayEditOrderSuccess(true, updatedOrder);
            } else if (response.equalsIgnoreCase("N")) {
                view.displayEditOrderSuccess(false, updatedOrder);
            } else {
                unknownCommand();
            }
        } catch (InvalidOrderNumberException
                 | ProductValidationException | StateTaxValidationException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private void removeOrder() throws DataPersistenceException {
        view.displayRemoveOrderBanner();
        LocalDate dateChoice = view.inputDate();
        view.displayDateBanner(dateChoice);
        try {
            view.displayDateOrders(service.getOrders(dateChoice));
            String orderNumber = view.inputOrderNumber();
            Orders order = service.getOrder(dateChoice, orderNumber);
            view.displayRemoveOrderBanner();
            view.displayOrder(order);
            String response = view.askRemove();
            if (response.equalsIgnoreCase("Y")) {
                service.removeOrder(order);
                view.displayRemoveOrderSuccess(true, order);
            } else if (response.equalsIgnoreCase("N")) {
                view.displayRemoveOrderSuccess(false, order);
            } else {
                unknownCommand();
            }
        } catch (InvalidOrderNumberException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

}
