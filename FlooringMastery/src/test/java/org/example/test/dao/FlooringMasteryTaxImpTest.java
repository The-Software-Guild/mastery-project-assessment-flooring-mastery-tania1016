package org.example.test.dao;

import org.example.dao.FlooringMasteryTaxDao;
import org.example.dao.FlooringMasteryTaxDaoImp;
import org.example.model.Tax;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlooringMasteryTaxImpTest {


    FlooringMasteryTaxDao testDao = new FlooringMasteryTaxDaoImp();
    @Test
    public void getTaxTest() throws Exception{
        Tax expectedTax = new Tax();
        expectedTax.setStateName("Texas");
        expectedTax.setTaxRate(new BigDecimal("4.45"));
        expectedTax.setStateAbbreviation("TX");
        Tax resultTax = testDao.getTax("TX");
        assertEquals(expectedTax, resultTax, " The Product should be equal");
        assertEquals(expectedTax.getStateAbbreviation(), resultTax.getStateAbbreviation(), "Checking State Abbreviation");
        assertEquals(expectedTax.getStateName(), resultTax.getStateName(), "Checking State Name");
        assertEquals(expectedTax.getTaxRate(), resultTax.getTaxRate(), "Checking Tax Rate");

    }


}
