package com.example.cgm;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;


public class cgmMainPageController implements Initializable{
    @FXML
    private ImageView AcivementView;

    @FXML
    private ImageView CaptureView;

    @FXML
    private ImageView ExitView;

    @FXML
    private ImageView FpsView;

    @FXML
    private ImageView GameView;

    @FXML
    private ImageView HomeView;

    @FXML
    private ImageView LoginView;

    @FXML
    private ImageView MenuImageView;

    @FXML
    private VBox MenuVbox;

    @FXML
    private ImageView MenuView;

    @FXML
    private ImageView MenuView1;

    @FXML
    private ImageView SettingsImageView;

    @FXML
    private ImageView monitorHardwareView;

    @FXML
    private ImageView SigninView;

    @FXML
    private AnchorPane WallpaperView1;

    @FXML
    private AnchorPane WallpaperView2;

    @FXML
    private AnchorPane WallpaperView3;


    @FXML
    private Button btnExit;

    @FXML
    private Button btnHighScore;

    @FXML
    private ImageView MonitorView;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnSignIn;

    @FXML
    private Button btnStart;

    @FXML
    private Label lblMenu;

    @FXML
    private Label lblMenuBack;

    @FXML
    private AnchorPane slider;

    @FXML
    private ImageView Image1;

    @FXML
    private ImageView Image2;

    @FXML
    private ImageView Image3;

    @FXML
    private Button BtnDiagnosis;

    @FXML
    private Button btnFPS;

    @FXML
    private Label lblFPS;

    private long lastTime = 0;

    private int frameCount = 0;

    private int fps = 0;


    @FXML
    private Button BtnMonitor;

    @FXML
    private Button btnAchivevements;

    @FXML
    private Button btnGmaingMode;

    @FXML
    private Button btnCapture;

    @FXML
    private AnchorPane paneAchievement;

    @FXML
    private AnchorPane panehardware;

    @FXML
    private BorderPane paneCGM;



    private int currentImageIndex = 0;

    private Timeline timeline;


    public cgmMainPageController() {
    }


    public void translationAnimation(double duration, int i, Node node, double width){
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(duration),node);
        translateTransition.setByX(width);
        translateTransition.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
//        File gammingFile = new File("Image/arcade-game.png");
//        Image gammingImage = new Image(gammingFile.toURI().toString());
//        GameImageView.setImage(gammingImage);

        MenuImageView.setOnMouseClicked(mouseEvent ->
                {
                    System.exit(0);
                }
                );

        WallpaperView1.setVisible(true);
        WallpaperView2.setVisible(false);
        WallpaperView3.setVisible(false);

        // Create a timeline to automatically switch images
        timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> switchImage()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        File HomeFile = new File("Image/Home/dash.png");
        Image HomeImage = new Image(HomeFile.toURI().toString());
        HomeView.setImage(HomeImage);

        File CaptureFile = new File("Image/Home/capture.png");
        Image CaptureImage = new Image(CaptureFile.toURI().toString());
        CaptureView.setImage(CaptureImage);

        File AchivementFile = new File("Image/Home/Achive ori.png");
        Image AchivementImage = new Image(AchivementFile.toURI().toString());
        AcivementView.setImage(AchivementImage);

        File monitorHardwareFile = new File("Image/Achievement/Monitor.png");
        Image monitorHardwareImage = new Image(monitorHardwareFile.toURI().toString());
        monitorHardwareView.setImage(monitorHardwareImage);

        File GameFile = new File("Image/Home/GAmming mode.png");
        Image GameImage = new Image(GameFile.toURI().toString());
        GameView.setImage(GameImage);

        File HomeMenuFile = new File("Image/Home/menu-bar (White).png");
        Image HomeMenuImage = new Image(HomeMenuFile.toURI().toString());
        MenuView.setImage(HomeMenuImage);

        File HomeMenu1File = new File("Image/Home/menu-bar.png");
        Image HomeMenu1Image = new Image(HomeMenu1File.toURI().toString());
        MenuView1.setImage(HomeMenu1Image);

        File FpsFile = new File("Image/Home/fps.png");
        Image FpsImage = new Image(FpsFile.toURI().toString());
        FpsView.setImage(FpsImage);

        File LoginFile = new File("Image/Home/Log in.png");
        Image LoginImage = new Image(LoginFile.toURI().toString());
        LoginView.setImage(LoginImage);

