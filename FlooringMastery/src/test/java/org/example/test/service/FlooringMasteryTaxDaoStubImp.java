package org.example.test.service;

import org.example.dao.DataPersistenceException;
import org.example.dao.FlooringMasteryTaxDao;
import org.example.model.Tax;

import java.math.BigDecimal;

public class FlooringMasteryTaxDaoStubImp implements FlooringMasteryTaxDao {

    private Tax tax;


    public FlooringMasteryTaxDaoStubImp(){
        tax.setStateAbbreviation("KY");
        tax.setTaxRate(new BigDecimal("6.00"));
        tax.setStateName("Kentucky");
    }
    @Override
    public Tax getTax(String stateAbb) throws DataPersistenceException {
        return tax;
    }


}
