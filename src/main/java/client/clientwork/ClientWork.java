package client.clientwork;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import server.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

public class ClientWork {

    private String clientMessage;

    public ClientWork() {
    }

    public ArrayList<Product> getProductArrayList() {
        clientMessage = "getProductTable";
        ArrayList<Product> productList = null;

        try {
            Client.outputStream.writeObject(clientMessage);
            productList = (ArrayList<Product>) Client.inputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return productList;
    }

    public void addToProductTable(Product product) {
        clientMessage = "addToProductTable";

        try {
            Client.outputStream.writeObject(clientMessage);
            Client.outputStream.writeObject(product);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Product> searchFromProductTable(Product product) {
        clientMessage = "searchFromProductTable";
        ArrayList<Product> productList = null;

        try {
            Client.outputStream.writeObject(clientMessage);
            Client.outputStream.writeObject(product);
            productList = (ArrayList<Product>) Client.inputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return productList;
    }

    public void deleteFromProductTable(int productID) {
        clientMessage = "deleteFromProductTable";

        try {
            Client.outputStream.writeObject(clientMessage);
            Client.outputStream.writeObject(productID);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editFromProductTable(Product product) {
        clientMessage = "editFromProductTable";

        try {
            Client.outputStream.writeObject(clientMessage);
            Client.outputStream.writeObject(product);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Supply> getSupplyArrayList() {
        clientMessage = "getSupplyTable";
        ArrayList<Supply> supplyList = null;

        try {
            Client.outputStream.writeObject(clientMessage);
            supplyList = (ArrayList<Supply>) Client.inputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return supplyList;
    }

    public void addToSupplyTable(Supply supply) {
        clientMessage = "addToSupplyTable";

        try {
            Client.outputStream.writeObject(clientMessage);
            Client.outputStream.writeObject(supply);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteFromSupplyTable(int supplyID) {
        clientMessage = "deleteFromSupplyTable";

        try {
            Client.outputStream.writeObject(clientMessage);
            Client.outputStream.writeObject(supplyID);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editFromSupplyTable(Supply supply) {
        clientMessage = "editFromSupplyTable";

        try {
            Client.outputStream.writeObject(clientMessage);
            Client.outputStream.writeObject(supply);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Order> getOrderArrayList() {
        clientMessage = "getOrderArrayList";
        ArrayList<Order> orderList = null;

        try {
            Client.outputStream.writeObject(clientMessage);
            orderList = (ArrayList<Order>) Client.inputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return orderList;
    }

    public void addToOrderTable(Order order) {
        clientMessage = "addToOrderTable";

        try {
            Client.outputStream.writeObject(clientMessage);
            Client.outputStream.writeObject(order);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteFromOrderTable(int orderID) {
        clientMessage = "deleteFromOrderTable";

        try {
            Client.outputStream.writeObject(clientMessage);
            Client.outputStream.writeObject(orderID);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editFromOrderTable(Order order) {
        clientMessage = "editFromOrderTable";

        try {
            Client.outputStream.writeObject(clientMessage);
            Client.outputStream.writeObject(order);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addToProductListTable(ArrayList<ProductList> productListArrayList) {
        clientMessage = "addToProductListTable";

        try {
            Client.outputStream.writeObject(clientMessage);
            Client.outputStream.writeObject(productListArrayList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editFromProductListTable(ArrayList<ProductList> productListArrayList) {
        clientMessage = "editFromProductListTable";

        try {
            Client.outputStream.writeObject(clientMessage);
            Client.outputStream.writeObject(productListArrayList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getNextOrderID() {
        clientMessage = "getNextOrderID";
        int orderID = 0;

        try {
            Client.outputStream.writeObject(clientMessage);
            orderID = (Integer) Client.inputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return orderID;
    }

    public ArrayList<ProductList> getProductListArrayListByOrderID(int orderID) {
        clientMessage = "getProductListArrayListByOrderID";
        ArrayList<ProductList> productListArrayList = null;

        try {
            Client.outputStream.writeObject(clientMessage);
            Client.outputStream.writeObject(orderID);
            productListArrayList = (ArrayList<ProductList>) Client.inputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return productListArrayList;
    }

    /*public ArrayList<ProductList> getProductListArrayList() {
        clientMessage = "getProductListArrayList";
        ArrayList<ProductList> productListArrayList = null;

        try {
            Client.outputStream.writeObject(clientMessage);
            productListArrayList = (ArrayList<ProductList>) Client.inputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return productListArrayList;
    }*/

    public boolean accountVerification(String login, String password) {
        clientMessage = "accountVerification";
        boolean flag = false;

        try {
            Client.outputStream.writeObject(clientMessage);
            Client.outputStream.writeObject(login);
            Client.outputStream.writeObject(password);
            flag = (Boolean) Client.inputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return flag;
    }

    public int getUserRoleID(String login) {
        clientMessage = "getUserRoleID";
        int roleID = 0;

        try {
            Client.outputStream.writeObject(clientMessage);
            Client.outputStream.writeObject(login);
            roleID = (Integer) Client.inputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return roleID;
    }

    /*public int getProductPrice(int id){
        clientMessage = "getProductPrice";
        int productPrice = 0;
        try {
            Client.outputStream.writeObject(clientMessage);
            Client.outputStream.writeObject(id);
            productPrice = (Integer) Client.inputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return productPrice;
    }*/

    public Product getProductByProductID(int productID) {
        clientMessage = "getProductByProductID";
        Product product = null;

        try {
            Client.outputStream.writeObject(clientMessage);
            Client.outputStream.writeObject(productID);
            product = (Product) Client.inputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return product;
    }

    public int getNumberOfOrders() {
        int number = 0;
        clientMessage = "getNumberOfOrders";

        try {
            Client.outputStream.writeObject(clientMessage);
            number = (Integer) Client.inputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return number;
    }

    public int getNumberOfProducts() {
        int number = 0;
        clientMessage = "getNumberOfProducts";

        try {
            Client.outputStream.writeObject(clientMessage);
            number = (Integer) Client.inputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return number;
    }

    public int getNumberOfSupplies() {
        int number = 0;
        clientMessage = "getNumberOfSupplies";

        try {
            Client.outputStream.writeObject(clientMessage);
            number = (Integer) Client.inputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return number;
    }

    public void generateOrderReport(Order order) {
        clientMessage = "generateOrderReport";

        try {
            Client.outputStream.writeObject(clientMessage);
            Client.outputStream.writeObject(order);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generateSupplyReport(Supply supply) {
        clientMessage = "generateSupplyReport";

        try {
            Client.outputStream.writeObject(clientMessage);
            Client.outputStream.writeObject(supply);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getTotalProductListQuantityByProductID(int productID) {
        clientMessage = "getTotalProductListQuantityByProductID";
        int quantity = 0;

        try {
            Client.outputStream.writeObject(clientMessage);
            Client.outputStream.writeObject(productID);
            quantity = (Integer) Client.inputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return quantity;
    }

    public Set<Integer> getUniqueProductList() {
        clientMessage = "getUniqueProductList";
        Set<Integer> set = null;

        try {
            Client.outputStream.writeObject(clientMessage);
            set = (Set<Integer>) Client.inputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return set;
    }

    public ArrayList<String> getUserNameList() {
        clientMessage = "getUserNameList";
        ArrayList<String> arrayList = null;

        try {
            Client.outputStream.writeObject(clientMessage);
            arrayList = (ArrayList<String>) Client.inputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return arrayList;
    }

    public ArrayList<Integer> getProductIDList() {
        clientMessage = "getProductIDList";
        ArrayList<Integer> arrayList = null;

        try {
            Client.outputStream.writeObject(clientMessage);
            arrayList = (ArrayList<Integer>) Client.inputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return arrayList;
    }

    public void dialogWindow(String message, String title) {
        Alert alert = new Alert(Alert.AlertType.NONE, message, ButtonType.OK);
        alert.setTitle(title);
        alert.showAndWait();
    }

}
