package server.serverwork;

import server.database.*;
import server.model.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Set;

public class ServerThread extends Thread{

    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private Socket socket;
    private InetAddress inetAddress;
    private int counter;
    private String clientMessage;
    private DatabaseConnection databaseConnection;

    public ServerThread(Socket socket, int _counter) throws IOException {
        this.outputStream = new ObjectOutputStream(socket.getOutputStream());
        this.inputStream = new ObjectInputStream(socket.getInputStream());
        this.socket = socket;
        this.inetAddress = socket.getInetAddress();
        this.counter = _counter;
        this.databaseConnection = new DatabaseConnection();
    }

    public void run() {

        ProductTable productTable;
        Product product;
        SupplyTable supplyTable;
        Supply supply;
        OrderTable orderTable;
        Order order;
        ProductListTable productListTable;
        ProductList productListObject;
        UserTable userTable;
        User user;
        Report report;

        productTable = new ProductTable(databaseConnection.getDatabaseConnection());
        product = new Product();
        supplyTable = new SupplyTable(databaseConnection.getDatabaseConnection());
        supply = new Supply();
        orderTable = new OrderTable(databaseConnection.getDatabaseConnection());
        order = new Order();
        productListTable = new ProductListTable(databaseConnection.getDatabaseConnection());
        productListObject = new ProductList();
        userTable = new UserTable(databaseConnection.getDatabaseConnection());
        user = new User();
        report = new Report();

        System.out.println("Server is waiting...");

        try {
            while (!socket.isClosed()) {
                clientMessage = (String) inputStream.readObject();
                System.out.println(clientMessage);
                switch (clientMessage) {
                    case "getProductTable": {
                        ArrayList<Product> productList = productTable.getProductTable();
                        outputStream.writeObject(productList);
                        break;
                    }
                    case "addToProductTable": {
                        product = (Product) inputStream.readObject();
                        productTable.AddToProductTable(product);
                        break;
                    }
                    case "searchFromProductTable": {
                        product = (Product) inputStream.readObject();
                        ArrayList<Product> productList = productTable.searchFromProductTable(product);
                        outputStream.writeObject(productList);
                        break;
                    }
                    case "deleteFromProductTable": {
                        int productID = (Integer) inputStream.readObject();
                        productTable.DeleteFromProductTable(productID);
                        break;
                    }
                    case "editFromProductTable": {
                        product = (Product) inputStream.readObject();
                        productTable.EditFromProductTable(product);
                        break;
                    }
                    case "getSupplyTable": {
                        ArrayList<Supply> supplyList = supplyTable.getSupplyTable();
                        outputStream.writeObject(supplyList);
                        break;
                    }
                    case "addToSupplyTable": {
                        supply = (Supply) inputStream.readObject();
                        supplyTable.AddToSupplyTable(supply);
                        break;
                    }
                    case "deleteFromSupplyTable": {
                        int supplyID = (Integer) inputStream.readObject();
                        supplyTable.DeleteFromSupplyTable(supplyID);
                        break;
                    }
                    case "editFromSupplyTable": {
                        supply = (Supply) inputStream.readObject();
                        supplyTable.EditFromSupplyTable(supply);
                        break;
                    }
                    case "getOrderArrayList": {
                        ArrayList<Order> orderList = orderTable.getOrderTable();
                        outputStream.writeObject(orderList);
                        break;
                    }
                    case "addToOrderTable": {
                        order = (Order) inputStream.readObject();
                        orderTable.AddToOrderTable(order);
                        break;
                    }
                    case "deleteFromOrderTable": {
                        int orderID = (Integer) inputStream.readObject();
                        orderTable.DeleteFromOrderTable(orderID);
                        break;
                    }
                    case "editFromOrderTable": {
                        order = (Order) inputStream.readObject();
                        orderTable.EditFromOrderTable(order);
                        break;
                    }
                    case "addToProductListTable": {
                        ArrayList<ProductList> productListArrayList = new ArrayList<ProductList>();
                        productListArrayList = (ArrayList<ProductList>) inputStream.readObject();
                        for (int i = 0; i < productListArrayList.size(); i++)
                            productListTable.AddToProductListTable(productListArrayList.get(i));
                        break;
                    }
                    case "getNextOrderID": {
                        ArrayList<Order> orderArrayList = orderTable.getOrderTable();
                        int orderId = orderArrayList.get(orderArrayList.size() - 1).getOrderID() + 1;
                        outputStream.writeObject(orderId);
                        break;
                    }
                    case "editFromProductListTable": {
                        ArrayList<ProductList> productListArrayList = new ArrayList<ProductList>();
                        productListArrayList = (ArrayList<ProductList>) inputStream.readObject();
                        productListTable.DeleteFromProductListTable(productListArrayList.get(0).getProductListOrderID());
                        for (int i = 0; i < productListArrayList.size(); i++)
                            productListTable.AddToProductListTable(productListArrayList.get(i));
                        break;
                    }
                    case "getProductListArrayListByOrderID": {
                        int orderID = (Integer) inputStream.readObject();
                        ArrayList<ProductList> productListArrayList = productListTable.getProductListTableByOrderID(orderID);
                        outputStream.writeObject(productListArrayList);
                        break;
                    }
                    case "accountVerification": {
                        boolean flag = false;
                        String login = (String) inputStream.readObject();
                        String password = (String) inputStream.readObject();
                        flag = userTable.AccountVerification(login, password);
                        outputStream.writeObject(flag);
                        break;
                    }
                    case "getUserRoleID": {
                        int roleID;
                        String log = (String) inputStream.readObject();
                        roleID = userTable.getUserRoleID(log);
                        outputStream.writeObject(roleID);
                        break;
                    }
                    case "getProductByProductID": {
                        int productID = (Integer) inputStream.readObject();
                        product = productTable.getProductByProductID(productID);
                        outputStream.writeObject(product);
                        break;
                    }
                    case "getNumberOfOrders": {
                        int number = 0;
                        number = orderTable.getNumberOfOrders();
                        outputStream.writeObject(number);
                        break;
                    }
                    case "getNumberOfProducts": {
                        int number = 0;
                        number = productListTable.getNumberOfProducts();
                        outputStream.writeObject(number);
                        break;
                    }
                    case "getNumberOfSupplies": {
                        int number = 0;
                        number = supplyTable.getNumberOfSupplies();
                        outputStream.writeObject(number);
                        break;
                    }
                    case "generateOrderReport": {
                        order = (Order) inputStream.readObject();
                        report.generateOrderReport(order);
                        break;
                    }
                    case "generateSupplyReport": {
                        supply = (Supply) inputStream.readObject();
                        report.generateSupplyReport(supply);
                        break;
                    }
                    case "getTotalProductListQuantityByProductID": {
                        int productID = (Integer) inputStream.readObject();
                        int quantity = productListTable.getTotalProductListQuantityByProductID(productID);
                        outputStream.writeObject(quantity);
                        break;
                    }
                    case "getUniqueProductList": {
                        Set<Integer> set = productListTable.getUniqueProductList();
                        outputStream.writeObject(set);
                        break;
                    }
                    case "getUserNameList": {
                        ArrayList<String> arrayList = userTable.getUserNameList();
                        outputStream.writeObject(arrayList);
                        break;
                    }
                    case "getProductIDList": {
                        ArrayList<Integer> arrayList = productTable.getProductIDList();
                        outputStream.writeObject(arrayList);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            this.disconnect();
        }

    }

    private void disconnect() {
        try {
            if (outputStream != null)
                outputStream.close();
            if (inputStream != null)
                inputStream.close();
            System.out.println(inetAddress.getHostName() + " Closing client connection number " + counter);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            this.interrupt();
        }
    }

}
