package client.controller;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import client.clientwork.ClientWork;
import client.clientwork.Statistics;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import server.model.Order;
import server.model.Product;
import server.model.Supply;

public class AdminMenuController {

    private Product product;
    private Supply supply;
    private Order order;
    ClientWork clientWork = new ClientWork();
    public static Product productSt;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<?, ?> productDateColumn;

    @FXML
    private TableColumn<?, ?> productIDColumn;

    @FXML
    private TableColumn<?, ?> supplyUserNameColumn;

    @FXML
    private TableView<Product> productTable;

    @FXML
    private TableColumn<?, ?> productProducerColumn;

    @FXML
    private Button orderReportButton;

    @FXML
    private Button supplyReportButton;

    @FXML
    private Button editSupplyButton;

    @FXML
    private Tab supplyTab;

    @FXML
    private Button deleteProductButton;

    @FXML
    private Button editProductButton;

    @FXML
    private TableView<Order> orderTable;

    @FXML
    private TableColumn<?, ?> supplyProductIDColumn;

    @FXML
    private TableColumn<?, ?> supplyIDColumn;

    @FXML
    private TableColumn<?, ?> supplyDateColumn;

    @FXML
    private TableColumn<?, ?> supplyCostColumn;

    @FXML
    private Button deleteSupplyButton;

    @FXML
    private Button addProductButton;

    @FXML
    private Tab productTab;

    @FXML
    private TableColumn<?, ?> productQuantityColumn;

    @FXML
    private TableColumn<?, ?> productCharacteristicColumn;

    @FXML
    private Button deleteOrderButton;

    @FXML
    private Button editOrderButton;

    @FXML
    private TextField completedOrdersField;

    @FXML
    private TextField soldProductsField;

    @FXML
    private TableColumn<?, ?> orderIDColumn;

    @FXML
    private TableColumn<?, ?> orderDateColumn;

    @FXML
    private Button viewProductListButton;

    @FXML
    private TableColumn<?, ?> productPriceColumn;

    @FXML
    private TableColumn<?, ?> orderClientNameColumn;

    @FXML
    private TableColumn<?, ?> orderUserNameColumn;

    @FXML
    private TableColumn<?, ?> orderCostColumn;

    @FXML
    private Tab orderTab;

    @FXML
    private TableView<Supply> supplyTable;

    @FXML
    private TableColumn<?, ?> productNameColumn;

    @FXML
    private Button addOrderButton;

    @FXML
    private TableColumn<?, ?> supplyQuantityColumn;

    @FXML
    private Button addSupplyButton;

    @FXML
    private Button pieChartProductButton;

    @FXML
    private Button logOfButton;

    @FXML
    private TextField incomeField;

    @FXML
    private Button pieChartUserButton;

    @FXML
    private TextField spentSuppliesField;

    @FXML
    private Button searchProductButton;

