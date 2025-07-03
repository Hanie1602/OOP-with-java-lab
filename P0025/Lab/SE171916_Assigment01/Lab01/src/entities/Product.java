package entities;

import java.io.Serializable;

public abstract class Product implements Serializable {

    private String productCode;
    private String productName;
    private int quantity;
    private float price;

    //Contructor
    public Product(String productCode, String productName, int quantity, float price) {
        this.productCode = productCode;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product code: " + productCode
                + ", Name: " + productName
                + ", Price: " + price
                + ", Quantity: " + quantity;
    }
}
