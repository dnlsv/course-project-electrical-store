package client.model;

import java.io.Serializable;
import java.sql.Date;

public class Supply implements Serializable {

    private int supplyID;
    private int supplyProductID;
    private int supplyQuantity;
    private Date supplyDate;
    private String supplyUserName;
    private int supplyCost;

    public Supply(int supplyID, int supplyProductID, int supplyQuantity, Date supplyDate,
                  String supplyUserName, int supplyCost){
        this.supplyID = supplyID;
        this.supplyProductID = supplyProductID;
        this.supplyQuantity = supplyQuantity;
        this.supplyDate = supplyDate;
        this.supplyUserName = supplyUserName;
        this.supplyCost = supplyCost;
    };

    public Supply(){};

    public int getSupplyID() {
        return supplyID;
    }

    public void setSupplyID(int supplyID) {
        this.supplyID = supplyID;
    }

    public int getSupplyProductID() {
        return supplyProductID;
    }

    public void setSupplyProductID(int supplyProductID) {
        this.supplyProductID = supplyProductID;
    }

    public int getSupplyQuantity() {
        return supplyQuantity;
    }

    public void setSupplyQuantity(int supplyQuantity) {
        this.supplyQuantity = supplyQuantity;
    }

    public Date getSupplyDate() {
        return supplyDate;
    }

    public void setSupplyDate(Date supplyDate) {
        this.supplyDate = supplyDate;
    }

    public String getSupplyUserName() {
        return supplyUserName;
    }

    public void setSupplyUserName(String supplyUserName) {
        this.supplyUserName = supplyUserName;
    }

    public int getSupplyCost() {
        return supplyCost;
    }

    public void setSupplyCost(int supplyCost) {
        this.supplyCost = supplyCost;
    }
}
