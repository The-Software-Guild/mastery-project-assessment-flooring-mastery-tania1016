package org.example.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Products {


    private String productType;
    private BigDecimal costPerSquareFoot;
    private BigDecimal laborCostPerSquareFoot;

    public Products() {
    }

    public Products(String productType, BigDecimal costPerSquareFoot, BigDecimal laborCostPerSquareFoot) {
        this.productType = productType;
        this.costPerSquareFoot = costPerSquareFoot;
        this.laborCostPerSquareFoot = laborCostPerSquareFoot;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getCostPerSquareFoot() {
        return costPerSquareFoot;
    }

    public void setCostPerSquareFoot(BigDecimal costPerSquareFoot) {
        this.costPerSquareFoot = costPerSquareFoot;
    }

    public BigDecimal getLaborCostPerSquareFoot() {
        return laborCostPerSquareFoot;
    }

    public void setLaborCostPerSquareFoot(BigDecimal laborCostPerSquareFoot) {
        this.laborCostPerSquareFoot = laborCostPerSquareFoot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Products products = (Products) o;

        if (!Objects.equals(productType, products.productType))
            return false;
        if (!Objects.equals(costPerSquareFoot, products.costPerSquareFoot))
            return false;
        return Objects.equals(laborCostPerSquareFoot, products.laborCostPerSquareFoot);
    }

    @Override
    public int hashCode() {
        int result = productType != null ? productType.hashCode() : 0;
        result = 31 * result + (costPerSquareFoot != null ? costPerSquareFoot.hashCode() : 0);
        result = 31 * result + (laborCostPerSquareFoot != null ? laborCostPerSquareFoot.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Products{" +
                "productType='" + productType + '\'' +
                ", costPerSquareFoot=" + costPerSquareFoot +
                ", laborCostPerSquareFoot=" + laborCostPerSquareFoot +
                '}';
    }
}
