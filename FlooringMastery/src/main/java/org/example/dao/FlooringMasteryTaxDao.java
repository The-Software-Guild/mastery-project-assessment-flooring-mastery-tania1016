package org.example.dao;

import org.example.model.Tax;

import java.util.List;

public interface FlooringMasteryTaxDao {

    Tax getTax(String state) throws DataPersistenceException;

}