    @FXML
    void addProductButtonAction(ActionEvent event) {
        addProductButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("view/addProduct.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();

        Stage stage = new Stage();
        stage.setOnCloseRequest(e -> initialize());
        stage.setOnHidden(e -> initialize());
        stage.setScene(new Scene(root));
        stage.setTitle("Добавление товара");
        stage.showAndWait();
    }

    @FXML
    void editProductButtonAction(ActionEvent event) {
        if(product != null) {
            editProductButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("view/editProduct.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();

            EditProductController editProductController = loader.getController();
            editProductController.setProduct(product);

            Stage stage = new Stage();
            stage.setOnCloseRequest(e -> initialize());
            stage.setOnHidden(e -> initialize());
            stage.setScene(new Scene(root));
            stage.setTitle("Редактирование товара");
            stage.showAndWait();
        }
        else {
            clientWork.dialogWindow("Выберите товар из таблицы для редактирования!", "Information");
        }
    }

    @FXML
    void deleteProductButtonAction(ActionEvent event) {
        if(product != null) {
            deleteProductButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("view/deleteProduct.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();

            DeleteProductController deleteProductController = loader.getController();
            deleteProductController.setProduct(product);

            Stage stage = new Stage();
            stage.setOnCloseRequest(e -> initialize());
            stage.setOnHidden(e -> initialize());
            stage.setScene(new Scene(root));
            stage.setTitle("Удаление товара");
            stage.showAndWait();
        }
        else {
            clientWork.dialogWindow("Выберите товар из таблицы для удаления!", "Information");
        }
    }

    @FXML
    void orderReportButtonAction(ActionEvent event) {
        if(order != null){
            clientWork.generateOrderReport(order);
            clientWork.dialogWindow("Отчет сформирован!", "Information");
        }
        else
            clientWork.dialogWindow("Выберите заказ из таблицы для создания отчета о заказе!", "Information");
    }

    @FXML
    void supplyReportButtonAction(ActionEvent event) {
        if(supply != null){
            clientWork.generateSupplyReport(supply);
            clientWork.dialogWindow("Отчет сформирован!", "Information");
        }
        else
            clientWork.dialogWindow("Выберите заказ из таблицы для создания отчета о заказе!", "Information");
    }

    @FXML
    void productTableAction(MouseEvent event) {
        product = productTable.getSelectionModel().selectedItemProperty().get();
    }

    @FXML
    void supplyTableAction(MouseEvent event) {
        supply = supplyTable.getSelectionModel().selectedItemProperty().get();
    }

    @FXML
    void orderTableAction(MouseEvent event) {
        order = orderTable.getSelectionModel().selectedItemProperty().get();
    }

    @FXML
    void viewProductListButtonAction(ActionEvent event) {
        if(order != null) {
            viewProductListButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("view/viewProductList.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();

            ViewProductListController viewProductListController = loader.getController();
            viewProductListController.setProductListOrderID(order.getOrderID());

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Просмотр списка товаров");
            stage.showAndWait();
        }else {
            clientWork.dialogWindow("Выберите заказ из таблицы для просмотра списка товаров!", "Information");
        }
    }

    @FXML
    void deleteOrderButtonAction(ActionEvent event) {
        if(order != null) {
            deleteOrderButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("view/deleteOrder.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();

            DeleteOrderController deleteOrderController = loader.getController();
            deleteOrderController.setOrder(order);

            Stage stage = new Stage();
            stage.setOnCloseRequest(e -> initialize());
            stage.setOnHidden(e -> initialize());
            stage.setScene(new Scene(root));
            stage.setTitle("Удаление заказа");
            stage.showAndWait();
        }
        else {
            clientWork.dialogWindow("Выберите заказ из таблицы для удаления!", "Information");
        }
    }

    @FXML
    void editOrderButtonAction(ActionEvent event) {
        if(order != null) {
            editOrderButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("view/editOrder.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();

            EditOrderController editOrderController = loader.getController();
            editOrderController.setOrder(order);

            Stage stage = new Stage();
            stage.setOnCloseRequest(e -> initialize());
            stage.setOnHidden(e -> initialize());
            stage.setScene(new Scene(root));
            stage.setTitle("Редактирование заказа");
            stage.showAndWait();
        }
        else {
            clientWork.dialogWindow("Выберите заказ из таблицы для редактирования!", "Information");
        }
    }

    @FXML
    void addOrderButtonAction(ActionEvent event) {
        addOrderButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("view/addOrder.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();

        AddOrderController addOrderController = loader.getController();
        addOrderController.setNextOrderID(clientWork.getNextOrderID());

        Stage stage = new Stage();
        stage.setOnCloseRequest(e -> initialize());
        stage.setOnHidden(e -> initialize());
        stage.setScene(new Scene(root));
        stage.setTitle("Добавление заказа");
        stage.showAndWait();
    }

    @FXML
    void addSupplyButtonAction(ActionEvent event) {
        addSupplyButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("view/addSupply.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();

        Stage stage = new Stage();
        stage.setOnCloseRequest(e -> initialize());
        stage.setOnHidden(e -> initialize());
        stage.setScene(new Scene(root));
        stage.setTitle("Добавление поставки");
        stage.showAndWait();
    }

    @FXML
    void editSupplyButtonAction(ActionEvent event) {
        if(supply != null) {
            editSupplyButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("view/editSupply.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();

            EditSupplyController editSupplyController = loader.getController();
            editSupplyController.setSupply(supply);

            Stage stage = new Stage();
            stage.setOnCloseRequest(e -> initialize());
            stage.setOnHidden(e -> initialize());
            stage.setScene(new Scene(root));
            stage.setTitle("Редактирование поставки");
            stage.showAndWait();
        }
        else {
            clientWork.dialogWindow("Выберите поставку из таблицы для редактирования!", "Information");
        }
    }

    @FXML
    void deleteSupplyButtonAction(ActionEvent event) {
        if(supply != null) {
            deleteSupplyButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("view/deleteSupply.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();

            DeleteSupplyController deleteSupplyController = loader.getController();
            deleteSupplyController.setSupply(supply);

            Stage stage = new Stage();
            stage.setOnCloseRequest(e -> initialize());
            stage.setOnHidden(e -> initialize());
            stage.setScene(new Scene(root));
            stage.setTitle("Удаление поставки");
            stage.showAndWait();
        }
        else {
            clientWork.dialogWindow("Выберите поставку из таблицы для удаления!", "Information");
        }
    }

    @FXML
    void logOfButtonAction(ActionEvent event) {
        Stage primaryStage = (Stage)logOfButton.getScene().getWindow();
        primaryStage.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("view/userAuthentication.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Магазин электротоваров");
        stage.show();

    }

    @FXML
    void pieChartProductButtonAction(ActionEvent event) {
        Stage stage = new Stage();

        PieChart pieChart = new PieChart();

        Set<Integer> set = clientWork.getUniqueProductList();
        Iterator<Integer> iterator = set.iterator();
        Iterator<Integer> iterator2 = set.iterator();

        while(iterator.hasNext()) {
            int quantity = clientWork.getTotalProductListQuantityByProductID(iterator.next());
            PieChart.Data slice = new PieChart.Data(clientWork.getProductByProductID(iterator2.next()).getProductName(), quantity);
            pieChart.getData().add(slice);
        }

        stage.setTitle("Данные о наиболее продаваемых товарах");
        StackPane root = new StackPane(pieChart);
        Scene scene = new Scene(root, 800, 500);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void pieChartUserButtonAction(ActionEvent event) {
        Stage stage = new Stage();

        PieChart pieChart = new PieChart();

        ArrayList<String> userNameList = clientWork.getUserNameList();
        ArrayList<Order> arrayList = clientWork.getOrderArrayList();

        String name;
        int cost = 0;

        for(int j = 0; j < userNameList.size(); j++) {
            name = userNameList.get(j);
            for (int i = 0; i < arrayList.size(); i++) {
                if (name.equals(arrayList.get(i).getOrderUserName()))
                    cost = cost + arrayList.get(i).getOrderCost();
            }
            if(cost != 0) {
                PieChart.Data slice = new PieChart.Data(name, cost);
                pieChart.getData().add(slice);
            }
        }

        stage.setTitle("Данные о сотрудниках в зависимости от принесенной прибыли");
        StackPane root = new StackPane(pieChart);
        Scene scene = new Scene(root, 800, 500);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void searchProductButtonAction(ActionEvent event){
        Stage st = (Stage) searchProductButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("view/searchProduct.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();

        Stage stage = new Stage();
        stage.setOnCloseRequest(e -> setProduct());
        stage.setOnHidden(e -> setProduct());
        stage.setScene(new Scene(root));
        stage.setTitle("Поиск товара");
        stage.showAndWait();
    }

    @FXML
    void initialize() {
        productTableView();
        orderTableView();
        supplyTableView();
        setStatisticalData();
        resetValue();
    }

    private void productTableView(){
        productIDColumn.setCellValueFactory(new PropertyValueFactory<>("productID"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        productProducerColumn.setCellValueFactory(new PropertyValueFactory<>("productProducer"));
        productCharacteristicColumn.setCellValueFactory(new PropertyValueFactory<>("productCharacteristic"));
        productQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("productQuantity"));
        productDateColumn.setCellValueFactory(new PropertyValueFactory<>("productDate"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
        productTable.setItems(FXCollections.observableArrayList(clientWork.getProductArrayList()));
    }

    private void orderTableView(){
        supplyIDColumn.setCellValueFactory(new PropertyValueFactory<>("supplyID"));
        supplyProductIDColumn.setCellValueFactory(new PropertyValueFactory<>("supplyProductID"));
        supplyQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("supplyQuantity"));
        supplyDateColumn.setCellValueFactory(new PropertyValueFactory<>("supplyDate"));
        supplyUserNameColumn.setCellValueFactory(new PropertyValueFactory<>("supplyUserName"));
        supplyCostColumn.setCellValueFactory(new PropertyValueFactory<>("supplyCost"));
        supplyTable.setItems(FXCollections.observableArrayList(clientWork.getSupplyArrayList()));
    }

    private void supplyTableView(){
        orderIDColumn.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        orderClientNameColumn.setCellValueFactory(new PropertyValueFactory<>("orderClientName"));
        orderDateColumn.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        orderUserNameColumn.setCellValueFactory(new PropertyValueFactory<>("orderUserName"));
        orderCostColumn.setCellValueFactory(new PropertyValueFactory<>("orderCost"));
        orderTable.setItems(FXCollections.observableArrayList(clientWork.getOrderArrayList()));
    }

    private void setStatisticalData(){
        Statistics statistics = new Statistics();
        completedOrdersField.setText(Integer.toString(statistics.getNumOfCompletedOrders()));
        soldProductsField.setText(Integer.toString(statistics.getNumOfSoldProducts()));
        incomeField.setText(Integer.toString(statistics.getIncome()));
        spentSuppliesField.setText(Integer.toString(statistics.getSpentSupplies()));
    }

    private void setProduct(){
        if(productSt != null)
            product = productSt;
    }

    private void resetValue(){
        product = null;
    }

}
