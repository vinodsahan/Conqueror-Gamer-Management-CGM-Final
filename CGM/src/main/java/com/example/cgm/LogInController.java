package com.example.cgm;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LogInController implements Initializable {
    @FXML
    private Button btnCancel;

    @FXML
    private Button btnLogin;

    @FXML
    private Label lblLogin;

    @FXML
    private ImageView brandingImageView;

    @FXML
    private ImageView LockImageView;

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File brandingFile = new File("Image/game-console.png");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);

        File LockFile = new File("Image/padlock.png");
        Image LockImage = new Image(LockFile.toURI().toString());
        LockImageView.setImage(LockImage);
    }

    public void loginButtonOnAction(ActionEvent event){
        lblLogin.setText("");
        if (txtUsername.getText().isBlank() == false && txtPassword.getText().isBlank() == false){
            validateLogin();
        }else{
            lblLogin.setText("Please enter Username and Password");
        }
    }
    public void CancelBottonOnAction(ActionEvent event){
        Stage stage = (Stage) btnCancel.getScene().getWindow();
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
    public void MainGUI() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("cgmMainPageUI.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1200, 720);

            Stage loginStage = new Stage();
            loginStage.setTitle("Main Menu");
            loginStage.setScene(scene);
            loginStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    public void validateLogin(){
        DatabaseConection conection = new DatabaseConection();
        Connection connectDB = conection.getConnection();
        String verifyLogin = "SELECT count(1) FROM desmodb.useraccount WHERE userName = '" + txtUsername.getText() + "' AND password = '" + txtPassword.getText() + "'";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()) {
                if (queryResult.getInt(1)== 1){
                    Stage stage = (Stage) btnLogin.getScene().getWindow();
                    stage.close();
                    MainGUI();
                }else {
                    lblLogin.setText("Invalid Login Details, Please Try Again");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void createAccountForm(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("SignInUi.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 520, 580);
            Stage signinStage = new Stage();
            signinStage.setTitle("Sign In");
            signinStage.setScene(scene);
            signinStage.show();

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
}