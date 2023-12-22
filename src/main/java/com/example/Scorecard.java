package com.example;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

public class Scorecard extends Page implements GoToLandingPage{
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label ScoreLabel;
    @FXML
    private Label BestScoreLabel;

    @FXML
    private Label ScoreLabel1;

    @FXML
    private Label BestScoreLabel1;

    @FXML
    private Button ReviveButton;

    private String score;
    private String bestScore;

    private int cherryReq = 5;

    //    @FXML
//    Label nameLabel1;
//
//    public void displayGameOver(){
//        nameLabel1.setText("Game Over");
//    }
    public void switchToLanding(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("landing.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void retry(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gameplay.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        root = loader.load();
        scene = new Scene(root);


        Gameplay gameplay = loader.getController();
        stage.setScene(scene);
        stage.show();
        gameplay.eventHandler();
    }

    public void play(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gameplay.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        root = loader.load();
        scene = new Scene(root);

        Gameplay gameplay = loader.getController(); // Get the Gameplay instance from the FXMLLoader
        stage.setScene(scene);
        stage.show();
//            gameplay.saveState();
        Platform.runLater(() -> {
            gameplay.loadState();
            gameplay.setScoreLabel(String.valueOf(gameplay.getScore()));
            gameplay.eventHandler();
        });

    }



    public void revive(ActionEvent event) throws IOException {
        int cherryCount = Gameplay.getCherryCount();
        if (cherryCount >= cherryReq) {
            int a = cherryCount - cherryReq;
            Gameplay.setCherryCount(cherryCount - cherryReq);
            play(event);
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.getDialogPane().setPrefWidth(250);
            alert.setTitle("Sorry");
            alert.setHeaderText(null);
            alert.setContentText("Not enough cherries");
            alert.showAndWait();
        }
    }

    public void eventHandler(){
        AtomicBoolean ADown = new AtomicBoolean(false);
        AtomicBoolean KDown = new AtomicBoolean(false);

        ScoreLabel.getScene().setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.A) {
                    ADown.set(true);
            }
            if (e.getCode() == KeyCode.K) {
                KDown.set(true);
            }

            if (ADown.get() && KDown.get()) {
                ReviveButton.setText("Revive for 2 cherries");
                cherryReq = 2;
            }
        });

        ScoreLabel.getScene().setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.A) {
                ADown.set(false);
            }
            if (e.getCode() == KeyCode.K) {
                KDown.set(false);
            }
            if (!ADown.get() || !KDown.get()) {
                ReviveButton.setText("Revive for 5 cherries");
                cherryReq = 5;
            }
        });
    }

//    public void initialize(){
//        eventHandler();
//    }

    public void setScoreLabel(String score){
        ScoreLabel.setText(score);
    }

    public void setHighscoreLabel(String bestScore){
        BestScoreLabel.setText(bestScore);
    }

    public void leaderboard(){

    }

    public void addToAllScoresList(){

    }

    public void sortScoresList(){

    }

    @Override
    public void switchToLanding() throws IOException {

    }

    public void setScoreLabel1(String s) {
        ScoreLabel1.setText(s);
    }

    public void setHighscoreLabel1(String s) {
        BestScoreLabel1.setText(s);
    }
}