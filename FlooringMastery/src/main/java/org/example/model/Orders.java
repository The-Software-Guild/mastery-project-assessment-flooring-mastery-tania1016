package org.example.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Orders {


    private LocalDate date;
    private int orderNumber;
    private String customerName;
    private String state ; // Tax
    private BigDecimal taxRate; //  Tax
    private String productType; // Product
    private BigDecimal area;
    private BigDecimal costPerSquareFoot; // Product
    private BigDecimal laborCostPerSquareFoot; // Product
    private BigDecimal materialCost;
    private BigDecimal laborCost;
    private BigDecimal tax; // Tax ( ?)
    private BigDecimal total;

    public Orders() {
    }

    public Orders(LocalDate date, BigDecimal taxRate, String productType, BigDecimal area, BigDecimal costPerSquareFoot, BigDecimal laborCostPerSquareFoot, BigDecimal materialCost, BigDecimal laborCost, BigDecimal tax, BigDecimal total, int orderNumber, String customerName, String state) {
        this.date=date;
        this.taxRate = taxRate;
        this.productType = productType;
        this.area = area;
        this.costPerSquareFoot = costPerSquareFoot;
        this.laborCostPerSquareFoot = laborCostPerSquareFoot;
        this.materialCost = materialCost;
        this.laborCost = laborCost;
        this.tax = tax;
        this.total = total;
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.state = state;
    }


    public String getOrderNumber() {
        return Integer.toString(orderNumber);
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {

        this.state = state;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
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

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost;
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Orders orders = (Orders) o;

        if (orderNumber != orders.orderNumber) return false;
        if (!Objects.equals(date, orders.date)) return false;
        if (!Objects.equals(customerName, orders.customerName))
            return false;
        if (!Objects.equals(state, orders.state)) return false;
        if (!Objects.equals(taxRate, orders.taxRate)) return false;
        if (!Objects.equals(productType, orders.productType)) return false;
        if (!Objects.equals(area, orders.area)) return false;
        if (!Objects.equals(costPerSquareFoot, orders.costPerSquareFoot))
            return false;
        if (!Objects.equals(laborCostPerSquareFoot, orders.laborCostPerSquareFoot))
            return false;
        if (!Objects.equals(materialCost, orders.materialCost))
            return false;
        if (!Objects.equals(laborCost, orders.laborCost)) return false;
        if (!Objects.equals(tax, orders.tax)) return false;
        return Objects.equals(total, orders.total);
    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + orderNumber;
        result = 31 * result + (customerName != null ? customerName.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (taxRate != null ? taxRate.hashCode() : 0);
        result = 31 * result + (productType != null ? productType.hashCode() : 0);
        result = 31 * result + (area != null ? area.hashCode() : 0);
        result = 31 * result + (costPerSquareFoot != null ? costPerSquareFoot.hashCode() : 0);
        result = 31 * result + (laborCostPerSquareFoot != null ? laborCostPerSquareFoot.hashCode() : 0);
        result = 31 * result + (materialCost != null ? materialCost.hashCode() : 0);
        result = 31 * result + (laborCost != null ? laborCost.hashCode() : 0);
        result = 31 * result + (tax != null ? tax.hashCode() : 0);
        result = 31 * result + (total != null ? total.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "date=" + date +
                ", orderNumber=" + orderNumber +
                ", customerName='" + customerName + '\'' +
                ", state='" + state + '\'' +
                ", taxRate=" + taxRate +
                ", productType='" + productType + '\'' +
                ", area=" + area +
                ", costPerSquareFoot=" + costPerSquareFoot +
                ", laborCostPerSquareFoot=" + laborCostPerSquareFoot +
                ", materialCost=" + materialCost +
                ", laborCost=" + laborCost +
                ", tax=" + tax +
                ", total=" + total +
                '}';
    }
}
