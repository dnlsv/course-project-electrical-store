package client.controller;

import java.net.URL;
import java.util.ResourceBundle;

import client.clientwork.ClientWork;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import server.model.Product;

public class DeleteProductController {

    private Product product;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button yesButton;

    @FXML
    private Button noButton;

    @FXML
    void noButtonAction(ActionEvent event) {
        Stage stage = (Stage) noButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void yesButtonAction(ActionEvent event) {
        ClientWork clientWork = new ClientWork();
        clientWork.deleteFromProductTable(product.getProductID());

        clientWork.dialogWindow("Товар удален!", "Information");

        Stage stage = (Stage) yesButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void initialize() {

    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
