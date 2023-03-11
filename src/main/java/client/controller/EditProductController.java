package client.controller;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

import client.clientwork.ClientWork;
import client.clientwork.Validation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import server.model.Product;

public class EditProductController {

    private Product product;
    ClientWork clientWork = new ClientWork();
    Validation validation = new Validation();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField productNameField;

    @FXML
    private TextField productCharacteristicsField;

    @FXML
    private TextField productProducerField;

    @FXML
    private DatePicker productDateField;

    @FXML
    private TextField productPriceField;

    @FXML
    private Button returnButton;

    @FXML
    private Button editProductButton;

    @FXML
    private TextField productQuantityField;

    @FXML
    void editProductButtonAction(ActionEvent event) {
        if (!productNameField.getText().equals("") && !productProducerField.getText().equals("") &&
                !productCharacteristicsField.getText().equals("") && !productQuantityField.getText().equals("") &&
                !productDateField.getValue().equals("") && !productPriceField.getText().equals("")) {
            if (validation.validationInt(productQuantityField.getText()) == true &&
                    validation.validationInt(productPriceField.getText()) == true) {
                if (Integer.parseInt(productPriceField.getText()) > 0 &&
                        Integer.parseInt(productQuantityField.getText()) > 0) {
                    String name = productNameField.getText();
                    String producer = productProducerField.getText();
                    String characteristics = productCharacteristicsField.getText();
                    int quantity = Integer.parseInt(productQuantityField.getText());
                    Date date = Date.valueOf(productDateField.getValue());
                    int price = Integer.parseInt(productPriceField.getText());
                    Product product = new Product(this.product.getProductID(), name, producer, characteristics, quantity, date, price);
                    clientWork.editFromProductTable(product);

                    clientWork.dialogWindow("Товар отредактирован!", "Information");

                    Stage stage = (Stage) editProductButton.getScene().getWindow();
                    stage.close();
                } else
                    clientWork.dialogWindow("Введены некорректные данные!", "Error");
            } else
                clientWork.dialogWindow("Введены некорректные данные!", "Error");
        } else
            clientWork.dialogWindow("Заполните все поля!", "Information");
    }

    @FXML
    void returnButtonAction(ActionEvent event) {
        Stage stage = (Stage) returnButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void initialize() {

    }

    public void setProduct(Product product) {
        this.product = product;
        productNameField.setText(product.getProductName());
        productProducerField.setText(product.getProductProducer());
        productCharacteristicsField.setText(product.getProductCharacteristic());
        productQuantityField.setText(Integer.toString(product.getProductQuantity()));
        productDateField.setValue(LocalDate.parse(String.valueOf(product.getProductDate())));
        productPriceField.setText(Integer.toString(product.getProductPrice()));
    }
}
