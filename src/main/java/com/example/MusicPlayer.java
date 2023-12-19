package com.example;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class MusicPlayer implements Runnable{
    private MediaPlayer stickIncreasingSoundPlayer;

    public MusicPlayer() {
        String stickIncreasingSoundFilePath = "src/main/resources/com/example/sounds/Stick_Increase.mp3";
        Media stickIncreasingSoundMedia = new Media(new File(stickIncreasingSoundFilePath).toURI().toString());

        this.stickIncreasingSoundPlayer = new MediaPlayer(stickIncreasingSoundMedia);
        this.stickIncreasingSoundPlayer.setVolume(0.15);
    }

    public void playStickIncreasingSound() {
        stickIncreasingSoundPlayer.play();
    }

    public void stopStickIncreasingSound() {
        stickIncreasingSoundPlayer.stop();
    }

    @Override
    public void run() {
        String stickIncreasingSoundFilePath = "src/main/resources/com/example/sounds/Stick_Increase.mp3";
        Media stickIncreasingSoundMedia = new Media(new File(stickIncreasingSoundFilePath).toURI().toString());

        this.stickIncreasingSoundPlayer = new MediaPlayer(stickIncreasingSoundMedia);
        this.stickIncreasingSoundPlayer.setVolume(0.15);
        stickIncreasingSoundPlayer.play();
    }
}