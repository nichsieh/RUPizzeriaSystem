module project4.rupizzaria {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.testng;
    requires junit;


    opens project4.rupizzeria to javafx.fxml;
    exports project4.rupizzeria;
}