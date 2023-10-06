package com.example.cgm;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScoreSearchController implements Initializable {

    @FXML
    private TableView<ScoreSearchModel> scoreTableView;

    @FXML
    private TableColumn<ScoreSearchModel, Integer> ScoreIDTableCol;

    @FXML
    private TableColumn<ScoreSearchModel, String> scoreTableCol;

    @FXML
    private TableColumn<ScoreSearchModel, String> userNameTableCol;

    @FXML
    private TextField keywordTextField;

    @FXML
    private ImageView HighScoreImageView;

    @FXML
    private ImageView SettingsImageView;

    @FXML
    private Button btnMainMenu;

    @FXML
    private Button btnHighScore;

    @FXML
    private TextField txtHighScore;

    ObservableList<ScoreSearchModel> scoreSearchModelObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourse){
        File HighScoreFile = new File("Image/high-score.png");
        Image HighScoreImage = new Image(HighScoreFile.toURI().toString());
        HighScoreImageView.setImage(HighScoreImage);

        File SettingsFile = new File("Image/Settings.png");
        Image SettingsImage = new Image(SettingsFile.toURI().toString());
        SettingsImageView.setImage(SettingsImage);

        DatabaseConection conection = new DatabaseConection();
        Connection connection = conection.getConnection();

        //sql querry
        String scoreViewQuerry = "SELECT scoreId, score, userName FROM desmodb.score";

        try {
            Statement statement = connection.createStatement();
            ResultSet querryOutput = statement.executeQuery(scoreViewQuerry);

            while (querryOutput.next()){
                Integer queryscoreID = querryOutput.getInt("scoreId");
                String queryscore = querryOutput.getString("score");
                String queryuserName = querryOutput.getString("userName");

                // populate the ObserverList
                scoreSearchModelObservableList.add(new ScoreSearchModel(queryscoreID, queryscore, queryuserName));
            }
            //Property Value factory

            ScoreIDTableCol.setCellValueFactory(new PropertyValueFactory<>("scoreId"));
            scoreTableCol.setCellValueFactory(new PropertyValueFactory<>("score"));
            userNameTableCol.setCellValueFactory(new PropertyValueFactory<>("userName"));

            scoreTableView.setItems(scoreSearchModelObservableList);

            FilteredList<ScoreSearchModel> filteredData = new FilteredList<>(scoreSearchModelObservableList, b -> true);
            keywordTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(scoreSearchModel -> {
                    if (newValue.isEmpty() || newValue.isBlank() || newValue == null){
                        return true;
                    }
                    String searchKeyword = newValue.toLowerCase();
                    if (scoreSearchModel.getUserName().toLowerCase().indexOf(searchKeyword) > -1){
                        return true;
                    }else if (scoreSearchModel.getScore().toLowerCase().indexOf(searchKeyword) > -1){
                        return true;
                    }else
                        return false;
                });
            });

            SortedList<ScoreSearchModel> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(scoreTableView.comparatorProperty());
            scoreTableView.setItems(sortedData);



        }catch (SQLException e){
            Logger.getLogger(ScoreSearchController.class.getName()).log(Level.SEVERE, null,e);
            e.printStackTrace();
        }
    }
    public void MainMenuButtonOnAction(ActionEvent event) {
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
}
