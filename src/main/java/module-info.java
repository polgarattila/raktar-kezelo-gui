module com.raktar.raktarapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.raktar.raktarapp to javafx.fxml;
    exports com.raktar.raktarapp;
}