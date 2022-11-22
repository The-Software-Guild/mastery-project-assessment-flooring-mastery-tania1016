package org.example.dao;

import org.example.model.Orders;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class FlooringMasteryOrderDaoImp implements  FlooringMasteryOrderDao{


    private int lastOrderNumber;
    private static final String HEADER = "OrderNumber,CustomerName,State,TaxRate,"
            + "ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,"
            + "MaterialCost,LaborCost,Tax,Total";
    private static final String DELIMITER = ",";
    private String ORDERS_FILE = "orders/";
    

    public FlooringMasteryOrderDaoImp(String ORDERS_FILE) {
        this.ORDERS_FILE = ORDERS_FILE;

    }

    public FlooringMasteryOrderDaoImp() {

    }


    @Override
    public List<Orders> getAllOrders(LocalDate date) throws DataPersistenceException{
        return loadOrdersFromFile(date);
    }

    @Override
    public Orders addOrder(Orders order) throws DataPersistenceException{
        //Checks input for commas
        order = cleanFields(order);
        //Getting the last used number, adding one, and saving it
        readLastOrderNumber();
        lastOrderNumber++;
        order.setOrderNumber(lastOrderNumber);
        writeLastOrderNumber(lastOrderNumber);

        List<Orders> orders = loadOrdersFromFile(order.getDate());
        orders.add(order);
        writeOrdersToFile(orders, order.getDate());

        return order;
    }

    private void writeLastOrderNumber(int lastOrderNumber) throws DataPersistenceException{
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(ORDERS_FILE + "LastOrderNumber.txt"));
        } catch (IOException e) {
            throw new DataPersistenceException(
                    "Could not save order number.", e);
        }

        out.println(lastOrderNumber);

        out.flush();

        out.close();
    }


    private void readLastOrderNumber() throws DataPersistenceException{
        Scanner scanner;

        try {
            //Create Scanner to read file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(ORDERS_FILE + "LastOrderNumber.txt")));
        } catch (FileNotFoundException e) {
            //Throwing a general exception to the calling code
            throw new DataPersistenceException(
                    "-_- Could not load order number into memory.", e);
        }

        int savedOrderNumber = Integer.parseInt(scanner.nextLine());

        this.lastOrderNumber = savedOrderNumber;

        scanner.close();

    }

    @Override
    public Orders removeOrder(Orders order) throws DataPersistenceException {


        String orderNumber = order.getOrderNumber();

            List<Orders> orders = loadOrdersFromFile(order.getDate());
            Orders removedOrder = orders.stream()
                    .filter(o -> o.getOrderNumber().equals(orderNumber))
                    .findFirst().orElse(null);

            if (removedOrder != null) {
                orders.remove(removedOrder);
                writeOrdersToFile(orders, order.getDate());
                return removedOrder;
            } else {
                return null;
            }
    }

    @Override
    public Orders editOrder(Orders order) throws DataPersistenceException {
            order = cleanFields(order);
            String orderNumber =order.getOrderNumber();

            List<Orders> orders = loadOrdersFromFile(order.getDate());
            Orders chosenOrder = orders.stream()
                    .filter(o -> o.getOrderNumber().equals(orderNumber))
                    .findFirst().orElse(null);

            if (chosenOrder != null) {
                int index = orders.indexOf(chosenOrder);
                orders.set(index, order);
                writeOrdersToFile(orders,order.getDate());
                return order;
            } else {
                return null;
            }
        }

    private Orders cleanFields(Orders order) {
        //Dao does not know what the other layers are doing so its clearing delimiters
        String cleanCustomerName = order.getCustomerName().replace(DELIMITER, "");
        String cleanStateAbbr = order.getState().replace(DELIMITER, "");
        String cleanProductType = order.getProductType().replace(DELIMITER, "");

        order.setCustomerName(cleanCustomerName);
        order.setState(cleanStateAbbr);
        order.setProductType(cleanProductType);

        return order;
    }
    @Override
    public List<Orders> loadOrdersFromFile(LocalDate date) throws DataPersistenceException {
        Scanner scanner;
        String fileDate = date.format(DateTimeFormatter.ofPattern("MMddyyyy"));

        File f = new File(String.format(ORDERS_FILE + "Orders_%s.txt", fileDate));

        List<Orders> orders = new ArrayList<>();

        if (f.isFile()) {
            try {
                scanner = new Scanner(
                        new BufferedReader(
                                new FileReader(f)));
            } catch (FileNotFoundException e) {
                throw new DataPersistenceException(
                        "-_- Could not load order data into memory.", e);
            }
            String currentLine;
            String[] currentTokens;
            scanner.nextLine();//Skips scanning document headers
            while (scanner.hasNextLine()) {
                currentLine = scanner.nextLine();
                currentTokens = currentLine.split(DELIMITER);
                if (currentTokens.length == 12) {
                    Orders currentOrder = new Orders();
                    currentOrder.setDate(LocalDate.parse(fileDate,
                            DateTimeFormatter.ofPattern("MMddyyyy")));
                    currentOrder.setOrderNumber(Integer.parseInt(currentTokens[0]));
                    currentOrder.setCustomerName(currentTokens[1]);
                    currentOrder.setState(currentTokens[2]);
                    currentOrder.setTaxRate(new BigDecimal(currentTokens[3]));
                    currentOrder.setProductType(currentTokens[4]);
                    currentOrder.setArea(new BigDecimal(currentTokens[5]));
                    currentOrder.setCostPerSquareFoot(new BigDecimal(currentTokens[6]));
                    currentOrder.setLaborCostPerSquareFoot(new BigDecimal(currentTokens[7]));
                    currentOrder.setMaterialCost(new BigDecimal(currentTokens[8]));
                    currentOrder.setLaborCost(new BigDecimal(currentTokens[9]));
                    currentOrder.setTax(new BigDecimal(currentTokens[10]));
                    currentOrder.setTotal(new BigDecimal(currentTokens[11]));
                    orders.add(currentOrder);
                } else {
                    //Ignore line.
                }
            }
            scanner.close();
            return orders;
        } else {
            return orders;
        }
    }

    @Override
    public void writeOrdersToFile(List<Orders> orders, LocalDate orderDate) throws DataPersistenceException {


            PrintWriter out;

            String fileDate = orderDate.format(DateTimeFormatter
                    .ofPattern("MMddyyyy"));

            File f = new File(String.format(ORDERS_FILE + "Orders_%s.txt", fileDate));

            try {
                out = new PrintWriter(new FileWriter(f));
            } catch (IOException e) {
                throw new DataPersistenceException(
                        "Could not save order data.", e);
            }

            // Write out the Order objects to the file.
            out.println(HEADER);
            for (Orders currentOrder : orders) {
                // Write the Order objects to the file
                out.println(currentOrder.getOrderNumber() + DELIMITER
                        + currentOrder.getCustomerName() + DELIMITER
                        + currentOrder.getState() + DELIMITER
                        + currentOrder.getTaxRate() + DELIMITER
                        + currentOrder.getProductType() + DELIMITER
                        + currentOrder.getArea() + DELIMITER
                        + currentOrder.getMaterialCost() + DELIMITER
                        + currentOrder.getLaborCostPerSquareFoot() + DELIMITER
                        + currentOrder.getMaterialCost() + DELIMITER
                        + currentOrder.getLaborCost() + DELIMITER
                        + currentOrder.getTax() + DELIMITER
                        + currentOrder.getTotal());

                //Force PrintWriter to write line to the file
                out.flush();
            }
            //Clean up
            out.close();
        }


}
