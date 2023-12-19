package com.example;


import java.io.Serializable;

public class PauseState implements Serializable {
    private int score;
    private int cherryCount;
    private int highScore;

    public PauseState(int score, int cherryCount, int highScore) {
        this.score = score;
        this.cherryCount = cherryCount;
        this.highScore = highScore;
    }

    public int getScore() {
        return score;
    }

    public int getCherryCount() {
        return cherryCount;
    }

    public int getHighScore() {
        return highScore;
    }
}