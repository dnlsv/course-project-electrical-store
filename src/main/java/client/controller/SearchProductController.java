package client.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client.clientwork.ClientWork;
import client.clientwork.Validation;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import server.model.Product;

public class SearchProductController {

    private Product product;
    ClientWork clientWork = new ClientWork();
    Validation validation = new Validation();
    ArrayList<Product> arrayList = new ArrayList<>();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField productNameField;

    @FXML
    private TableColumn<?, ?> productDateColumn;

    @FXML
    private TextField productProducerField;

    @FXML
    private DatePicker productDateField;

    @FXML
    private Button searchProductButton;

    @FXML
    private TableColumn<?, ?> productIDColumn;

    @FXML
    private TableView<Product> productTable;

    @FXML
    private TableColumn<?, ?> productProducerColumn;

    @FXML
    private TableColumn<?, ?> productPriceColumn;

    @FXML
    private TextField productCharacteristicsField;

    @FXML
    private TextField productPriceField;

    @FXML
    private TableColumn<?, ?> productNameColumn;

    @FXML
    private TableColumn<?, ?> productQuantityColumn;

    @FXML
    private Button returnButton;

    @FXML
    private TableColumn<?, ?> productCharacteristicColumn;

    @FXML
    private TextField productQuantityField;

    /*@FXML
    private Button selectAndGoButton;*/

    @FXML
    void searchProductButtonAction(ActionEvent event) {
        /*if (validation.validationInt(productQuantityField.getText()) == true &&
                validation.validationInt(productPriceField.getText()) == true) {
            if (Integer.parseInt(productPriceField.getText()) > 0 &&
                    Integer.parseInt(productQuantityField.getText()) > 0) {*/
        String name = productNameField.getText();
        String producer = productProducerField.getText();
        String characteristics = productCharacteristicsField.getText();
        int quantity;
        if (productQuantityField.getText().equals(""))
            quantity = 0;
        else
            quantity = Integer.parseInt(productQuantityField.getText());
        Date date;
        if (productDateField.getValue() == null)
            date = null;
        else
            date = Date.valueOf(productDateField.getValue());
        int price;
        if (productPriceField.getText().equals(""))
            price = 0;
        else
            price = Integer.parseInt(productPriceField.getText());
        Product _product = new Product(0, name, producer, characteristics, quantity, date, price);
        arrayList = clientWork.searchFromProductTable(_product);
        if(arrayList.size() == 0)
            clientWork.dialogWindow("Ничего не найдено!", "Information");
        else
            productTableView();

            /*} else
                clientWork.dialogWindow("Введены некорректные данные!", "Error");
        } else
            clientWork.dialogWindow("Введены некорректные данные!", "Error");*/
    }

    /*@FXML
    void selectAndGoButtonAction(ActionEvent event){
        if(product != null) {
            AdminMenuController.productSt = product;
            Stage stage = (Stage) selectAndGoButton.getScene().getWindow();
            stage.close();
        }
        else
            clientWork.dialogWindow("Выберите товар из таблицы!", "Information");
    }*/

    @FXML
    void returnButtonAction(ActionEvent event) {
        if(product != null) {
            AdminMenuController.productSt = product;
            Stage stage = (Stage) returnButton.getScene().getWindow();
            stage.close();
        }
        else {
            Stage stage = (Stage) returnButton.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    void productTableAction(MouseEvent event) {
        product = productTable.getSelectionModel().selectedItemProperty().get();
    }

    @FXML
    void initialize() {
        productTableView();
    }

    private void productTableView(){
        productIDColumn.setCellValueFactory(new PropertyValueFactory<>("productID"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        productProducerColumn.setCellValueFactory(new PropertyValueFactory<>("productProducer"));
        productCharacteristicColumn.setCellValueFactory(new PropertyValueFactory<>("productCharacteristic"));
        productQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("productQuantity"));
        productDateColumn.setCellValueFactory(new PropertyValueFactory<>("productDate"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
        productTable.setItems(FXCollections.observableArrayList(arrayList));
    }

}
