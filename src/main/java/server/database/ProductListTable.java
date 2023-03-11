package server.database;

import server.model.ProductList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ProductListTable {

    private Connection connection;
    private Statement statement;
    private ArrayList<ProductList> arrayList;
    private ResultSet resultSet;
    private String query;

    public ProductListTable(Connection connection) {
        this.connection = connection;
        try {
            statement = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ArrayList<ProductList> getProductListTable() {
        query = "SELECT * FROM productlist;";
        arrayList = new ArrayList<ProductList>();

        try {
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                ProductList productList = new ProductList(resultSet.getInt(1),
                        resultSet.getInt(2), resultSet.getInt(3));
                arrayList.add(productList);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return arrayList;
    }


    public ArrayList<ProductList> getProductListTableByOrderID(int id) {
        query = "SELECT * FROM productlist where productlistOrderID = " + id + ";";
        arrayList = new ArrayList<ProductList>();

        try {
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                ProductList productList = new ProductList(resultSet.getInt(1),
                        resultSet.getInt(2), resultSet.getInt(3));
                arrayList.add(productList);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return arrayList;
    }

    public void AddToProductListTable(ProductList productList) {
        query = "insert into productlist " +
                "(productlistProductID, productlistOrderID, productlistQuantity)" +
                "values (" + productList.getProductListProductID() + ", " + productList.getProductListOrderID() + ", " +
                productList.getProductListQuantity() + ");";

        try {
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void DeleteFromProductListTable(int id) {
        query = "delete from productlist where productlistOrderID = " + id + ";";
        try {
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public int getNumberOfProducts() {
        int number = 0;
        query = "SELECT * FROM productlist;";

        try {
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                number = number + resultSet.getInt(3);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return number;
    }

    public int getTotalProductListQuantityByProductID(int id) {
        int quantity = 0;
        query = "SELECT productlistQuantity FROM productlist where productlistProductID = " + id + ";";

        try {
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                quantity = quantity + resultSet.getInt(1);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return quantity;
    }

    public Set<Integer> getUniqueProductList() {
        ArrayList<ProductList> arrayList = getProductListTable();
        Set<Integer> set = new HashSet<Integer>();

        for (int i = 0; i < arrayList.size(); i++) {
            set.add(arrayList.get(i).getProductListProductID());
        }

        return set;
    }

}


