package project4.rupizzeria;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Objects;

/**
 * Controller for the StoreOrderView.fxml
 * @author Kayla Kam and Nicole Hsieh
 */
public class StoreOrderController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    ChoiceBox orderNumberBox;
    @FXML
    ListView orderDisplay;
    @FXML
    TextField total;
    static StoreOrders storeOrders = MainViewController.storeOrders;
    String orderNumber;
    Order order;
    static ObservableList<Integer> orders = FXCollections.observableArrayList(storeOrders.getOrderNumbers());

    /**
     * initializes the choice box with the pizza orders and their number
     */
    @FXML
    public void initialize(){
        orderNumberBox.setItems(orders);
        orderNumberBox.setOnAction(e -> setOrder(orderNumberBox));
    }

    /**
     * called after initilizing the choice box or when the user selects an order to analyse
     * sets the information of the selected order
     * @param orderNumberBox
     */
    public void setOrder( ChoiceBox orderNumberBox ){
        orderNumber = orderNumberBox.getValue().toString();
        order = storeOrders.find(orderNumber);
        if (order != null){
            ObservableList<Pizza> ordersObservable = FXCollections.observableArrayList(order.getPizzaOrders());
            orderDisplay.setItems(ordersObservable);
            total.setText(String.format( "%.2f", order.getTotal()));
        }
    }

    /**
     * called when the user selects the cancel order button
     */
    public void cancelOrderButton(){
        storeOrders.remove(order);
    }

    /**
     * called when the user clicks on the back button
     * @param event
     * @throws IOException
     */
    public void backToMainView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainView.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("RU Pizzeria");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * method to export all the orders into a file
     * @param event
     */
    @FXML
    public void exportFile(ActionEvent event){
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open Target File for the Export");
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        new FileChooser.ExtensionFilter("All Files", "*.*");
        Stage stage = new Stage();
        File targetFile = chooser.showSaveDialog(stage);

        try {
            PrintStream writer = new PrintStream(targetFile);
            for (Order order : storeOrders.getOrders()){
                writer.println( "Order "+ order.getOrderNumber() + " :");
                writer.println(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
