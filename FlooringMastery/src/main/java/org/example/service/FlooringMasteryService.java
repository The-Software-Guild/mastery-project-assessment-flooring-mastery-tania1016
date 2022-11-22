package org.example.service;

import org.example.dao.DataPersistenceException;
import org.example.model.Orders;

import java.time.LocalDate;
import java.util.List;

public interface FlooringMasteryService {

    List<Orders> getOrders(LocalDate date) throws InvalidOrderNumberException,
            DataPersistenceException;

    Orders calculateOrders(Orders orders) throws DataPersistenceException,
            OrderValidationException, StateTaxValidationException, ProductValidationException;

    Orders getOrder(LocalDate date, String orderNumber) throws
            DataPersistenceException, InvalidOrderNumberException;

    Orders addOrder(Orders order) throws DataPersistenceException;

    Orders compareOrders(Orders savedOrder, Orders editedOrder)
            throws DataPersistenceException, StateTaxValidationException,
            ProductValidationException;

    Orders editOrder(Orders updatedOrder) throws DataPersistenceException,
            InvalidOrderNumberException;

    Orders removeOrder(Orders removedOrder) throws DataPersistenceException,
            InvalidOrderNumberException;
}
