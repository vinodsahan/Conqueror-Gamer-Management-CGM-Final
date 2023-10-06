module com.example.impo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.impo to javafx.fxml;
    exports com.example.impo;
}