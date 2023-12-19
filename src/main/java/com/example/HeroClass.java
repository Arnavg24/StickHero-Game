package com.example;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

import java.util.HashMap;

public class HeroClass implements Rotator{

    private static HashMap<String, HeroClass> heroMap = new HashMap<String, HeroClass>();
    private ImageView hero;
    private Line stick;
    public static HeroClass getHeroInstance(ImageView hero, Line stick) {
        String heroName = hero.getId();
        if (heroMap.containsKey(heroName)) {
            return heroMap.get(heroName);
        } else {
            HeroClass heroClass = new HeroClass(hero, stick);
            heroMap.put(heroName, heroClass);
            return heroClass;
        }
    }

    private HeroClass(ImageView hero, Line stick) {
        this.hero = hero;
        this.stick = stick;
    }
    @Override
    public Timeline rotate(double period, double angle) {
        Rotate rotate = new Rotate();
        rotate.setPivotX(hero.getFitWidth()/2);
        rotate.setPivotY(hero.getFitHeight()/2);
        hero.getTransforms().add(rotate);

        Timeline timeline = new Timeline();
        KeyFrame kf = new KeyFrame(Duration.ZERO, new KeyValue(rotate.angleProperty(), hero.getRotate()));
        KeyFrame kf2 = new KeyFrame(Duration.millis(period), new KeyValue(rotate.angleProperty(), angle));
        timeline.getKeyFrames().addAll(kf, kf2);
        return timeline;
    }

    public Timeline heroForward(double period) {
        Translate translate = new Translate();
        translate.setX(0);
        translate.setY(0);
        hero.getTransforms().add(translate);

        Timeline timeline = new Timeline();
        KeyFrame kf = new KeyFrame(Duration.millis(0), new KeyValue(translate.xProperty(), hero.getLayoutX()));
        KeyFrame kf2 = new KeyFrame(Duration.millis(period), new KeyValue(translate.xProperty(), -stick.getEndY() + 2));
        timeline.getKeyFrames().addAll(kf, kf2);
        return timeline;
    }
}
