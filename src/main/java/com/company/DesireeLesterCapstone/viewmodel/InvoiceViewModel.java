package com.company.DesireeLesterCapstone.viewmodel;


import com.company.DesireeLesterCapstone.models.ProcessingFee;
import com.company.DesireeLesterCapstone.models.SalesTaxRate;

import java.math.BigDecimal;
import java.util.Objects;


public class InvoiceViewModel {
    private int id;
    private String name;
    private String street;
    private String city;
    private String state;
    private String zipcode;
    private String itemType;
    private int idemId;
    private BigDecimal unitPrice;
    private int quantity;
    private BigDecimal subtotal;
    private SalesTaxRate tax;
    private ProcessingFee processingFee;
    private BigDecimal total;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getZipcode() {
        return zipcode;
    }
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
    public String getItemType() {
        return itemType;
    }
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }
    public int getIdemId() {
        return idemId;
    }
    public void setIdemId(int idemId) {
        this.idemId = idemId;
    }
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public BigDecimal getSubtotal() {
        return subtotal;
    }
    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
    public SalesTaxRate getTax() {
        return tax;
    }
    public void setTax(SalesTaxRate tax) {
        this.tax = tax;
    }
    public ProcessingFee getProcessingFee() {
        return processingFee;
    }
    public void setProcessingFee(ProcessingFee processingFee) {
        this.processingFee = processingFee;
    }
    public BigDecimal getTotal() {
        return total;
    }
    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceViewModel that = (InvoiceViewModel) o;
        return getId() == that.getId() && getIdemId() == that.getIdemId() && getQuantity() == that.getQuantity() && Objects.equals(getName(), that.getName()) && Objects.equals(getStreet(), that.getStreet()) && Objects.equals(getCity(), that.getCity()) && Objects.equals(getState(), that.getState()) && Objects.equals(getZipcode(), that.getZipcode()) && Objects.equals(getItemType(), that.getItemType()) && Objects.equals(getUnitPrice(), that.getUnitPrice()) && Objects.equals(getSubtotal(), that.getSubtotal()) && Objects.equals(getTax(), that.getTax()) && Objects.equals(getProcessingFee(), that.getProcessingFee()) && Objects.equals(getTotal(), that.getTotal());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getStreet(), getCity(), getState(), getZipcode(), getItemType(), getIdemId(), getUnitPrice(), getQuantity(), getSubtotal(), getTax(), getProcessingFee(), getTotal());
    }
}