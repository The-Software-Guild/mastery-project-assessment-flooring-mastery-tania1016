package org.example.test.service;

import org.example.dao.FlooringMasteryProductsDao;
import org.example.dao.FlooringMasteryProductsDaoImp;
import org.example.dao.FlooringMasteryTaxDao;
import org.example.dao.FlooringMasteryTaxDaoImp;
import org.example.model.Orders;
import org.example.service.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

public class FlooringMasteryServiceImpTest {
	
    private FlooringMasteryService testService;

    public FlooringMasteryServiceImpTest() {
        /*FlooringMasteryOrderDaoStubImp ordersDao = new FlooringMasteryOrderDaoStubImp();
        FlooringMasteryProductsDao productsDao = new FlooringMasteryProductsDaoImp();
        FlooringMasteryTaxDao taxDao = new FlooringMasteryTaxDaoImp();
        testService = new FlooringMasteryServiceImp(ordersDao, productsDao, taxDao); */
       ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        testService = ctx.getBean("serviceTest", FlooringMasteryService.class);
    }

    @Test
    public void testGetOrders() throws Exception {

        assertEquals(1, testService.getOrders(LocalDate.of(1999, 01, 04)).size());

        try {
            List<Orders> orders = testService.getOrders(LocalDate.of(1800, 01, 01));
            fail("Expected InvalidOrderNumberException was not thrown.");
        } catch (InvalidOrderNumberException e) {
        }
    }


    @Test
    public void testGetOrder() throws Exception {
        Orders order = testService.getOrder(LocalDate.of(1999, 01, 04), "200");
        assertNotNull(order);

        try {
            order = testService.getOrder(LocalDate.of(1991, 01, 04), "0");
            fail("Expected InvalidOrderNumberException was not thrown.");
        } catch (InvalidOrderNumberException e) {
        }

        try {
            testService.getOrder(LocalDate.of(1800, 01, 01), "19");
            fail("Expected InvalidOrderNumberException was not thrown.");
        } catch (InvalidOrderNumberException e) {
        }

    }


    @Test
    public void testCalculateOrder() throws Exception {

        Orders order1 = new Orders();
        order1.setCustomerName("Order Dao Customer");
        order1.setState("KY");
        order1.setProductType("Laminate");
        order1.setArea(new BigDecimal("100"));

        Orders order2 = new Orders();
        order2.setCustomerName("Order Dao Customer");
        order2.setState("KY");
        order2.setProductType("Laminate");
        order2.setArea(new BigDecimal("100"));

        assertEquals(testService.calculateOrders(order1), testService.calculateOrders(order2));

        order1.setCustomerName("");

        try {
            testService.calculateOrders(order1);
            fail("Expected OrderValidationException was not thrown.");
        } catch (OrderValidationException e) {
        }

        order1.setCustomerName("Order Dao Customer");
        order1.setState("");

        try {
            testService.calculateOrders(order1);
            fail("Expected OrderValidationException was not thrown.");
        } catch (OrderValidationException e) {
        }

        order1.setState("KY");
        order1.setProductType("");

        try {
            testService.calculateOrders(order1);
            fail("Expected OrderValidationException was not thrown.");
        } catch (OrderValidationException e) {
        }

        order1.setProductType("Laminate");
        order1.setArea(new BigDecimal("0"));

        try {
            testService.calculateOrders(order1);
            fail("Expected OrderValidationException was not thrown.");
        } catch (OrderValidationException e) {
        }

        order1.setArea(new BigDecimal("100"));
        order1.setState("MD");

        try {
            testService.calculateOrders(order1);
            fail("Expected StateTaxValidationException was not thrown.");
        } catch (StateTaxValidationException e) {
        }

        order1.setState("KY");
        order1.setProductType("Glass");

        try {
            testService.calculateOrders(order1);
            fail("Expected ProductValidationException was not thrown.");
        } catch (ProductValidationException e) {
        }

    }

    @Test
    public void testAddOrder() throws Exception {

        Orders order = new Orders();
        order.setCustomerName("Order Dao Customer");
        order.setState("KY");
        order.setProductType("Laminate");
        order.setArea(new BigDecimal("100"));
        testService.addOrder(order);

        assertEquals(order, testService.addOrder(order), "The orders should be equal");

    }


    @Test
    public void testCompareOrders() throws Exception {

        Orders savedOrder = testService.getOrder(LocalDate.of(1999, 01, 04), "200");

        Orders editedOrder = new Orders();
        editedOrder.setCustomerName("New Edited Name");

        Orders updatedOrder = testService.compareOrders(savedOrder, editedOrder);

        assertEquals(updatedOrder, savedOrder, "The new name should be New Edited Name");

    }


    @Test
    public void testEditOrder() throws Exception {

        Orders savedOrder = testService.getOrder(LocalDate.of(1999, 01, 04), "200");
        assertNotNull(savedOrder);

        try {
            savedOrder = testService.getOrder(LocalDate.of(1999, 01, 04), "0");
            fail("Expected InvalidOrderNumberException was not thrown.");
        } catch (InvalidOrderNumberException e) {
        }

    }


    @Test
    public void testRemoveOrder() throws Exception {

        Orders removedOrder = testService.getOrder(LocalDate.of(1999, 01, 04), "200");
        assertNotNull(removedOrder);

        try {
            removedOrder = testService.getOrder(LocalDate.of(2001, 04, 30), "0");
            fail("Expected InvalidOrderNumberException was not thrown.");
        } catch (InvalidOrderNumberException e) {

        }

    }
}
