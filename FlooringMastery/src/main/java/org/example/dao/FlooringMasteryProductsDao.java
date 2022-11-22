package org.example.dao;

import org.example.model.Products;


import java.util.List;

public interface FlooringMasteryProductsDao {

    Products getProduct(String productType) throws DataPersistenceException;


}
