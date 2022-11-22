package org.example.view;

import org.example.UserIO.UserIO;
import org.example.model.Orders;
import org.example.model.Products;
import org.example.model.Tax;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class FlooringMasteryView {
    private UserIO io;


    public FlooringMasteryView(UserIO io) {

        this.io = io;
    }
    public void displayFlooringMasteryWelcome(){
        io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n" +
                "   * <<Flooring Program>>");

    }

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. Display Orders");
        io.print("2. Add an Order");
        io.print("3. Edit an Order");
        io.print("4. Remove an Order");
        io.print("5. Export All Data");
        io.print("6. Quit");


        return io.readInt("Please select from the above choices.", 1, 6);
    }


    public void displayProductsHeader(){
        io.print("No\t\tProduct Type\t\t\t\tCost per Square Feet\t\t\t\t Labor cost per square Foot");
        io.print("----------------------------------------------");
    }

    public void displayProduct(Products product ){
        io.print(product.getProductType()+ "\t\t"+ product.getCostPerSquareFoot()+ "\t\t\t"+product.getLaborCostPerSquareFoot());
    }

    public BigDecimal promptUserInput(){
        return io.readBigDecimal("\nPlease select a number: ");

    }


    public void displayOrder(Orders order){
        io.print(order.getOrderNumber()+ "\t\t"+ order.getCustomerName()+ "\t\t"+ order.getProductType()+
                "\t\t"+order.getState()+"\t\t"+order.getTaxRate()+"\t\t"+order.getProductType()+
                "\t\t"+order.getArea()+"\t\t"+order.getCostPerSquareFoot()+"\t\t"+order.getLaborCostPerSquareFoot()+
                "\t\t"+order.getMaterialCost()+"\t\t"+order.getLaborCost()+"\t\t"+order.getTax()+"\t\t"+order.getTotal());

    }
    public void displayErrorMessage(String message)
    {
        io.print("======Error======");
        io.readString(message);
    }

    public boolean toExit(){
        String answer = io.readString("Do you want to exit the Flooring App? (y/n) ").toLowerCase();
        if ( answer.startsWith("y")){
            return true;
        } else {
            return false;
        }
    }

    public void displayFinalMessage() {

        io.print("Thank you for using the flooring application!");
    }



    public LocalDate inputDate() {
        return io.readDate("Please enter a date. (MM-DD-YYYY)",
                LocalDate.of(2005, 1, 1), LocalDate.of(2050, 1, 31));
    }

    public void displayDateBanner(LocalDate date) {
        System.out.printf("=== Orders on %s ===\n", date);
    }

    public void displayDateOrders(List<Orders> orders) {

        for (Orders o : orders) {
            io.print(o.getOrderNumber() + " | " + o.getCustomerName() + " | "
                    + io.formatCurrency(o.getTotal()));
        }
    }

    public void displayContinue() {
        io.readString("Please hit enter to continue.");
    }

    public Orders getOrder() {
        Orders order = new Orders();
        order.setDate(inputDate());
        order.setCustomerName(inputCustomerName());
        order.setState(inputState());
        order.setProductType(inputProductType());
        order.setArea(inputArea());
        return order;
    }

    private BigDecimal inputArea() {
        return io.readBigDecimal("Please enter the area of your project "
                + "in square feet.");
    }

    private String inputProductType() {
        return io.readString("Please enter the product type.");
    }

    private String inputState() {
        return io.readString("Please enter your state's USPS abbreviation. "
                + "(Ex. MD)");
    }

    private String inputCustomerName() {
        return io.readString("Please enter customer's name.");
    }

    public String askSave() {
        return io.readString("Would you like to save this order? Y/N");
    }

    public void displayAddOrderSuccess(boolean b, Orders order) {
        if (b == true) {
            io.print("Order #" + order.getOrderNumber() + " was successfully added!");
        } else {
            io.print("Order was not saved.");
            displayContinue();
        }
    }

    public void displayEditOrderBanner() {
        io.print("=== Edit Order ===");
    }

    public String inputOrderNumber() {
        return io.readString("Please enter an order number.");
    }

    public Orders editOrderInfo(Orders savedOrder) {
        Orders editedOrder = new Orders();

        io.print("Customer: " + savedOrder.getCustomerName());
        editedOrder.setCustomerName(inputCustomerName());

        io.print("State: " + savedOrder.getState());
        editedOrder.setState(inputState());

        io.print("Product: " + savedOrder.getProductType());
        editedOrder.setProductType(inputProductType());

        io.print("Area: " + savedOrder.getArea() + " sq ft");
        editedOrder.setArea(inputArea());

        return editedOrder;
    }

    public void displayEditOrderSuccess(boolean b, Orders updatedOrder) {
        if (b == true) {
            io.print("Order #" + updatedOrder.getOrderNumber() + " was successfully edited!");
        } else {
            io.print("Order was not edited.");
            displayContinue();
        }
    }

    public void displayRemoveOrderBanner() {
        io.print("=== Remove Order ===");
    }

    public String askRemove() {
        return io.readString("Would you like to remove this order? Y/N");
    }

    public void displayRemoveOrderSuccess(boolean b, Orders order) {
        if (b == true) {
            io.print("Order #" + order.getOrderNumber() + " was successfully removed!");
        } else {
            io.print("Order was not removed.");
            displayContinue();
        }
    }
}
