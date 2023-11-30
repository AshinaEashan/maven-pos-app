package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import db.DBConnection;
import dto.CustomerDto;
import dto.ItemDto;
import dto.OrderDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.CustomerModel;
import model.ItemModel;
import model.OrderModel;
import model.impl.CustomerModelImpl;
import model.impl.ItemModelImpl;
import model.impl.OrderModelImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PlaceOrderFormController {

    public BorderPane placeOrderPane;
    @FXML
    private JFXTreeTableView<OrderDto> tblCart;

    @FXML
    private JFXComboBox<?> customerID;

    @FXML
    private JFXComboBox<?> itemCode;

    @FXML
    private JFXTextField customerName;

    @FXML
    private JFXTextField itemDesc;

    @FXML
    private Label orderID;

    @FXML
    private JFXTextField unitPrice;

    @FXML
    private JFXTextField qty;

    @FXML
    private TreeTableColumn orderCode;

    @FXML
    private TreeTableColumn orderDesc;

    @FXML
    private TreeTableColumn orderQty;

    @FXML
    private TreeTableColumn orderAmount;

    @FXML
    private TreeTableColumn orderOption;

    @FXML
    private Label totLabel;

    private CustomerModel customerModel = new CustomerModelImpl();
    private ItemModel itemModel = new ItemModelImpl();

    private OrderModel orderModel = new OrderModelImpl();

    private List<CustomerDto> customerDtos;
    private List<ItemDto> itemDtos;
    private List<OrderDto> orderDtos;

    public void initialize(){
        orderCode.setCellValueFactory(new PropertyValueFactory<>("Code"));
        orderDesc.setCellValueFactory(new PropertyValueFactory<>("Desc"));
        orderQty.setCellValueFactory(new PropertyValueFactory<>("Qty"));
        orderAmount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        orderOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        generateID();

        loadCustomers();
        loadItems();

        customerID.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, id) -> {
            for (CustomerDto dto : customerDtos) {
                if (dto.getId().equals(id)){
                    customerName.setText(dto.getName());
                }
            }
        });
        itemCode.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, code) -> {
            for (ItemDto dto : itemDtos){
                if (dto.getCode().equals(code)){
                    itemDesc.setText(dto.getDesc());
                    unitPrice.setText(String.valueOf(dto.getUnitPrice()));
                }
            }
        });
    }

    private void generateID() {
        try {
            orderDtos = orderModel.allOrders();
            String orderId = orderDtos.get(orderDtos.size()-1).getOrderId();
            int num = Integer.parseInt(orderId.split("[D]")[1]);
            orderID.setText(num<100?"D0"+String.valueOf(++num):"D"+String.valueOf(++num));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void loadItems() {
        try {
            itemDtos = itemModel.allItem();
            ObservableList list = FXCollections.observableArrayList();
            for (ItemDto item : itemDtos) {
                list.add(item.getCode());
            }
            itemCode.setItems(list);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void loadCustomers() {
        try {
            customerDtos = customerModel.allCustomers();
            ObservableList list = FXCollections.observableArrayList();
            for (CustomerDto custDto : customerDtos){
                list.add(custDto.getId());
            }
            customerID.setItems(list);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void AddToCartOnAction(ActionEvent event) {

    }

    @FXML
    void BackButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) placeOrderPane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashboardForm.fxml"))));
            stage.centerOnScreen();
            stage.setTitle("Dashboard");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void PlaceOrderOnAction(ActionEvent event) {

    }

}
