package client.controller;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client.clientwork.ClientWork;
import client.clientwork.Validation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import server.model.Order;
import server.model.Product;
import server.model.ProductList;

public class AddOrderController {

    private Product product;
    private ProductList productList;
    private int productListOrderID;
    private int orderCost;
    ArrayList<ProductList> arrayList = new ArrayList<ProductList>();
    ClientWork clientWork = new ClientWork();
    Validation validation = new Validation();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<?, ?> productDateColumn;

    @FXML
    private TextField orderClientNameField;

    @FXML
    private TableView<ProductList> productListTable;

    @FXML
    private TableColumn<?, ?> productListQuantityColumn;

    @FXML
    private TableColumn<?, ?> productIDColumn;

    @FXML
    private TableView<Product> productTable;

    @FXML
    private TableColumn<?, ?> productProducerColumn;

    @FXML
    private TableColumn<?, ?> productListOrderIDColumn;

    @FXML
    private TableColumn<?, ?> productPriceColumn;

    @FXML
    private DatePicker orderDateField;

    @FXML
    private Button addToList;

    @FXML
    private TextField orderCostField;

    @FXML
    private TextField productListQuantityField;

    @FXML
    private TableColumn<?, ?> productNameColumn;

    @FXML
    private TableColumn<?, ?> productListProductIDColumn;

    @FXML
    private TableColumn<?, ?> productQuantityColumn;

    @FXML
    private Button addOrderButton;

    @FXML
    private Button returnButton;

    @FXML
    private TableColumn<?, ?> productCharacteristicColumn;

    @FXML
    private Button deleteFromList;

    @FXML
    private ChoiceBox<String> orderUserNameChoiceBox;

    @FXML
    void addOrderButtonAction(ActionEvent event) {
        if(orderClientNameField.getText().equals("")
                || String.valueOf(Date.valueOf(orderDateField.getValue())).equals("")
                || orderUserNameChoiceBox.getValue().equals("") || orderCostField.getText().equals("")
                || orderCostField.getText().equals("0"))
            clientWork.dialogWindow("Заполните все поля и выберите данные!", "Information");
        else {
            String clientName = orderClientNameField.getText();
            Date date = Date.valueOf(orderDateField.getValue());
            String userName = orderUserNameChoiceBox.getValue();
            int cost = Integer.parseInt(orderCostField.getText());
            Order order = new Order(0, clientName, date, userName, cost);
            clientWork.addToOrderTable(order);
            clientWork.addToProductListTable(arrayList);

            clientWork.dialogWindow("Заказ добавлен!", "Information");

            Stage stage = (Stage) addOrderButton.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    void returnButtonAction(ActionEvent event) {

        for (int i = 0; i < arrayList.size(); i++) {
            product = clientWork.getProductByProductID(arrayList.get(i).getProductListProductID());
            product.setProductQuantity(product.getProductQuantity() + arrayList.get(i).getProductListQuantity());
            clientWork.editFromProductTable(product);
        }

        Stage stage = (Stage) returnButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void deleteFromListAction(ActionEvent event) {
        if (productListTable.getItems().size() != 0) {
            if (this.productList != null) {
                for (int i = 0; i < arrayList.size(); i++) {
                    if (productList.equals(arrayList.get(i)))
                        arrayList.remove(arrayList.get(i));
                }

                orderCost = orderCost - clientWork.getProductByProductID(productList.getProductListProductID()).getProductPrice()
                        * productList.getProductListQuantity();
                orderCostField.setText(Integer.toString(orderCost));

                product = clientWork.getProductByProductID(productList.getProductListProductID());
                product.setProductQuantity(product.getProductQuantity() + productList.getProductListQuantity());
                clientWork.editFromProductTable(product);

                productListTableView();
                productTableView();

            } else
                clientWork.dialogWindow("Выберите товар из списка!", "Information");
        } else
            clientWork.dialogWindow("Список пуст!", "Information");
    }

    @FXML
    void addToListAction(ActionEvent event) {
        if (this.product == null)
            clientWork.dialogWindow("Выберите товар!", "Information");
        else {
            if (productListQuantityField.getText().equals(""))
                clientWork.dialogWindow("Введите количество товаров!", "Information");
            else {
                if (validation.validationInt(productListQuantityField.getText()) == false ||
                        Integer.parseInt(productListQuantityField.getText()) == 0)
                    clientWork.dialogWindow("Введите корректные данные!", "Error");
                else {
                    if (Integer.parseInt(productListQuantityField.getText()) > product.getProductQuantity())
                        clientWork.dialogWindow("Столько товаров нет в наличии!", "Error");
                    else {
                        ProductList productList;
                        productList = new ProductList();
                        productList.setProductListProductID(product.getProductID());
                        productList.setProductListOrderID(productListOrderID);
                        productList.setProductListQuantity(Integer.parseInt(productListQuantityField.getText()));

                        orderCost = orderCost + product.getProductPrice() * Integer.parseInt(productListQuantityField.getText());
                        orderCostField.setText(Integer.toString(orderCost));

                        product.setProductQuantity(product.getProductQuantity() - Integer.parseInt(productListQuantityField.getText()));
                        clientWork.editFromProductTable(product);

                        arrayList.add(productList);

                        productListTableView();
                        productTableView();
                    }
                }
            }
        }

    }

    private void productListTableView(){
        productListProductIDColumn.setCellValueFactory(new PropertyValueFactory<>("productListProductID"));
        productListOrderIDColumn.setCellValueFactory(new PropertyValueFactory<>("productListOrderID"));
        productListQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("productListQuantity"));
        productListTable.setItems(FXCollections.observableArrayList(arrayList));
    }

    @FXML
    void productTableAction(javafx.scene.input.MouseEvent event) {
        product =  productTable.getSelectionModel().selectedItemProperty().get();
    }

    @FXML
    void productListTableAction(javafx.scene.input.MouseEvent event) {
        productList = productListTable.getSelectionModel().selectedItemProperty().get();
    }

    @FXML
    void initialize() {
        setUserNameChoiceBox();
        productTableView();
    }

    public void productTableView(){
        productIDColumn.setCellValueFactory(new PropertyValueFactory<>("productID"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        productProducerColumn.setCellValueFactory(new PropertyValueFactory<>("productProducer"));
        productCharacteristicColumn.setCellValueFactory(new PropertyValueFactory<>("productCharacteristic"));
        productQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("productQuantity"));
        productDateColumn.setCellValueFactory(new PropertyValueFactory<>("productDate"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
        productTable.setItems(FXCollections.observableArrayList(clientWork.getProductArrayList()));
    }

    private void setUserNameChoiceBox(){
        ArrayList<String> userNameList = clientWork.getUserNameList();
        ObservableList<String> list = FXCollections.observableArrayList();
        list.addAll(userNameList);
        orderUserNameChoiceBox.getItems().addAll(list);
    }

    public void setNextOrderID(int productListOrderID){
        this.productListOrderID = productListOrderID;
    }
}
