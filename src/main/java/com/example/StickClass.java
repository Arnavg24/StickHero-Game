package com.example;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class StickClass implements Rotator{
    private final Line stick;
    public static StickClass getStickInstance(Line stick) {
        return new StickClass(stick);
    }

    private StickClass(Line stick) {
        this.stick = stick;
    }

    public void printStick() {
        System.out.println(stick.getBoundsInParent());
    }

    public void stickIncrease() {
        stick.setVisible(true);
        int changeFactor = 10;
        stick.setEndY(stick.getEndY() - changeFactor);
    }

    @Override
    public Timeline rotate(double period, double angle) {
        Rotate rotate = new Rotate();
        rotate.setPivotX(stick.getStartY());
        stick.getTransforms().add(rotate);
        Timeline timeline = new Timeline();
        KeyFrame kf = new KeyFrame(Duration.ZERO, new KeyValue(rotate.angleProperty(), stick.getRotate()));
        KeyFrame kf2 = new KeyFrame(Duration.millis(period), new KeyValue(rotate.angleProperty(), angle));
        timeline.getKeyFrames().addAll(kf, kf2);
        return timeline;
    }
}
