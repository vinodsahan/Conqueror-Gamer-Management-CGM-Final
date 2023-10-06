package com.example.cgm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class GameOverController implements Initializable {

    @FXML
    private ImageView SettingsImageView;

    @FXML
    private Button btnExit;

    @FXML
    private Button btnMainMenu;

    @FXML
    private Button btnRestart;

    @FXML
    private ImageView gameOverImageView;

    @FXML
    private Label lblGameOver;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File brandingFile = new File("Image/Settings.png");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        SettingsImageView.setImage(brandingImage);

        File LockFile = new File("Image/game-over.png");
        Image LockImage = new Image(LockFile.toURI().toString());
        gameOverImageView.setImage(LockImage);
    }
    public void ExitBottonOnAction(ActionEvent event){
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }
    public void MainMenuBottonOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnMainMenu.getScene().getWindow();
        stage.close();
        MainGUI();
    }
    public void MainGUI() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("cgmMainPageUI.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 900, 650);

            Stage loginStage = new Stage();
            loginStage.setTitle("Main Menu");
            loginStage.setScene(scene);
            loginStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    public void RestartButtonOnAction(ActionEvent event) throws MalformedURLException, RemoteException {
        Stage stage = (Stage) btnRestart.getScene().getWindow();
        stage.close();
        RestartGame();
    }
    public void RestartGame() {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("GameUI.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 900, 650);
            Stage GameStage = new Stage();
            GameStage.setTitle("The Game");
            GameStage.setScene(scene);
            GameStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}
