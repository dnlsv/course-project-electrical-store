package client.controller;

import java.net.URL;
import java.util.ResourceBundle;

import client.clientwork.ClientWork;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import server.model.Order;

public class DeleteOrderController {

    ClientWork clientWork = new ClientWork();
    private Order order;
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
        clientWork.deleteFromOrderTable(order.getOrderID());

        clientWork.dialogWindow("Заказ удален!", "Information");

        Stage stage = (Stage) yesButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void initialize() {
    }

    public void setOrder(Order order) {
        this.order = order;
    }

}
