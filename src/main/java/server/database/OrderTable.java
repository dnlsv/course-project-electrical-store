package server.database;

import server.model.Order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OrderTable {

    private Connection connection;
    private Statement statement;
    private ArrayList<Order> arrayList;
    private ResultSet resultSet;
    private String query;

    public OrderTable(Connection connection) {
        this.connection = connection;
        try {
            statement = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ArrayList<Order> getOrderTable() {
        query = "SELECT * FROM clientorder;";
        arrayList = new ArrayList<Order>();

        try {
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Order order = new Order(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getDate(3), resultSet.getString(4), resultSet.getInt(5));
                arrayList.add(order);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return arrayList;
    }

    public void AddToOrderTable(Order order) {
        query = "insert into clientorder " +
                "(orderClientName, orderDate, orderUserName, orderCost)" +
                "values ('" + order.getOrderClientName() + "', '" +
                order.getOrderDate() + "', '" + order.getOrderUserName() + "', " +
                order.getOrderCost() + ");";

        try {
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void DeleteFromOrderTable(int id) {
        query = "delete from clientorder where orderID = " + id + ";";
        try {
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void EditFromOrderTable(Order order) {
        query = "update clientorder set orderClientName = '" + order.getOrderClientName() +
                "', orderDate = '" + order.getOrderDate() +
                "', orderUserName = '" + order.getOrderUserName() +
                "', orderCost = " + order.getOrderCost() +
                " where orderID = " + order.getOrderID() + ";";

        try {
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public int getNumberOfOrders() {
        int number = 0;
        query = "SELECT * FROM clientorder;";

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
