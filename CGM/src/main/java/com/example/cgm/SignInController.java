package com.example.cgm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;

public class SignInController implements Initializable {

    @FXML
    private ImageView ShieldmageView;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnSignin;

    @FXML
    private Label lblSignIn;

    /*@FXML
    private Label lblSign;*/

    @FXML
    private Label lblConfirmPassword;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private PasswordField txtConfirmPassword;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtUserName;



    public void initialize(URL url, ResourceBundle resourceBundle){
        File shieldFile = new File("Image/shield.png");
        Image shieldImage = new Image(shieldFile.toURI().toString());
        ShieldmageView.setImage(shieldImage);

    }

    public void SignInbuttonOnAction(ActionEvent event){
        if (txtPassword.getText().equals(txtConfirmPassword.getText())){
            SignInUser();
            lblConfirmPassword.setText("");
        }else {
            lblConfirmPassword.setText("Password Does Not Match!");
        }
    }

    public void BackbuttonOnAction(ActionEvent event){
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.close();
        SetUpMenuGUI();
    }
    public void SetUpMenuGUI() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("SetupMenuUI.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);

            Stage loginStage = new Stage();
            loginStage.setTitle("Set Up Menu");
            loginStage.setScene(scene);
            loginStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void LogInGUI() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LogInUI.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 520, 400);

            Stage loginStage = new Stage();
            loginStage.setTitle("Log In");
            loginStage.setScene(scene);
            loginStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    public void SignInUser(){
        DatabaseConection connectNow = new DatabaseConection();
        Connection connectDB = connectNow.getConnection();

            String firstName = txtFirstName.getText();
            String lastName = txtLastName.getText();
            String username = txtUserName.getText();
            String password = txtPassword.getText();

            String insertFeilds = "INSERT INTO desmodb.useraccount(firstName, lastName, userName, password) VALUES ('";
            String insertValues = firstName + "','" + lastName + "','" + username + "','" + password + "')";
            String insertRegister = insertFeilds + insertValues;


            try {
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(insertRegister);
                lblSignIn.setText("User has been Sign In Successfilly");
                Stage stage = (Stage) btnSignin.getScene().getWindow();
                stage.close();
                LogInGUI();
            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
    }
}
