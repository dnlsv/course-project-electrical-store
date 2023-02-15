package server.model;

import java.io.Serializable;

public class ProductList implements Serializable {

    private int productListProductID;
    private int productListOrderID;
    private int productListQuantity;

    public ProductList(){}

    public ProductList(int productListProductID, int productListOrderID, int productListQuantity){
        this.productListProductID = productListProductID;
        this.productListOrderID = productListOrderID;
        this.productListQuantity = productListQuantity;
    }

    public int getProductListProductID() {
        return productListProductID;
    }

    public void setProductListProductID(int productListProductID) {
        this.productListProductID = productListProductID;
    }

    public int getProductListOrderID() {
        return productListOrderID;
    }

    public void setProductListOrderID(int productListOrderID) {
        this.productListOrderID = productListOrderID;
    }

    public int getProductListQuantity() {
        return productListQuantity;
    }

    public void setProductListQuantity(int productListQuantity) {
        this.productListQuantity = productListQuantity;
    }
}
