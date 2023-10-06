package com.example.cgm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
public class TheGameGUI {

    @FXML
    public AnchorPane root;
    @FXML
    public ImageView imageView;
    @FXML
    public TextField txtAnswer;
    @FXML
    public Text lblLevelCount;
    @FXML
    public Text lblLevel;
    @FXML
    public Text lblRemainingTime;
    @FXML
    public Text lblRemainingTimeCount;
    @FXML
    public Text txtComment;
    @FXML
    public Text playerScore;
    @FXML
    public Text levelScore;
    @FXML
    public TextField txtUserName;
    @FXML
    public Button btnNext;
    @FXML
    public Button btnSubmit;
    @FXML
    public Button btnScoreSave;
    @FXML
    private Button btnExit;
    @FXML
    public Label lblAnswer;
    @FXML
    public Label lblName;

    public int levelCount = 0;
    public int sendLevelCount;
    public int solution;
    int timeCount;
    boolean status = false;
    int score1 = 0;
    int score2 = 0;


    public void initialize() throws IOException, JSONException {

        loadQuestion();

        lblLevel.setText("Question Format ");
        lblLevelCount.setText("");
        btnNext.setText("Let's Play");
        txtAnswer.setVisible(false);
        lblAnswer.setVisible(false);
        lblRemainingTime.setVisible(false);
        lblRemainingTimeCount.setVisible(false);
        txtComment.setVisible(false);
        btnSubmit.setVisible(false);
        lblName.setText("Enter User Name");
        txtUserName.setVisible(true);

    }
    public void btnNextLevel(ActionEvent actionEvent) throws IOException, JSONException {

        txtAnswer.setVisible(true);
        lblAnswer.setVisible(true);
        lblRemainingTime.setVisible(true);
        lblRemainingTimeCount.setVisible(true);
        txtComment.setVisible(false);
        btnSubmit.setVisible(true);
        btnNext.setVisible(false);

        levelCount +=1;
        lblLevel.setText("");
        lblName.setText("");
        txtUserName.setVisible(false);
        lblLevelCount.setText("Level :  " + levelCount);
        btnNext.setText("Next");
        System.out.println("level Count :   " +levelCount);
        score2=0;
        levelScore.setText("Level Score  :  " +score2);



        if (levelCount == 11) {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("GameOver.fxml")));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Game Over");
            stage.centerOnScreen();
            ScoreCounter();

        }

        LevelTimer(levelCount, true);

        try{
            sendLevelCount = levelCount - 1;
            saveAnswer(sendLevelCount);
        } catch (RuntimeException e){

        }

    }
    public void MainMenuBottonOnAction(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("cgmMainPageUI.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Game Over");
        stage.centerOnScreen();
    }
    public void exitBottonOnAction(){
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }

    public int loadQuestion() throws JSONException, IOException {
        JSONObject apiCaller = API.sendGET();
        Image image = new Image(apiCaller.getString("question"));
        imageView.setImage(image);

        int sol = Integer.parseInt(String.valueOf(apiCaller.getInt("solution")));
        System.out.println("solution : " +sol);
        solution = sol;

        return solution;

    }

    public int btnSubmitOnAction (){
        int ans = Integer.parseInt(txtAnswer.getText());
        if (ans == solution){
            btnNext.setVisible(true);
            System.out.println("Answer is correct :" +solution);
            txtComment.setVisible(true);
            txtComment.setText("Congratulations!!");
            btnSubmit.setVisible(false);
            lblAnswer.setVisible(false);
            txtAnswer.setVisible(false);

            TimerStop(true);


        }else {
            txtComment.setVisible(true);
            txtComment.setText("Wrong Answer, Try again..");
            System.out.println("Wrong Answer, try again." +ans);
            txtAnswer.clear();

            score1 = score1-10;
            score2 = score2-10;

            levelScore.setText("Level Score  :  " + score2);
            return score1;
        }
        score1 = 100 + score1;
        playerScore.setText("Player Score  :  " + score1);
        score2 = 100 + score2;
        levelScore.setText("Level Score:  " +score2);
        score2=0;
        return score1;
    }
    public void ScoreCounter() {
        DatabaseConection connectNow = new DatabaseConection();
        Connection connectDB = connectNow.getConnection();

        String username = txtUserName.getText();
        String score = playerScore.getText();


        String insertFields = "INSERT INTO desmodb.score(score, userName) VALUES ('";
        String insertValues =  score + "','" + username+ "')";
        String insertRegister = insertFields + insertValues;
        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertRegister);
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }
    public void ScoreSavebuttonOnAction(ActionEvent event) throws IOException {

        ScoreCounter();
        Parent parent = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("GameOver.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Game Over");
        stage.centerOnScreen();
    }

    private void saveAnswer(int levelCount) {
        String ans = txtAnswer.getText();
        int answer = Integer.parseInt(ans);
        System.out.println("Answer :  " +answer);
        System.out.println("Solution :  " +solution);
        System.out.println("Level count from :  " +levelCount);

        try {
            System.out.println("Next Level");
            TimerStop(false);
            loadQuestion();
            txtAnswer.clear();
            txtAnswer.requestFocus();
        } catch (Exception e) {
            System.out.println("problem have: " + e);
            e.printStackTrace();
        }
    }
    private void Timer (int time, int lvlCount) throws IOException {
        timeCount = time;
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                lblRemainingTimeCount.setText(String.valueOf(timeCount));
                timeCount--;
                if (timeCount == -1) {
                    timer.cancel();
                    saveAnswer(levelCount);
                } else if (status) {
                    timer.cancel();
                    TimerStop(false);
                }
            }
        } ;
        timer.scheduleAtFixedRate(timerTask, 1000,1000);
    }
    private void TimerStop(boolean isIt) {
        status = isIt;
    }
    private void LevelTimer(int level , boolean stat) throws IOException {
        if (level == 1) {
            Timer (60, level);
        } else if (level == 2) {
            TimerStop(true);
            Timer (55, level);
        } else if (level == 3) {
            TimerStop(true);
            Timer (50, level);
        } else if (level == 4) {
            TimerStop(true);
            Timer (45, level);
        } else if (level == 5) {
            TimerStop(true);
            Timer (40, level);
        } else if (level == 6) {
            TimerStop(true);
            Timer (38, level);
        } else if (level == 7) {
            TimerStop(true);
            Timer (36, level);
        } else if (level == 8) {
            TimerStop(true);
            Timer (34, level);
        } else if (level == 9) {
            TimerStop(true);
            Timer (32, level);
        } else if (level == 10) {
            TimerStop(true);
            Timer (1000, level);
        } else  {
            TimerStop(false);

        }
    }
}


