package org.example.test.dao;

import org.example.dao.FlooringMasteryOrderDao;
import org.example.dao.FlooringMasteryOrderDaoImp;
import org.example.model.Orders;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FlooringMasteryOrderImpTest {

    String testFile = "orders/";
    FlooringMasteryOrderDao testDao = new FlooringMasteryOrderDaoImp(testFile);

    public FlooringMasteryOrderImpTest() {
    }

    @BeforeEach
    public void setUp() throws Exception {

    }

    @Test
    public void testLoadOrdersFromFile() throws Exception {

        LocalDate date = LocalDate.parse("01011999",
                DateTimeFormatter.ofPattern("MMddyyyy"));
        System.out.println("loadOrderFromFile");
        Orders o1 = new Orders();
        o1.setDate(date);
        o1.setOrderNumber(101);
        o1.setCustomerName("Test Customer");
        o1.setState("CA");
        o1.setTaxRate(new BigDecimal("25.00"));
        o1.setProductType("Tile");
        o1.setArea(new BigDecimal("249.00"));
        o1.setCostPerSquareFoot(new BigDecimal("217003.50"));
        o1.setLaborCostPerSquareFoot(new BigDecimal("4.15"));
        o1.setMaterialCost(new BigDecimal("217003.50"));
        o1.setLaborCost(new BigDecimal("1033.35"));
        o1.setTax(new BigDecimal("54509.21"));
        o1.setTotal(new BigDecimal("272546.06"));
        List<Orders> result = testDao.loadOrdersFromFile(date);
        List<Orders> expResult = new ArrayList<>();
        expResult.add(o1);
        assertEquals(expResult, result, "Load Orders from file");
    }


    @Test
    public void testRemoveOrder() throws Exception{
        System.out.println("removeOrder");
        LocalDate date = LocalDate.parse("01021999",
                DateTimeFormatter.ofPattern("MMddyyyy"));
        Orders o1 = new Orders();
        o1.setDate(date);
        o1.setOrderNumber(16);
        o1.setCustomerName("Test Customer 2");
        o1.setState("WA");
        o1.setTaxRate(new BigDecimal("9.25"));
        o1.setProductType("Wood");
        o1.setArea(new BigDecimal("243"));
        o1.setCostPerSquareFoot(new BigDecimal("1251.45"));
        o1.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        o1.setMaterialCost(new BigDecimal("1251.45"));
        o1.setLaborCost(new BigDecimal("1154.25"));
        o1.setTax(new BigDecimal("216.51"));
        o1.setTotal(new BigDecimal("2622.21"));

        Orders o2 = new Orders();
        o2.setDate(date);
        o2.setOrderNumber(17);
        o2.setCustomerName("Test Customer 3");
        o2.setState("WA");
        o2.setTaxRate(new BigDecimal("9.25"));
        o2.setProductType("Wood");
        o2.setArea(new BigDecimal("243"));
        o2.setCostPerSquareFoot(new BigDecimal("1251.45"));
        o2.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        o2.setMaterialCost(new BigDecimal("1251.45"));
        o2.setLaborCost(new BigDecimal("1154.25"));
        o2.setTax(new BigDecimal("216.51"));
        o2.setTotal(new BigDecimal("2622.21"));

        testDao.addOrder(o1);
        testDao.addOrder(o2);

        List<Orders> initialOrders = testDao.getAllOrders(date);
        Orders removedOrder = testDao.removeOrder(o2);

        List<Orders> finalOrders = testDao.getAllOrders(date);

        assertEquals(removedOrder, o2, "The removed order should be order 103");

        assertNotNull(finalOrders, "The list of products should not be null");
        assertEquals(1, finalOrders.size(), "List of orders should have 1 order.");

        removedOrder = testDao.removeOrder(o1);
        assertEquals(removedOrder, o1, "The removed order should be order 102");

         finalOrders = testDao.getAllOrders(date);
        assertEquals(0, finalOrders.size(), "List of orders should have zero orders.");

    }
    @Test
    public void testAddGetProduct() throws Exception{
        System.out.println("addProduct");
        LocalDate date = LocalDate.parse("01011999",
                DateTimeFormatter.ofPattern("MMddyyyy"));
        Orders o1 = new Orders();
        o1.setDate(date);
        o1.setOrderNumber(102);
        o1.setCustomerName("Test Customer 2");
        o1.setState("WA");
        o1.setTaxRate(new BigDecimal("9.25"));
        o1.setProductType("Wood");
        o1.setArea(new BigDecimal("243"));
        o1.setCostPerSquareFoot(new BigDecimal("1251.45"));
        o1.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        o1.setMaterialCost(new BigDecimal("1251.45"));
        o1.setLaborCost(new BigDecimal("1154.25"));
        o1.setTax(new BigDecimal("216.51"));
        o1.setTotal(new BigDecimal("2622.21"));
        Orders result = testDao.addOrder(o1);
        assertEquals(o1, result, "Add Order to file");
        assertEquals(o1.getCustomerName(), result.getCustomerName(), "Checking Customer Name");
        assertEquals(o1.getOrderNumber(), result.getOrderNumber(), " Checking Order Number");
        assertEquals(o1.getDate(), result.getDate(), " Checking Date");
        assertEquals(o1.getTaxRate(), result.getTaxRate(), "Checking Tax Rate");

    }

    @Test
    public void testEditOrder() throws Exception {
        LocalDate date = LocalDate.parse("01031999",
                DateTimeFormatter.ofPattern("MMddyyyy"));
        System.out.println("testEditOrder");
        Orders o1 = new Orders();
        o1.setDate(date);
        o1.setOrderNumber(35);
        o1.setCustomerName("Test Customer 1");
        o1.setState("CA");
        o1.setTaxRate(new BigDecimal("25.00"));
        o1.setProductType("Tile");
        o1.setArea(new BigDecimal("249.00"));
        o1.setCostPerSquareFoot(new BigDecimal("217003.50"));
        o1.setLaborCostPerSquareFoot(new BigDecimal("4.15"));
        o1.setMaterialCost(new BigDecimal("217003.50"));
        o1.setLaborCost(new BigDecimal("1033.35"));
        o1.setTax(new BigDecimal("54509.21"));
        o1.setTotal(new BigDecimal("272546.06"));
        testDao.addOrder(o1);
        o1.setCustomerName("Edited Customer Name");
        Orders editedOrder = testDao.editOrder(o1);
        List<Orders> expResult = new ArrayList<>();
        expResult.add(o1);
        List<Orders> result = testDao.getAllOrders(date);
        assertEquals(expResult, result, "Orders should be the same");

        assertEquals(o1, editedOrder, "Updated Order Name should be Edited.");
    }

}
