package project4.rupizzeria;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * Controller for the main view
 * @author Kayla Kam and Nicole Hsieh
 */
public class MainViewController {

    /**
     * stage to be created
     */
    private Stage stage;

    /**
     * scene to be created
     */
    private Scene scene;

    /**
     * root to be created
     */
    private Parent root;

    /**
     * main store order object
     */
    public static StoreOrders storeOrders = new StoreOrders();

    /**
     * current order to be added to the store order
     */
    public static Order currOrder = new Order();

    /**
     * Opens the new york pizza order view
     * @param event ActionEvent
     * @throws IOException exception
     */
    public void openNYPizzaView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("NYPizzaView.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Start a New York Pizza Order");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Opens the chicago pizza order view
     * @param event ActionEvent
     * @throws IOException exception
     */
    public void openChicagoPizzaView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ChicagoPizzaView.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Start a Chicago Pizza Order");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Opens the current order view
     * @param event ActionEvent
     * @throws IOException exception
     */
    public void openCurrentOrderView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("CurrentOrderView.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Current Order");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Opens the store order view
     * @param event ActionEvent
     * @throws IOException exception
     */
    public void openStoreOrderView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("StoreOrderView.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Store Orders");
        stage.setScene(scene);
        stage.show();
    }

}