package org.example.dao;

import org.example.model.Orders;

import javax.xml.crypto.Data;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface FlooringMasteryOrderDao {

    List<Orders> getAllOrders(LocalDate date) throws DataPersistenceException;

    Orders addOrder(Orders order) throws DataPersistenceException;

    Orders removeOrder(Orders order) throws DataPersistenceException;

    Orders editOrder(Orders order) throws DataPersistenceException;

    List<Orders> loadOrdersFromFile(LocalDate date) throws DataPersistenceException;

    void writeOrdersToFile(List<Orders> orders, LocalDate date) throws DataPersistenceException;
}