        File SigninFile = new File("Image/Home/Sign in.png");
        Image SigninImage = new Image(SigninFile.toURI().toString());
        SigninView.setImage(SigninImage);

        File ExitFile = new File("Image/Home/exit.png");
        Image ExitImage = new Image(ExitFile.toURI().toString());
        ExitView.setImage(ExitImage);

        File MenuFile = new File("Image/Home/exit.png");
        Image MenuImage = new Image(MenuFile.toURI().toString());
        MenuImageView.setImage(MenuImage);

        File SettingsFile = new File("Image/Settings.png");
        Image SettingsImage = new Image(SettingsFile.toURI().toString());
        SettingsImageView.setImage(SettingsImage);

        File Image1File = new File("Image/Backgrounds_Images/1.png");
        Image Image1Image = new Image(Image1File.toURI().toString());
        Image1.setImage(Image1Image);

        File Image2File = new File("Image/Backgrounds_Images/2.png");
        Image Image2Image = new Image(Image2File.toURI().toString());
        Image2.setImage(Image2Image);



        translationAnimation(0,5,WallpaperView2,829);
        translationAnimation(0,5,WallpaperView3,829);


        paneCGM.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.F1) {
                captureAndSaveScreenshot();
            }
        });

// Add event handler to btnCapture button
        btnCapture.setOnAction(event -> {
            captureAndSaveScreenshot();
        });


        Button btnAchievements = new Button("Achievements");
        AnchorPane pcDetails = new AnchorPane();



// Method to capture and save screenshot


