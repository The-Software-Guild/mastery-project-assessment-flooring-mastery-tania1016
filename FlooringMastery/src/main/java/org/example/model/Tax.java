package org.example.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Tax {

    private String stateAbbreviation;
    private String stateName;
    private BigDecimal taxRate;

    public Tax() {
    }

    public Tax(String stateAbbreviation, String stateName, BigDecimal taxRate) {
        this.stateAbbreviation = stateAbbreviation;
        this.stateName = stateName;
        this.taxRate = taxRate;
    }

    public String getStateAbbreviation() {
        return stateAbbreviation;
    }

    public void setStateAbbreviation(String stateAbbreviation) {

        this.stateAbbreviation = stateAbbreviation;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tax tax = (Tax) o;
        if (!Objects.equals(stateAbbreviation, tax.stateAbbreviation))
            return false;
        if (!Objects.equals(stateName, tax.stateName)) return false;
        return Objects.equals(taxRate, tax.taxRate);
    }

    @Override
    public int hashCode() {
        int result = stateAbbreviation != null ? stateAbbreviation.hashCode() : 0;
        result = 31 * result + (stateName != null ? stateName.hashCode() : 0);
        result = 31 * result + (taxRate != null ? taxRate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Tax{" +
                "stateAbbreviation='" + stateAbbreviation + '\'' +
                ", stateName='" + stateName + '\'' +
                ", taxRate=" + taxRate +
                '}';
    }
}


