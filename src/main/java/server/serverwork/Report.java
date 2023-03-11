package server.serverwork;

import server.database.DatabaseConnection;
import server.database.ProductListTable;
import server.database.ProductTable;
import server.model.Order;
import server.model.ProductList;
import server.model.Supply;

import java.io.*;
import java.util.ArrayList;

public class Report {
    private DatabaseConnection databaseConnection;
    private ProductListTable productListTable;
    private ProductTable productTable;

    public Report() {
        databaseConnection = new DatabaseConnection();
        productListTable = new ProductListTable(databaseConnection.getDatabaseConnection());
        productTable = new ProductTable(databaseConnection.getDatabaseConnection());
    }

    public void generateOrderReport(Order order) {
        String fileName = "D:/orderReport.txt";

        ArrayList<ProductList> arrayList = productListTable.getProductListTableByOrderID(order.getOrderID());

        String report = "Отчет о заказе № " + order.getOrderID() + "\nФИО клиента: " + order.getOrderClientName() +
                "\nДата заказа: " + order.getOrderDate() + "\nФИО сотрудника: " + order.getOrderUserName() +
                "\nСтоимость заказа: " + order.getOrderCost() + "\n\nСписок товаров:";

        String str = "";

        for (int i = 0; i < arrayList.size(); i++) {
            str = str + "\n" + productTable.getProductByProductID(arrayList.get(i).getProductListProductID()).getProductName() +
                    " " + arrayList.get(i).getProductListQuantity();
        }

        report = report + str;

        writeToFile(report, fileName);
    }


    public void generateSupplyReport(Supply supply) {
        String fileName = "D:/supplyReport.txt";

        String report = "Отчет о поставке № " + supply.getSupplyID() + "\nКод товара: " + supply.getSupplyProductID() +
                "\nКоличество товаров: " + supply.getSupplyQuantity() + "\nДата поступления: " + supply.getSupplyDate() +
                "\nФИО сотрудника: " + supply.getSupplyUserName() + "\nСтоимость: " + supply.getSupplyCost();

        writeToFile(report, fileName);
    }

    public void writeToFile(String str, String outputFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            writer.write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readFromFile(String inputFileName) {
        String str = "";

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                str = str + "\n" + line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

}
