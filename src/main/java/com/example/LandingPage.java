package com.example;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.Node;

public class LandingPage extends Page implements GoToLandingPage{

    private boolean sound;

    private int bestScore;

    private ArrayList<Integer> scores;

    public ArrayList<Integer> getScores() {
        return scores;
    }

    public void setScores(ArrayList<Integer> scoress) {
        scores = scoress;
    }

    public boolean isSound() {
        return sound;
    }

    public void setSound(boolean sound) {
        this.sound = sound;
    }

    public int getBestScore() {
        return bestScore;
    }

    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
    }

    public LandingPage(){
        this.bestScore=0;
        this.scores= new ArrayList<>();
        this.sound= true;   //check
    }

    public void soundOnOff(){

    }

    public void switchToGameplay(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gameplay.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = loader.load();
        Scene scene = new Scene(root);

        Gameplay gameplay = loader.getController();
        stage.setScene(scene);
        stage.show();
        gameplay.eventHandler();
    }

  public void resumeAfterPause(ActionEvent event) throws IOException {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("gameplay.fxml"));
      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      Parent root = loader.load();
      Scene scene = new Scene(root);

      Gameplay gameplay = loader.getController();
      stage.setScene(scene);
      stage.show();
      gameplay.PauseLoadState();
      gameplay.setScoreLabel(String.valueOf(gameplay.getScore()));

      if(gameplay.getScore() > 0) {
          gameplay.eventHandler();
      }
      else{

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.getDialogPane().setPrefWidth(250);
            alert.setHeaderText(null);
            alert.setContentText("No saved game found");
            alert.showAndWait();
            gameplay.eventHandler();

      }


  }

    @Override
    public void switchToLanding() throws IOException {

    }
}
