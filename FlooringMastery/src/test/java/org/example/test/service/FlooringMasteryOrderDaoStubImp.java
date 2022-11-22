package org.example.test.service;

import org.example.dao.DataPersistenceException;
import org.example.dao.FlooringMasteryOrderDao;
import org.example.model.Orders;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FlooringMasteryOrderDaoStubImp implements FlooringMasteryOrderDao {

    private Orders order;
    private List<Orders> ordersList = new ArrayList<>();

    public  FlooringMasteryOrderDaoStubImp() {

        order = new Orders();
        order.setDate(LocalDate.parse("01041999",
                DateTimeFormatter.ofPattern("MMddyyyy")));
        order.setOrderNumber(200);
        order.setCustomerName("Order Dao Customer");
        order.setState("KY");
        order.setTaxRate(new BigDecimal("6.00"));
        order.setProductType("Laminate");
        order.setArea(new BigDecimal("100"));
        order.setCostPerSquareFoot(new BigDecimal("1.75"));
        order.setLaborCostPerSquareFoot(new BigDecimal("2.10"));
        order.setMaterialCost(order.getCostPerSquareFoot()
                .multiply(order.getArea()).setScale(2, RoundingMode.HALF_UP));
        order.setLaborCost(order.getLaborCostPerSquareFoot().multiply(order.getArea())
                .setScale(2, RoundingMode.HALF_UP));
        order.setTax(order.getTaxRate().divide(new BigDecimal("100.00"))
                .multiply((order.getMaterialCost().add(order.getLaborCost())))
                .setScale(2, RoundingMode.HALF_UP));
        order.setTotal(order.getMaterialCost().add(order.getLaborCost())
                .add(order.getTax()));

        ordersList.add(order);

    }

    @Override
    public List<Orders> getAllOrders(LocalDate date) throws DataPersistenceException {
        if (date.equals(order.getDate())) {
            return ordersList;
        } else {
            //Should return an empty list like the dao does.
            return new ArrayList<>();
        }
    }

    @Override
    public Orders addOrder(Orders order) throws DataPersistenceException {
        ordersList.add(order);
        return order;
    }

    @Override
    public Orders editOrder(Orders editedOrder) throws DataPersistenceException {
        if (editedOrder.getOrderNumber() == order.getOrderNumber()) {
            return order;
        } else {
            return null;
        }
    }

    @Override
    public Orders removeOrder(Orders removedOrder) throws DataPersistenceException {
        if (removedOrder.equals(order)) {
            return order;
        } else {
            return null;
        }
    }

    public List<Orders> loadOrdersFromFile(LocalDate date) throws DataPersistenceException{
        if (date.equals(order.getDate())) {
            return ordersList;
        } else {
            //Should return an empty list like the dao does.
            return new ArrayList<>();
        }
}
    public void writeOrdersToFile(List<Orders> orders, LocalDate date) throws DataPersistenceException{
      // do nothing
    }

}
