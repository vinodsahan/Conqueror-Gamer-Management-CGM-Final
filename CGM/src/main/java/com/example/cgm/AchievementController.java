package com.example.cgm;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class AchievementController implements Initializable {

    @FXML
    private ImageView AchiveImage;

    @FXML
    private Label assassinsCreedGemsLabel;

    @FXML
    private ImageView assassinsCreedImage;

    @FXML
    private Label assassinsCreedSkillsLabel;

    @FXML
    private Label assassinsCreedTimeLabel;

    @FXML
    private Label callOfDutyGemsLabel;

    @FXML
    private ImageView callOfDutyImage;

    @FXML
    private Label callOfDutySkillsLabel;

    @FXML
    private Label callOfDutyTimeLabel;

    @FXML
    private Label pubgGemsLabel;

    @FXML
    private ImageView pubgImage;

    @FXML
    private Label pubgSkillsLabel;

    @FXML
    private Label pubgTimeLabel;

    @FXML
    private VBox root;

    @FXML
    private Label witcherGemsLabel;

    @FXML
    private ImageView witcherImage;

    @FXML
    private Label witcherSkillsLabel;

    @FXML
    private Label witcherTimeLabel;

    @Override
    public void initialize (URL url, ResourceBundle resourceBundle){

        File HardwareFile = new File("Image/Achievement/Achive ori.png");
        Image HardwareImage = new Image(HardwareFile.toURI().toString());
        AchiveImage.setImage(HardwareImage);

        // Set labels
        assassinsCreedTimeLabel.setText("Time Played: 50 hours");
        assassinsCreedGemsLabel.setText("Gems Earned: 2500");
        assassinsCreedSkillsLabel.setText("Skills Gained: Stealth, Parkour, Combat");

        // Set the time played, gems earned, and skills gained for Call of Duty Modern Warfare
        callOfDutyTimeLabel.setText("Time Played: 75 hours");
        callOfDutyGemsLabel.setText("Gems Earned: 3750");
        callOfDutySkillsLabel.setText("Skills Gained: Teamwork, Strategy, Reflexes");

        // Set the time played, gems earned, and skills gained for PUBG
        pubgTimeLabel.setText("Time Played: 100 hours");
        pubgGemsLabel.setText("Gems Earned: 5000");
        pubgSkillsLabel.setText("Skills Gained: Survival, Resource Management, Communication");

        // Set the time played, gems earned, and skills gained for The Witcher
        witcherTimeLabel.setText("Time Played: 30 hours");
        witcherGemsLabel.setText("Gems Earned: 1500");
        witcherSkillsLabel.setText("Skills Gained: Magic, Alchemy, Investigation");
    }
}