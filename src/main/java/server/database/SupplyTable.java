package server.database;

import server.model.Product;
import server.model.Supply;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SupplyTable {

    private Connection connection;
    private Statement statement;
    private ArrayList<Supply> arrayList;
    private ResultSet resultSet;
    private String query;

    public SupplyTable(Connection connection) {
        this.connection = connection;
        try {
            statement = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ArrayList<Supply> getSupplyTable() {
        query = "SELECT * FROM supply;";
        arrayList = new ArrayList<Supply>();
        try {
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Supply supply = new Supply(resultSet.getInt(1), resultSet.getInt(2),
                        resultSet.getInt(3), resultSet.getDate(4),
                        resultSet.getString(5), resultSet.getInt(6));
                arrayList.add(supply);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return arrayList;
    }

    public void AddToSupplyTable(Supply supply) {
        query = "insert into supply " +
                "(supplyProductID, supplyQuantity, supplyDate, supplyUserName, supplyCost)" +
                "values (" + supply.getSupplyProductID() + ", " + supply.getSupplyQuantity() + ", '" +
                supply.getSupplyDate() + "', '" + supply.getSupplyUserName() + "', " +
                supply.getSupplyCost() + ");";

        try {
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void DeleteFromSupplyTable(int id) {
        query = "delete from supply where supplyID = " + id + ";";
        try {
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void EditFromSupplyTable(Supply supply) {
        query = "update supply set supplyProductID = " + supply.getSupplyProductID() +
                ", supplyQuantity = " + supply.getSupplyQuantity() +
                ", supplyDate = '" + supply.getSupplyDate() +
                "', supplyUserName = '" + supply.getSupplyUserName() +
                "', supplyCost = " + supply.getSupplyCost() +
                " where supplyID = " + supply.getSupplyID() + ";";

        try {
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public int getNumberOfSupplies() {
        int number = 0;
        query = "SELECT * FROM supply;";

        try {
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                number++;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return number;
    }
}
