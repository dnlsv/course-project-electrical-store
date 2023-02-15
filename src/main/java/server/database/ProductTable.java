package server.database;

import server.model.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProductTable {

    private Connection connection;
    private Statement statement;
    private ArrayList<Product> arrayList;
    private ResultSet resultSet;
    private String query;

    public ProductTable(Connection connection){
        this.connection = connection;
        try {
            statement = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ArrayList<Product> getProductTable(){
        query = "SELECT * FROM store.product;";
        arrayList = new ArrayList<Product>();
        try {
            resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                Product product = new Product(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getInt(5), resultSet.getDate(6),
                        resultSet.getInt(7));
                arrayList.add(product);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return arrayList;
    }

    public void AddToProductTable(Product product){
        query = "insert into product " +
                "(productName, productProducer, productCharacteristics, productQuantity, productDate, productPrice)" +
                "values ('" + product.getProductName() + "', '" + product.getProductProducer() + "', '" +
                product.getProductCharacteristic() + "', " + product.getProductQuantity() + ", '" +
                product.getProductDate() + "'," + product.getProductPrice() +");";

        try {
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ArrayList<Product> searchFromProductTable(Product product){
        String quantity, date, price;
        if(product.getProductQuantity() == 0)
            quantity = "";
        else
            quantity = Integer.toString(product.getProductQuantity());
        if(product.getProductDate() == null)
            date = "";
        else
            date = String.valueOf(product.getProductDate());
        if(product.getProductPrice() == 0)
            price = "";
        else
            price = Integer.toString(product.getProductPrice());

        query = "select * from product where productName LIKE '" + product.getProductName() +
                "%' and productProducer LIKE '" + product.getProductProducer() +
                "%' and productCharacteristics LIKE '" + product.getProductCharacteristic() +
                "%' and productQuantity LIKE '" + quantity +
                "%' and productDate LIKE '" + date +
                "%' and productPrice LIKE '" + price + "%';";
        arrayList = new ArrayList<Product>();
        try {
            resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                Product foundProduct = new Product(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getInt(5), resultSet.getDate(6),
                        resultSet.getInt(7));
                arrayList.add(foundProduct);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return arrayList;
    }

    public void DeleteFromProductTable(int id){
        query = "delete from product where productID = " + id + ";";
        try {
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void EditFromProductTable(Product product){
        query = "update product set productName = '" + product.getProductName() +
                "', productProducer = '" + product.getProductProducer() +
                "', productCharacteristics = '" + product.getProductCharacteristic() +
                "', productQuantity = " + product.getProductQuantity() +
                ", productDate = '"+ product.getProductDate() +
                "', productPrice = " + product.getProductPrice() +
                " where productID = " +  product.getProductID() +";";

        try {
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /*public int getProductPrice(int id){
        query = "SELECT productPrice FROM product where productId = " + id + ";";
        int productPrice = 0;
        try {
            resultSet = statement.executeQuery(query);
            resultSet.next();
            productPrice = resultSet.getInt(1);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return productPrice;
    }*/

    public Product getProductByProductID(int id){
        query = "SELECT * FROM product where productId = " + id + ";";
        Product product = null;
        try {
            resultSet = statement.executeQuery(query);
            resultSet.next();
            product = new Product(resultSet.getInt(1), resultSet.getString(2),
                    resultSet.getString(3), resultSet.getString(4),
                    resultSet.getInt(5), resultSet.getDate(6),
                    resultSet.getInt(7));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return product;
    }

    public ArrayList<Integer> getProductIDList(){
        ArrayList<Integer> productIDList = new ArrayList<Integer>();
        ArrayList<Product> arrayList = getProductTable();

        for(int i = 0; i < arrayList.size(); i++){
            productIDList.add(arrayList.get(i).getProductID());
        }

        return productIDList;
    }

}
