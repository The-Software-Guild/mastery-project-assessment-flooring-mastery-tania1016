package org.example.test.dao;

import org.example.dao.FlooringMasteryProductsDao;
import org.example.dao.FlooringMasteryProductsDaoImp;
import org.example.model.Products;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

public class FlooringMasteryProductImpTest {

    FlooringMasteryProductsDao testDao = new FlooringMasteryProductsDaoImp();

    @Test
    public void getProductTest() throws Exception{
        Products product = new Products();
        product.setProductType("Carpet");
        product.setCostPerSquareFoot(new BigDecimal("2.25"));
        product.setLaborCostPerSquareFoot(new BigDecimal("2.10"));
        Products result = testDao.getProduct("Carpet");
        assertEquals(product, result, " The Product should be equal");
        assertEquals(product.getProductType(), result.getProductType(), "Checking Product Type");
        assertEquals(product.getLaborCostPerSquareFoot(), result.getLaborCostPerSquareFoot(), "Checking Labor Cost");
        assertEquals(product.getCostPerSquareFoot(), result.getCostPerSquareFoot(), "Checking Cost per Square foot");

    }
}
