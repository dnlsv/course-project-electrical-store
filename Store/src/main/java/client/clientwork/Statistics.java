package client.clientwork;


import server.model.Order;
import server.model.Supply;

import java.util.ArrayList;

public class Statistics {

    ClientWork clientWork = new ClientWork();
    public static int beginNumberOfOrders;
    public static int beginNumberOfProducts;
    public static int beginNumberOfSupplies;
    private int numOfCompletedOrders;
    private int numOfSoldProducts;
    private int numOfReceivedSupplies;

    public Statistics() {
        numOfCompletedOrders = clientWork.getNumberOfOrders() - beginNumberOfOrders;
        numOfSoldProducts = clientWork.getNumberOfProducts() - beginNumberOfProducts;
        numOfReceivedSupplies = clientWork.getNumberOfSupplies() - beginNumberOfSupplies;
    }

    public int getNumOfCompletedOrders() {
        if (numOfCompletedOrders < 0)
            return 0;
        else
            return numOfCompletedOrders;
    }

    public int getNumOfSoldProducts() {
        if (numOfSoldProducts < 0)
            return 0;
        else
            return numOfSoldProducts;
    }

    public int getSpentSupplies() {
        int spentSupplies = 0;
        if (numOfReceivedSupplies < 0)
            return 0;
        else {
            ArrayList<Supply> arrayList = clientWork.getSupplyArrayList();
            for(int i = arrayList.size() - numOfReceivedSupplies; i < arrayList.size(); i++)
                spentSupplies = spentSupplies + arrayList.get(i).getSupplyCost();
            return spentSupplies;
        }
    }

    public int getIncome(){
        ArrayList<Order> arrayList = clientWork.getOrderArrayList();
        int cost = 0;
        for (int i = arrayList.size() - numOfCompletedOrders; i < arrayList.size(); i++)
            cost = cost + arrayList.get(i).getOrderCost();
        return cost;
    }

}
