module com.example.ex {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ex to javafx.fxml;
    exports com.example.ex;
}