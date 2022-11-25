package project4.rupizzeria;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * Controller for the CurrentOrderView.fxml
 * @author Kayla Kam and Nicole Hsieh
 */
public class OrderController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    ListView ordersDisplay;
    @FXML
    TextField orderNumber, subtotal, salesTax, total;
    @FXML
    TextArea messages;

    private final static int PERCENT = 100;

    Order currOrder = MainViewController.currOrder;
    StoreOrders storeOrders = MainViewController.storeOrders;

    /**
     * initializes the choice box with the pizza types
     */
    @FXML
    public void initialize(){
        orderNumber.setText(String.valueOf(currOrder.getOrderNumber()));
        ordersDisplay.setItems(FXCollections.observableArrayList(currOrder.getPizzaOrders()));
        subtotal.setText(String.format( "%.2f", currOrder.getSubtotal()));
        salesTax.setText(String.valueOf(Order.TAXRATE * PERCENT) + " %");
        total.setText(String.format("%.2f", currOrder.getTotal()));
    }

    /**
     * called when the user presses the cancel order button
     */
    public void cancelOrderButton(){
        currOrder.getPizzaOrders().clear();
        initialize();
        messages.setText("Order Canceled");
    }

    /**
     * called when the use presses the place order button
     */
    public void placeOrderButton(){
        if (!currOrder.getPizzaOrders().isEmpty()){
            if (storeOrders.add(currOrder)){
                // new order
                Order newCurrent = new Order();
                MainViewController.currOrder = newCurrent;
                messages.setText("Pizza order was placed");
            }
        } else{
            messages.setText("No Pizzas were added to the order");
        }
    }

    /**
     * called when the user clicks on the remove pizza button
     */
    public void removePizzaButton(){
        Object selected = ordersDisplay.getSelectionModel().getSelectedItem();
        if ( selected != null){
            Pizza found = currOrder.findPizza((Pizza) selected);
            if (found != null){
                currOrder.remove(found);
                initialize();
                messages.setText("Pizza was removed");
            } else {
                messages.setText("Pizza is not in the order");
            }
        }
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

}
