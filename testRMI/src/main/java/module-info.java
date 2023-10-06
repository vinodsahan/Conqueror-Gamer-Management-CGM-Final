module com.example.testrmi {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.testrmi to javafx.fxml;
    exports com.example.testrmi;
}