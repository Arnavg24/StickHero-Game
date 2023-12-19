package com.example;

public class Background implements MovementBG{

    private int bg;
    private float xcord;
    private float ycord;

    public float getXcord() {
        return xcord;
    }

    public void setXcord(float xcord) {
        this.xcord = xcord;
    }

    public float getYcord() {
        return ycord;
    }

    public void setYcord(float ycord) {
        this.ycord = ycord;
    }

    public int getBg() {
        return bg;
    }

    public void setBg(int bg) {
        this.bg = bg;
    }


    @Override
    public void forward() {

    }

    @Override
    public void movement() {

    }
}
