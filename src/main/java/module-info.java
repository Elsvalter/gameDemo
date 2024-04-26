module com.example.gameuidemo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.gameuidemo to javafx.fxml;
    exports com.example.gameuidemo;
}