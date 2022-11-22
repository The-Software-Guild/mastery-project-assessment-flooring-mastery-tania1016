package org.example.service;

import org.example.dao.DataPersistenceException;
import org.example.dao.FlooringMasteryOrderDao;
import org.example.dao.FlooringMasteryProductsDao;
import org.example.dao.FlooringMasteryTaxDao;
import org.example.model.Orders;
import org.example.model.Products;
import org.example.model.Tax;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;


public class FlooringMasteryServiceImp implements FlooringMasteryService{

    private FlooringMasteryOrderDao orderDao;
    private FlooringMasteryProductsDao productsDao;
    private FlooringMasteryTaxDao taxDao;

    public FlooringMasteryServiceImp(FlooringMasteryOrderDao orderDao, FlooringMasteryProductsDao productsDao,
                                     FlooringMasteryTaxDao taxDao) {
        this.orderDao = orderDao;
        this.productsDao = productsDao;
        this.taxDao = taxDao;

    }

    @Override
    public List<Orders> getOrders(LocalDate date) throws InvalidOrderNumberException, DataPersistenceException {
        List<Orders> ordersByDate = orderDao.getAllOrders(date);
        if (!ordersByDate.isEmpty()) {
            return ordersByDate;
        } else {
            throw new InvalidOrderNumberException("ERROR: No orders "
                    + "exist on that date.");
        }
    }

    @Override
    public Orders calculateOrders(Orders orders) throws DataPersistenceException, OrderValidationException, StateTaxValidationException, ProductValidationException {
            validateOrder(orders);
            calculateTax(orders);
            calculateMaterial(orders);
            calculateTotal(orders);

            return orders;
    }

    private void calculateTotal(Orders orders) {

        orders.setMaterialCost(orders.getCostPerSquareFoot().multiply(orders.getArea()).setScale(2, RoundingMode.HALF_UP));
        orders.setLaborCost(orders.getLaborCostPerSquareFoot().multiply(orders.getArea()).setScale(2, RoundingMode.HALF_UP));
        orders.setTax(orders.getTaxRate().divide(new BigDecimal("100.00")).multiply((orders.getCostPerSquareFoot().add(orders.getLaborCost()))).setScale(2, RoundingMode.HALF_UP));
        orders.setTotal(orders.getCostPerSquareFoot().add(orders.getLaborCost()).add(orders.getTax()));
    }

    private void calculateMaterial(Orders orders) throws DataPersistenceException,
            ProductValidationException {
            //Set product information in order.
            Products chosenProduct = productsDao.getProduct(orders.getProductType());
            if (chosenProduct == null) {
                throw new ProductValidationException("ERROR: We do not sell that "
                        + "product.");
            }
            orders.setProductType(chosenProduct.getProductType());
            orders.setCostPerSquareFoot(chosenProduct.getCostPerSquareFoot());
            orders.setLaborCostPerSquareFoot(chosenProduct.getLaborCostPerSquareFoot());
    }

    private void calculateTax(Orders orders) throws DataPersistenceException, StateTaxValidationException {

        Tax chosenState = taxDao.getTax(orders.getState());
        if (chosenState == null) {
            throw new StateTaxValidationException("ERROR: Company does not serve that state.");
        }
        orders.setState(chosenState.getStateAbbreviation());
        orders.setTaxRate(chosenState.getTaxRate());
    }

    private void validateOrder(Orders orders) throws OrderValidationException {
        String message = "";
        if (orders.getCustomerName().trim().isEmpty() || orders.getCustomerName() == null) {
            message += "Customer name is required.\n";
        }
        if (orders.getState().trim().isEmpty() || orders.getState() == null) {
            message += "State is required.\n";
        }
        if (orders.getProductType().trim().isEmpty() || orders.getProductType() == null) {
            message += "Product type is required.\n";
        }
        if (orders.getArea().compareTo(BigDecimal.ZERO) == 0 || orders.getArea() == null) {
            message += "Area square footage is required.";
        }
        if (!message.isEmpty()) {
            throw new OrderValidationException(message);
        }
    }

    @Override
    public Orders getOrder(LocalDate date, String orderNumber) throws DataPersistenceException, InvalidOrderNumberException {
        List<Orders> orders = getOrders(date);
        Orders order = orders.stream()
                .filter(o -> o.getOrderNumber().equals( orderNumber))
                .findFirst().orElse(null);
        if (order != null) {
            return order;
        } else {
            throw new InvalidOrderNumberException("ERROR: No orders with that number "
                    + "exist on that date.");
        }
    }

    @Override
    public Orders addOrder(Orders order) throws DataPersistenceException {
        orderDao.addOrder(order);
        return order;
    }

    @Override
    public Orders compareOrders(Orders savedOrder, Orders editedOrder) throws DataPersistenceException, StateTaxValidationException, ProductValidationException {
        if (editedOrder.getCustomerName() == null
                || editedOrder.getCustomerName().trim().equals("")) {
            //No change
        } else {
            savedOrder.setCustomerName(editedOrder.getCustomerName());
        }

        if (editedOrder.getState() == null
                || editedOrder.getState().trim().equals("")) {
        } else {
            savedOrder.setState(editedOrder.getState());
            calculateTax(savedOrder);
        }

        if (editedOrder.getProductType() == null
                || editedOrder.getProductType().equals("")) {
        } else {
            savedOrder.setProductType(editedOrder.getProductType());
            calculateMaterial(savedOrder);
        }

        if (editedOrder.getArea() == null
                || (editedOrder.getArea().compareTo(BigDecimal.ZERO)) == 0) {
        } else {
            savedOrder.setArea(editedOrder.getArea());
        }

        calculateTotal(savedOrder);

        return savedOrder;
    }

    @Override
    public Orders editOrder(Orders updatedOrder) throws DataPersistenceException, InvalidOrderNumberException {
        updatedOrder = orderDao.editOrder(updatedOrder);
        if (updatedOrder != null) {
            return updatedOrder;
        } else {
            throw new InvalidOrderNumberException("ERROR: No orders with that number "
                    + "exist on that date.");
        }
    }

    @Override
    public Orders removeOrder(Orders removedOrder) throws DataPersistenceException, InvalidOrderNumberException {
        removedOrder = orderDao.removeOrder(removedOrder);
        if (removedOrder != null) {
            return removedOrder;
        } else {
            throw new InvalidOrderNumberException("ERROR: No orders with that number "
                    + "exist on that date.");
        }

    }
}
