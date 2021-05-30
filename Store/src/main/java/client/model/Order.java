package client.model;

import java.io.Serializable;
import java.sql.Date;

public class Order implements Serializable {

    private int orderID;
    private String orderClientName;
    private Date orderDate;
    private String orderUserName;
    private int orderCost;

    public Order(int orderID, String orderClientName, Date orderDate, String orderUserName, int orderCost){
        this.orderID = orderID;
        this.orderClientName = orderClientName;
        this.orderDate = orderDate;
        this.orderUserName = orderUserName;
        this.orderCost = orderCost;
    };

    public Order(){};

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getOrderClientName() {
        return orderClientName;
    }

    public void setOrderClientName(String orderClientName) {
        this.orderClientName = orderClientName;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderUserName() {
        return orderUserName;
    }

    public void setOrderUserName(String orderUserName) {
        this.orderUserName = orderUserName;
    }

    public int getOrderCost() {
        return orderCost;
    }

    public void setOrderCost(int orderCost) {
        this.orderCost = orderCost;
    }
}

