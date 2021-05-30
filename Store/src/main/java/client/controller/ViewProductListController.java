package client.controller;

import java.net.URL;
import java.util.ResourceBundle;

import client.clientwork.ClientWork;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import server.model.ProductList;

public class ViewProductListController {

    private ProductList productList;
    private int productListOrderID;
    ClientWork clientWork = new ClientWork();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<ProductList> productListTable;

    @FXML
    private TableColumn<?, ?> productListQuantity;

    @FXML
    private TableColumn<?, ?> productListProductIDColumn;

    @FXML
    private Button returnButton;

    @FXML
    private TableColumn<?, ?> productListOrderIDColumn;

    @FXML
    void productListTableAction(MouseEvent event) {
        productList =  productListTable.getSelectionModel().selectedItemProperty().get();
    }

    @FXML
    void returnButtonAction(ActionEvent event) {
        Stage stage = (Stage) returnButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void initialize() {
        productListTableView();
    }

    public void productListTableView(){
        productListOrderIDColumn.setCellValueFactory(new PropertyValueFactory<>("productListOrderID"));
        productListProductIDColumn.setCellValueFactory(new PropertyValueFactory<>("productListProductID"));
        productListQuantity.setCellValueFactory(new PropertyValueFactory<>("productListQuantity"));
        productListTable.setItems(FXCollections.observableArrayList(clientWork.getProductListArrayListByOrderID(productListOrderID)));
    }

    public void setProductListOrderID(int productListOrderID){
        this.productListOrderID = productListOrderID;
        productListTableView();
    }

}
