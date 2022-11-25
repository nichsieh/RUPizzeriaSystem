package project4.rupizzeria;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Application class to run the program starting at MainView
 * @author Kayla Kam and Nicole Hsieh
 */
public class MainViewApplication extends Application {
    /**
     * launch the application
     * @param stage stage for mainview
     * @throws IOException throws exceptions
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainViewApplication.class.getResource("MainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 600);
        stage.setTitle("RU Pizzeria");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * launches the program
     * @param args lanches program
     */
    public static void main(String[] args) {
        launch();
    }
}