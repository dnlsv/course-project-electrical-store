package client;

import client.clientwork.Client;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/userAuthentication.fxml"));
        primaryStage.setTitle("Магазин электротоваров");
        primaryStage.setScene(new Scene(root, 450, 350));
        primaryStage.show();
    }

    public static void main(String[] args) {
        Client.startClient();

        launch(args);
    }
}