//        btnGmaingMode.setOnAction(event -> {
//            try {
//                // Run the powercfg.exe command to switch to the Ultimate power plan
//                ProcessBuilder builder = new ProcessBuilder("powercfg.exe", "/setactive", "8c5e7fda-e8bf-4a96-9a85-a6e23a8c635c");
//                builder.start();
//
//                // Show a popup message to indicate that the power plan has been switched
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle("Power Plan Switched");
//                alert.setHeaderText(null);
//                alert.setContentText("The power plan has been switched to Ultimate.");
//                alert.showAndWait();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });

        btnGmaingMode.setOnAction(event -> {
            // Define the available power plans
            Map<String, String> powerPlans = new LinkedHashMap<>();
            powerPlans.put("Balanced", "381b4222-f694-41f0-9685-ff5bb260df2e");
            powerPlans.put("High Performance", "8c5e7fda-e8bf-4a96-9a85-a6e23a8c635c");
            powerPlans.put("Ultimate Performance", "e9a42b02-d5df-448d-aa00-03f14749eb61");

            // Create a ChoiceDialog to let the user choose a power plan
            ChoiceDialog<String> dialog = new ChoiceDialog<>("Balanced", powerPlans.keySet());
            dialog.setTitle("Choose Power Plan");
            dialog.setHeaderText(null);
            dialog.setContentText("Select a power plan:");

            URL cssFile = getClass().getResource("CGM_CSS/choice-dialog.css");
            if (cssFile != null) {
                dialog.getDialogPane().getStylesheets().add(cssFile.toExternalForm());
            }

            // Show the dialog and wait for the user to make a choice
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                String selectedPlan = result.get();
                String selectedPlanId = powerPlans.get(selectedPlan);
                try {
                    // Run the powercfg.exe command to switch to the selected power plan
                    ProcessBuilder builder = new ProcessBuilder("powercfg.exe", "/setactive", selectedPlanId);
                    builder.start();

                    // Show a popup message to indicate that the power plan has been switched
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Power Plan Switched");
                    alert.setHeaderText(null);
                    alert.setContentText("The power plan has been switched to " + selectedPlan + ".");
                    alert.showAndWait();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        slider.setTranslateX(-400);
        lblMenu.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(0);
            slide.play();

            slider.setTranslateX(-400);

            slide.setOnFinished((ActionEvent e)-> {
                lblMenu.setVisible(false);
                lblMenuBack.setVisible(true);
            });
        });

        lblMenuBack.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(-400);
            slide.play();

            slider.setTranslateX(0);

            slide.setOnFinished((ActionEvent e)-> {
                lblMenu.setVisible(true);
                lblMenuBack.setVisible(false);
            });
        });
    }


    int show = 0;
    @FXML
    void back(ActionEvent event) {
        if (show == 0){
            translationAnimation(0,5,WallpaperView2,-829);
            show++;
        } else if (show == 1) {
            translationAnimation(0,5,WallpaperView3,-829);
            show++;
        }
    }

    @FXML
    void next(ActionEvent event) {
        if (show == 0){
            translationAnimation(0,5,WallpaperView2,-829);
            show--;
        } else if (show == 1) {
            translationAnimation(0,5,WallpaperView3,-829);
            show--;
        }
    }

    @FXML
    void handleBtnDiagnosis(ActionEvent event) {
        // Load the FXML file that contains the UI for the system diagnosis
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/path/to/DiagnosisUI.fxml"));
        try {
            // Load the root node of the FXML file (in this case, an AnchorPane)
            AnchorPane diagnosisUI = loader.load();

            // Create a new stage to display the diagnosis UI
            Stage diagnosisStage = new Stage();
            diagnosisStage.setTitle("System Diagnosis");
            diagnosisStage.setScene(new Scene(diagnosisUI));

            // Show the diagnosis stage
            diagnosisStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void countFPS() {
        AnimationTimer timer = new AnimationTimer() {

            @Override
            public void handle(long now) {
                if (lastTime != 0) {
                    long diff = now - lastTime;
                    if (diff >= 1_000_000_000) { // 1 second in nanoseconds
                        lblFPS.setText(Integer.toString(fps));
                        fps = 0;
                        lastTime = now;
                    } else {
                        fps++;
                    }
                } else {
                    lastTime = now;
                }
            }
        };

        timer.start();
    }


    public void ExitButtonOnAction(ActionEvent event){
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }
    public void StartButtonOnAction(ActionEvent event) throws MalformedURLException, RemoteException {
        Stage stage = (Stage) btnStart.getScene().getWindow();
        stage.close();
        StartGame();
    }

    public void setOnAction(ActionEvent event) throws MalformedURLException, RemoteException {
        Stage stage = (Stage) BtnMonitor.getScene().getWindow();
        stage.show();
        HardwareMonitor();
    }

    public void HardwareMonitor(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HardwareMonitor.fxml"));
            AnchorPane hardwareMonitor = loader.load();
            panehardware.getChildren().setAll(hardwareMonitor);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setOnAction1(ActionEvent event) throws MalformedURLException, RemoteException {
        Stage stage = (Stage) btnAchivevements.getScene().getWindow();
        stage.show();
        AchievementMonitor();
    }

    public void AchievementMonitor(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("achievementManage.fxml"));
            AnchorPane AhcievementMonitor = loader.load();
            paneAchievement.getChildren().setAll(AhcievementMonitor);
        } catch (IOException e) {
            e.printStackTrace();
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
    public void StartGame() {

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

    public void HighScoreButtonOnAction(ActionEvent event){
        Stage stage = (Stage) btnHighScore.getScene().getWindow();
        stage.close();
        HighScore();
    }

    public void HighScore() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Scoresearch.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 900, 650);

            Stage loginStage = new Stage();
            loginStage.setTitle("Score Table");
            loginStage.setScene(scene);
            loginStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void LoginButtonOnAction(ActionEvent event){
        Stage stage = (Stage) btnLogin.getScene().getWindow();
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

    private void switchImage() {
        switch (currentImageIndex) {
            case 0 -> {
                WallpaperView1.setVisible(false);
                WallpaperView2.setVisible(true);
                WallpaperView3.setVisible(false);
                currentImageIndex = 1;
            }
            case 1 -> {
                WallpaperView1.setVisible(false);
                WallpaperView2.setVisible(false);
                WallpaperView3.setVisible(true);
                currentImageIndex = 2;
            }
            case 2 -> {
                WallpaperView1.setVisible(true);
                WallpaperView2.setVisible(false);
                WallpaperView3.setVisible(false);
                currentImageIndex = 0;
            }
        }
    }




    private void captureAndSaveScreenshot(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Screenshot");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png"));
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        Stage stage = (Stage) MenuVbox.getScene().getWindow();
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((screenBounds.getHeight() - stage.getHeight()) / 2);
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            try {
                // Get the screen dimensions
                Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
                // Create a BufferedImage of the screen
                BufferedImage capture = new Robot().createScreenCapture(screenRect);
                // Save the BufferedImage to a PNG file
                ImageIO.write(capture, "png", file);
            } catch (IOException | AWTException ex) {
                ex.printStackTrace();
            }
        }
    }

}
