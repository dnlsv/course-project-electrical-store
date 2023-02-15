package client.controller;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client.clientwork.ClientWork;
import client.clientwork.Validation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import server.model.Supply;

public class EditSupplyController {

    private Supply supply;
    ClientWork clientWork = new ClientWork();
    Validation validation = new Validation();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField supplyQuantityField;

    @FXML
    private Button editSupplyButton;

    @FXML
    private Button returnButton;

    @FXML
    private TextField supplyCostField;

    @FXML
    private DatePicker supplyDateField;

    @FXML
    private ChoiceBox<Integer> supplyProductIDChoiceBox;

    @FXML
    private ChoiceBox<String> supplyUserNameChoiceBox;

    @FXML
    void editSupplyButtonAction(ActionEvent event) {
        if (!supplyProductIDChoiceBox.getValue().equals("") && !supplyQuantityField.getText().equals("") &&
                !supplyDateField.getValue().equals("") && !supplyUserNameChoiceBox.getValue().equals("") &&
                !supplyCostField.getText().equals("")) {
            if (validation.validationInt(supplyQuantityField.getText()) == true &&
                    validation.validationInt(supplyCostField.getText()) == true) {
                if (Integer.parseInt(supplyQuantityField.getText()) > 0 &&
                        Integer.parseInt(supplyCostField.getText()) > 0) {
                    int productID = supplyProductIDChoiceBox.getValue();
                    int quantity = Integer.parseInt(supplyQuantityField.getText());
                    Date date = Date.valueOf(supplyDateField.getValue());
                    String userName = supplyUserNameChoiceBox.getValue();
                    int cost = Integer.parseInt(supplyCostField.getText());
                    Supply supply = new Supply(this.supply.getSupplyID(), productID, quantity, date, userName, cost);
                    clientWork.editFromSupplyTable(supply);

                    clientWork.dialogWindow("Поставка отредактирована!", "Information");

                    Stage stage = (Stage) editSupplyButton.getScene().getWindow();
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
        setUserNameChoiceBox();
        setProductIDChoiceBox();
    }

    public void setSupply(Supply supply){
        this.supply = supply;
        supplyProductIDChoiceBox.setValue(supply.getSupplyProductID());
        supplyQuantityField.setText(Integer.toString(supply.getSupplyQuantity()));
        supplyDateField.setValue(LocalDate.parse(String.valueOf(supply.getSupplyDate())));
        supplyUserNameChoiceBox.setValue(supply.getSupplyUserName());
        supplyCostField.setText(Integer.toString(supply.getSupplyCost()));
    }

    private void setUserNameChoiceBox(){
        ArrayList<String> userNameList = clientWork.getUserNameList();
        ObservableList<String> list = FXCollections.observableArrayList();
        list.addAll(userNameList);
        supplyUserNameChoiceBox.getItems().addAll(list);
    }

    private void setProductIDChoiceBox(){
        ArrayList<Integer> productIDList = clientWork.getProductIDList();
        ObservableList<Integer> list = FXCollections.observableArrayList();
        list.addAll(productIDList);
        supplyProductIDChoiceBox.getItems().addAll(list);
    }
}
