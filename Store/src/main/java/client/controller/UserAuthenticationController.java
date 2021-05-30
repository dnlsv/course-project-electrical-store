package client.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import client.clientwork.ClientWork;
import client.clientwork.Statistics;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class UserAuthenticationController {

    private int roleID;
    ClientWork clientWork = new ClientWork();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField loginField;

    @FXML
    private Button authenticateButton;

    @FXML
    void authenticateButtonAction(ActionEvent event) {
        String login = loginField.getText();
        String password = passwordField.getText();
        boolean flag = clientWork.accountVerification(login, password);
        if(flag){
            roleID = clientWork.getUserRoleID(login);
            if(roleID == 2){
                clientWork.dialogWindow("Вы вошли в учетную запись сотрудника!", "Information");

                Stage st = (Stage)authenticateButton.getScene().getWindow();
                st.close();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/employeeMenu.fxml"));

                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Parent root = loader.getRoot();

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Меню сотрудника");
                stage.showAndWait();
            }
            if(roleID == 1){
                clientWork.dialogWindow("Вы вошли в учетную запись администратора!", "Information");

                Stage st = (Stage)authenticateButton.getScene().getWindow();
                st.close();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/adminMenu.fxml"));

                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Parent root = loader.getRoot();

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Меню администратора");
                stage.showAndWait();
            }
        }
        else{
            clientWork.dialogWindow("Вы ввели неверный логин или пароль!", "Error");
            loginField.setText("");
            passwordField.setText("");
        }
    }

    @FXML
    void initialize() {
        getStatisticalData();
    }

    private void getStatisticalData(){
        Statistics.beginNumberOfOrders = clientWork.getNumberOfOrders();
        Statistics.beginNumberOfProducts = clientWork.getNumberOfProducts();
        Statistics.beginNumberOfSupplies = clientWork.getNumberOfSupplies();
    }

}
