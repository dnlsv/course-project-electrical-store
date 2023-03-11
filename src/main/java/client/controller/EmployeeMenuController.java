package client.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Set;

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

public class EmployeeMenuController {

    ClientWork clientWork = new ClientWork();
    private Supply supply;
    private Order order;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<?, ?> productDateColumn;


    @FXML
    private TableView<Product> productTable;

    @FXML
    private TableColumn<?, ?> productProducerColumn;

    @FXML
    private Tab supplyTab;

    @FXML
    private TableColumn<?, ?> supplyProductIDColumn;

    @FXML
    private TableView<Order> orderTable;

    @FXML
    private TableColumn<?, ?> orderUserNameColumn;

    @FXML
    private TableColumn<?, ?> supplyDateColumn;

    @FXML
    private Tab productTab;

    @FXML
    private Button logOfButton;

    @FXML
    private TableColumn<?, ?> productQuantityColumn;

    @FXML
    private TableColumn<?, ?> productCharacteristicColumn;

    @FXML
    private TableColumn<?, ?> supplyIDColumn;

    @FXML
    private Button deleteOrderButton;

    @FXML
    private Button editOrderButton;

    @FXML
    private TableColumn<?, ?> supplyCostColumn;

    @FXML
    private TableColumn<?, ?> productIDColumn;

    @FXML
    private TableColumn<?, ?> orderDateColumn;

    @FXML
    private TableColumn<?, ?> orderIDColumn;

    @FXML
    private TableColumn<?, ?> productPriceColumn;

    @FXML
    private TableColumn<?, ?> orderClientNameColumn;

    @FXML
    private TableColumn<?, ?> supplyUserNameColumn;

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
    private Button pieChartUserButton;

    @FXML
    private Button pieChartProductButton;

    @FXML
    private TextField completedOrdersField;

    @FXML
    private TextField soldProductsField;

    @FXML
    private TextField incomeField;

    @FXML
    private Button viewProductListButton;

    @FXML
    private TextField spentSuppliesField;

    @FXML
    private Button searchProductButton;

    @FXML
    void viewProductListButtonAction(ActionEvent event) {
        if (order != null) {
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
        } else {
            clientWork.dialogWindow("Выберите заказ из таблицы для просмотра списка товаров!", "Information");
        }
    }

    @FXML
    void orderTableAction(MouseEvent event) {
        order = orderTable.getSelectionModel().selectedItemProperty().get();
    }

    @FXML
    void logOfButtonAction(ActionEvent event) {
        Stage st = (Stage) logOfButton.getScene().getWindow();
        st.close();
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
    void deleteOrderButtonAction(ActionEvent event) {
        if (order != null) {
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
        } else {
            clientWork.dialogWindow("Выберите заказ из таблицы для удаления!", "Information");
        }
    }

    @FXML
    void editOrderButtonAction(ActionEvent event) {
        if (order != null) {
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
        } else {
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
    void pieChartUserButtonAction(ActionEvent event) {
        Stage stage = new Stage();

        PieChart pieChart = new PieChart();

        ArrayList<String> userNameList = clientWork.getUserNameList();
        ArrayList<Order> arrayList = clientWork.getOrderArrayList();

        String name;
        int cost = 0;

        for (int j = 0; j < userNameList.size(); j++) {
            name = userNameList.get(j);
            for (int i = 0; i < arrayList.size(); i++) {
                if (name.equals(arrayList.get(i).getOrderUserName()))
                    cost = cost + arrayList.get(i).getOrderCost();
            }
            if (cost != 0) {
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
    void pieChartProductButtonAction(ActionEvent event) {
        Stage stage = new Stage();

        PieChart pieChart = new PieChart();

        Set<Integer> set = clientWork.getUniqueProductList();
        Iterator<Integer> iterator = set.iterator();
        Iterator<Integer> iterator2 = set.iterator();

        while (iterator.hasNext()) {
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
    void searchProductButtonAction(ActionEvent event) {
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
        stage.setScene(new Scene(root));
        stage.setTitle("Поиск товара");
        stage.showAndWait();
    }

    @FXML
    void supplyTableAction(MouseEvent event) {
        supply = supplyTable.getSelectionModel().selectedItemProperty().get();
    }


    @FXML
    void initialize() {
        productTableView();
        orderTableView();
        supplyTableView();
        setStatisticalData();
    }

    private void productTableView() {
        productIDColumn.setCellValueFactory(new PropertyValueFactory<>("productID"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        productProducerColumn.setCellValueFactory(new PropertyValueFactory<>("productProducer"));
        productCharacteristicColumn.setCellValueFactory(new PropertyValueFactory<>("productCharacteristic"));
        productQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("productQuantity"));
        productDateColumn.setCellValueFactory(new PropertyValueFactory<>("productDate"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
        productTable.setItems(FXCollections.observableArrayList(clientWork.getProductArrayList()));
    }

    private void orderTableView() {
        supplyIDColumn.setCellValueFactory(new PropertyValueFactory<>("supplyID"));
        supplyProductIDColumn.setCellValueFactory(new PropertyValueFactory<>("supplyProductID"));
        supplyQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("supplyQuantity"));
        supplyDateColumn.setCellValueFactory(new PropertyValueFactory<>("supplyDate"));
        supplyUserNameColumn.setCellValueFactory(new PropertyValueFactory<>("supplyUserName"));
        supplyCostColumn.setCellValueFactory(new PropertyValueFactory<>("supplyCost"));
        supplyTable.setItems(FXCollections.observableArrayList(clientWork.getSupplyArrayList()));
    }

    private void supplyTableView() {
        orderIDColumn.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        orderClientNameColumn.setCellValueFactory(new PropertyValueFactory<>("orderClientName"));
        orderDateColumn.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        orderUserNameColumn.setCellValueFactory(new PropertyValueFactory<>("orderUserName"));
        orderCostColumn.setCellValueFactory(new PropertyValueFactory<>("orderCost"));
        orderTable.setItems(FXCollections.observableArrayList(clientWork.getOrderArrayList()));
    }

    private void setStatisticalData() {
        Statistics statistics = new Statistics();
        completedOrdersField.setText(Integer.toString(statistics.getNumOfCompletedOrders()));
        soldProductsField.setText(Integer.toString(statistics.getNumOfSoldProducts()));
        incomeField.setText(Integer.toString(statistics.getIncome()));
        spentSuppliesField.setText(Integer.toString(statistics.getSpentSupplies()));
    }

}
