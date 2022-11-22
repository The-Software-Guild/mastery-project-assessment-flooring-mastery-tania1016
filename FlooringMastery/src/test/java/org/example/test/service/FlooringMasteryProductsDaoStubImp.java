package org.example.test.service;

import org.example.dao.DataPersistenceException;
import org.example.dao.FlooringMasteryProductsDao;
import org.example.model.Products;

import java.math.BigDecimal;


public class FlooringMasteryProductsDaoStubImp implements FlooringMasteryProductsDao {

    private Products product;


    public FlooringMasteryProductsDaoStubImp(){
        product.setProductType("Laminate");
        product.setCostPerSquareFoot(new BigDecimal("1.75"));
        product.setCostPerSquareFoot(new BigDecimal("2.10"));
    }
    @Override
    public Products getProduct(String productType) throws DataPersistenceException{
        return product;
    }
}
