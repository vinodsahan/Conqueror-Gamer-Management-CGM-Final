module com.example.desmo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires javafx.media;
    requires java.json;
    requires json.simple;
    requires java.rmi;
    requires java.desktop;
    requires mysql.connector.j;
    requires jdk.management;


    opens com.example.cgm to javafx.fxml;
    exports com.example.cgm;
}