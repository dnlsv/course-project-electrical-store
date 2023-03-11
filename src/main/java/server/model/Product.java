package server.model;

import java.io.Serializable;
import java.sql.Date;

public class Product implements Serializable {

    private int productID;
    private String productName;
    private String productProducer;
    private String productCharacteristic;
    private int productQuantity;
    private Date productDate;
    private int productPrice;

    public Product(int productID, String productName, String productProducer, String productCharacteristic,
                   int productQuantity, Date productDate, int productPrice) {
        this.productID = productID;
        this.productName = productName;
        this.productProducer = productProducer;
        this.productCharacteristic = productCharacteristic;
        this.productQuantity = productQuantity;
        this.productDate = productDate;
        this.productPrice = productPrice;
    }

    ;

    public Product() {
    }

    ;

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductProducer() {
        return productProducer;
    }

    public void setProductProducer(String productProducer) {
        this.productProducer = productProducer;
    }

    public String getProductCharacteristic() {
        return productCharacteristic;
    }

    public void setProductCharacteristic(String productCharacteristic) {
        this.productCharacteristic = productCharacteristic;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Date getProductDate() {
        return productDate;
    }

    public void setProductDate(Date productDate) {
        this.productDate = productDate;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }
}
