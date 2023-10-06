package com.example.cgm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.Label;



public class SetUpController implements Initializable {

    @FXML
    private ImageView SetUpImageView;

    @FXML
    private ImageView IconImageView;

    @FXML
    private Label lblSetup;

    @FXML
    private Button btnLogIn;

    @FXML
    private Button btnSignIn;

    @FXML
    private Button btnSetUpExit;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle ){

        File setUpFile = new File("Image/SetUp-File.png");
        Image SetUpImage = new Image(setUpFile.toURI().toString());
        SetUpImageView.setImage(SetUpImage);

        File setFile = new File("Image/setUp.png");
        Image setImage = new Image(setFile.toURI().toString());
        IconImageView.setImage(setImage);



    }
    public void ExitButtonOnAction(ActionEvent event){

        Stage stage = (Stage) btnSetUpExit.getScene().getWindow();
        stage.close();


    }
    public void LoginButtonOnAction(ActionEvent event){
        Stage stage = (Stage) btnLogIn.getScene().getWindow();
        stage.close();
        LogInGUI();
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
    public void SignInButtonOnAction(ActionEvent event){
        Stage stage = (Stage) btnSignIn.getScene().getWindow();
        stage.close();
        SignInGUI();
    }
    public void SignInGUI() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("SignInUI.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 520, 580);

            Stage SignInStage = new Stage();
            SignInStage.setTitle("Sign In");
            SignInStage.setScene(scene);
            SignInStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}
