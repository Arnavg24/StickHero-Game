package com.example;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class HeroFallMusic implements Runnable{
    private MediaPlayer heroFallMusic;

    public HeroFallMusic() {
        String heroFallSoundFilePath = "src/main/resources/com/example/sounds/Hero_Fall.mp3";
        Media heroFallSoundMedia = new Media(new File(heroFallSoundFilePath).toURI().toString());

        this.heroFallMusic = new MediaPlayer(heroFallSoundMedia);
        this.heroFallMusic.setVolume(0.15);
    }

    public void stopHeroFallMusic() {
        heroFallMusic.stop();
    }

    @Override
    public void run() {
        String heroFallSoundFilePath = "src/main/resources/com/example/sounds/Hero_Fall.mp3";
        Media heroFallSoundMedia = new Media(new File(heroFallSoundFilePath).toURI().toString());

        this.heroFallMusic = new MediaPlayer(heroFallSoundMedia);
        this.heroFallMusic.setVolume(0.15);
        heroFallMusic.play();

    }
}
