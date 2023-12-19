package com.example;
import java.io.*;
import java.util.ArrayList;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.event.Event;
import javafx.event.Event;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class Gameplay extends Thread implements HeroMovement,GoToLandingPage{
    private MusicPlayer musicPlayer;
    private HeroFallMusic heroFallMusic;
    private ArrayList<Integer> scores = new ArrayList<>();
    private int score;
    private static int highScore = 0;
    private LandingPage landingPage;

    public static int getHighScore() {
        return highScore;
    }


    @FXML
    private Label Cherry ;

    @FXML
    private Label ScoreLabel;


    public Label getScoreLabel() {
        return ScoreLabel;
    }

    public void setScoreLabel(String s) {
        this.ScoreLabel.setText(s);
    }

    private static int cherryCount = 0;

    public static int getCherryCount() {
        return cherryCount;
    }

    public static void setCherryCount(int cherryCount) {
        Gameplay.cherryCount = cherryCount;
    }



    private boolean isFalling = false;

    @FXML
    private ImageView cherry;

    @FXML
    private ImageView hero;

    @FXML

    private Line stick;

    @FXML
    private Rectangle platform1;

    @FXML
    private Rectangle platform2;
    public boolean release = false;
    public boolean valid = true;
    public boolean movingForward = false;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    public Gameplay(){
//        Cherry.setText(String.valueOf(cherryCount));
    }

    public void scoreAddToList(){

    }
    @FXML
    private AnchorPane rootLayout;

    private StickClass stickClass;
    private HeroClass heroClass;

    @FXML
    public void initialize() {
        Cherry.setText(String.valueOf(getCherryCount()));
        ScoreLabel.setText(String.valueOf(this.getScore()));
        stickClass = StickClass.getStickInstance(stick);
        heroClass = HeroClass.getHeroInstance(hero, stick);
    }

    public void eventHandler(){
        musicPlayer = new MusicPlayer();


        hero.getScene().setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.SPACE){
                if(!release){
                    Thread thread = new Thread(musicPlayer);
                    thread.start();
                    stickClass.stickIncrease();
                }
            }
            else if((event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.S) && movingForward){
                hero.translateYProperty().set(hero.getFitHeight());
                hero.setScaleY(-1);
//                System.out.println("collision detection: " + collisionDetection());
            }
            else if((event.getCode() == KeyCode.UP || event.getCode() == KeyCode.W) && movingForward){
                hero.translateYProperty().set(0);
                hero.setScaleY(1);
//                System.out.println("collision detection: " + collisionDetection());
            }

            if(collisionDetection()){
                cherryCount++;
                Cherry.setText(String.valueOf(getCherryCount()));
                System.out.println("Cherry count: " + cherryCount);

            }
            if(collisionHeroPlatform()){
                heroDrop();
//                this.setScore(0);
                updateHighScore();
            }
//            collisionHeroPlatform();

        });
        hero.getScene().setOnKeyReleased(event -> {
            if(event.getCode() == KeyCode.SPACE){
                if(!release){
                    musicPlayer.stopStickIncreasingSound();
                    stickFall();
                    release = true;
                }
            }
//            System.out.println("collision detection: " + collisionDetection());
        });

//        collisionHeroPlatform();
//
    }

    public void setParentController(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("landing.fxml"));
        this.landingPage = loader.getController();
    }


    public void switchToLanding() {

    }
    public void stickIncrease() {
        stick.setVisible(true);
        int changeFactor = 10;
//        System.out.print(-stick.getEndY() + " " + stick.getLayoutY() + "\n");
        stick.setEndY(stick.getEndY() - changeFactor);
    }

    public void stickFall() {
        Timeline timeline = stickRotate(1000, 90);
        timeline.play();
        timeline.setOnFinished(event -> {
//            platformCollision=collisionHeroPlatform();
            forward();

        });
        System.out.println(stick.getRotate());
    }

    @Override
    public void forward() {

        movingForward = true;

        Translate translate = new Translate();
        translate.setX(0);
        translate.setY(0);
        hero.getTransforms().add(translate);

        Timeline timeline = new Timeline();
        KeyFrame kf = new KeyFrame(Duration.millis(0), new KeyValue(translate.xProperty(), hero.getLayoutX()));
        KeyFrame kf2 = new KeyFrame(Duration.millis(1000), new KeyValue(translate.xProperty(), -stick.getEndY() + 2));
        timeline.getKeyFrames().addAll(kf, kf2);
        timeline.play();
//        Timeline timeline = heroClass.heroForward(1000);
        timeline.play();
        timeline.setOnFinished(event -> {
            movingForward = false;
            if (!collisionHeroPlatform()) check();
        });
    }

    @Override
    public void heroFlipHandler() {
        hero.getScene().setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.S){
                hero.translateYProperty().set(hero.getFitHeight());
                hero.setScaleY(-1);
            }
            else if(event.getCode() == KeyCode.UP || event.getCode() == KeyCode.W){
                hero.translateYProperty().set(0);
                hero.setScaleY(1);
            }
        });
    }

    public Timeline stickRotate(double period, double angle){
        Rotate rotate = new Rotate();
        rotate.setPivotX(stick.getStartY());
        stick.getTransforms().add(rotate);
        Timeline timeline = new Timeline();
        KeyFrame kf = new KeyFrame(Duration.ZERO, new KeyValue(rotate.angleProperty(), stick.getRotate()));
        KeyFrame kf2 = new KeyFrame(Duration.millis(period), new KeyValue(rotate.angleProperty(), angle));
        timeline.getKeyFrames().addAll(kf, kf2);
        return timeline;
    }

    public void platformRandomizer(){
        int platform2Width = 20 + (int)(Math.random() * (61 - 20));
        int platform1End = (int) (platform2.getWidth() + platform1.getLayoutX() + 20);
        int platform2X = platform1End + (int)(Math.random() * (165 - platform1End));

        platform1.setWidth(platform2.getWidth());
        platform2.setLayoutX(platform2X);
        platform2.setWidth(platform2Width);
        stick.setLayoutX(platform1End);

        double gap = platform2.getLayoutX() - (platform1.getLayoutX() + platform1.getWidth());

        if (gap < 50) {
            cherry.setVisible(false);
        } else {
            double random = Math.random();
            if(random >0.4){
                double minX = platform1.getLayoutX() + platform1.getWidth();
                double maxX = platform2.getLayoutX() - cherry.getFitWidth();

                double cherryX = minX + Math.random() * (maxX - minX);

                // Set the x-coordinate of the cherry
                cherry.setLayoutX(cherryX);
                cherry.setVisible(true);
            }
            else{
                cherry.setVisible(false);
            }
        }



        Translate translate = new Translate();
        translate.setX(0);
        translate.setY(0);
        hero.getTransforms().add(translate);

        Timeline translateTimeline = new Timeline();
        KeyFrame kf = new KeyFrame(Duration.millis(0), new KeyValue(translate.xProperty(), hero.getLayoutX()));
        KeyFrame kf2 = new KeyFrame(Duration.millis(1), new KeyValue(translate.xProperty(), stick.getEndY()));
        translateTimeline.getKeyFrames().addAll(kf, kf2);
        translateTimeline.play();

        stick.setLayoutX(platform1.getLayoutX() + platform1.getWidth() - 1);
        stick.setVisible(false);
        stick.setEndY(0);

        Timeline rotateTimeline = stickRotate(1, -90);
        rotateTimeline.play();
    }

    public void check(){
        double stickDistance = stick.getLayoutX() - stick.getEndY();
        System.out.println(stick.getEndY());
        System.out.println("stick distance: " + stickDistance + "\n");
        System.out.println("platform2 coord: " + platform2.getLayoutX() + "\n");


        if(stickDistance >= platform2.getLayoutX() && stickDistance <= platform2.getLayoutX() + platform2.getWidth()){
            System.out.print("valid\n");
            release = false;
            valid = true;
            if(!isFalling) {
                platformRandomizer();
                int a= this.getScore();
                this.setScore(a+1);
                ScoreLabel.setText(String.valueOf(this.getScore()));
                System.out.println("Current score: " + score);
                updateHighScore();
                System.out.println("current high score: " + highScore);
                saveState(this.score, cherryCount, highScore);
            }
        }
        else{
            System.out.print("invalid\n");
            valid = false;
            heroDrop();
//            this.score=0;
//            ScoreLabel.setText("0");

        }
    }

    public void switchToScorecard() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("scorecard.fxml"));
        Stage stage = (Stage) hero.getScene().getWindow();
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        Scorecard scorecard = loader.getController();
        scorecard.eventHandler();
        scorecard.setScoreLabel(String.valueOf(this.getScore()));
        this.score = 0;
        scorecard.setHighscoreLabel(String.valueOf(highScore));
    }

   public void switchtoPause() throws IOException {
        PauseSaveState(this.score, cherryCount, highScore);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pause.fxml"));
        Stage stage = (Stage) hero.getScene().getWindow();
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        Scorecard scorecard = loader.getController();
        scorecard.setScoreLabel1(String.valueOf(this.getScore()));
        scorecard.setHighscoreLabel1(String.valueOf(highScore));
    }
    public void heroDrop(){
        PauseSaveState(0,cherryCount,highScore);
        isFalling=true;
        Rotate rotate = new Rotate();
        heroFallMusic = new HeroFallMusic();
        Thread thread = new Thread(heroFallMusic);
        thread.start();
        rotate.setPivotX(hero.getFitWidth()/2);
        rotate.setPivotY(hero.getFitHeight()/2);
        hero.getTransforms().add(rotate);

        Timeline timeline = new Timeline();
        KeyFrame kf = new KeyFrame(Duration.ZERO, new KeyValue(rotate.angleProperty(), hero.getRotate()));
        KeyFrame kf2 = new KeyFrame(Duration.millis(800), new KeyValue(rotate.angleProperty(), 90));
        timeline.getKeyFrames().addAll(kf, kf2);
        timeline.play();


        TranslateTransition t = new TranslateTransition(Duration.millis(1000), hero);
        t.setByY(200);
        t.play();
        t.setOnFinished(event -> {

            isFalling = false;
//            heroFallMusic.stopHeroFallMusic();
            try {
                if(!valid) {
                    switchToScorecard();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }



    public boolean collisionDetection(){
        Bounds heroBounds = hero.getBoundsInParent();
        Bounds cherryBounds = cherry.getBoundsInParent();

        if(heroBounds.intersects(cherryBounds) && hero.getScaleY() == -1){
            cherry.setVisible(false);
            return true;
        }

        return false;
    }

        public boolean collisionHeroPlatform(){
            Bounds heroBounds = hero.getBoundsInParent();
            Bounds platform1Bounds = platform1.getBoundsInParent();
            Bounds platform2Bounds = platform2.getBoundsInParent();

            if(heroBounds.intersects(platform2Bounds) && hero.getScaleY() == -1){
                heroDrop();
                valid=false;
                this.score=0;
                return true;
            }
            return false;
        }

    public void updateHighScore() {
        if (this.score > highScore) {
            highScore = this.score;
        }
    }

    public void saveState(int score, int cherryCount, int highScore) {
        GameState gameState = new GameState(score, cherryCount, highScore);
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("gameState.ser"))) {
            out.writeObject(gameState);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadState() {
//        boolean success = false;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("gameState.ser"))) {
            GameState gameState = (GameState) in.readObject();
            this.score = gameState.getScore();
            cherryCount = gameState.getCherryCount();
            highScore = gameState.getHighScore();
//            success = true;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
//        return success;
    }

    public void PauseSaveState(int score, int cherryCount, int highScore) {
        GameState gameState = new GameState(score, cherryCount, highScore);
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("pauseState.ser"))) {
            out.writeObject(gameState);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void PauseLoadState() {
//        boolean success = false;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("pauseState.ser"))) {
            GameState gameState = (GameState) in.readObject();
            this.score = gameState.getScore();
            cherryCount = gameState.getCherryCount();
            highScore = gameState.getHighScore();
//            success = true;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
//        return success;
    }

    public ArrayList<Integer> getScores() {
        return scores;
    }

    public void setScores(ArrayList<Integer> scores) {
        this.scores = scores;
    }

    public static void setHighScore(int highScore) {
        Gameplay.highScore = highScore;
    }

    public Label getCherry() {
        return Cherry;
    }

    public void setCherry(ImageView cherry) {
        this.cherry = cherry;
    }

    public ImageView getHero() {
        return hero;
    }

    public void setHero(ImageView hero) {
        this.hero = hero;
    }

    public Line getStick() {
        return stick;
    }

    public void setStick(Line stick) {
        this.stick = stick;
    }

    public Rectangle getPlatform1() {
        return platform1;
    }

    public void setPlatform1(Rectangle platform1) {
        this.platform1 = platform1;
    }

    public Rectangle getPlatform2() {
        return platform2;
    }

    public void setPlatform2(Rectangle platform2) {
        this.platform2 = platform2;
    }

    public boolean isRelease() {
        return release;
    }

    public void setRelease(boolean release) {
        this.release = release;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public boolean isMovingForward() {
        return movingForward;
    }

    public void setMovingForward(boolean movingForward) {
        this.movingForward = movingForward;
    }

    public AnchorPane getRootLayout() {
        return rootLayout;
    }

    public void setRootLayout(AnchorPane rootLayout) {
        this.rootLayout = rootLayout;
    }

    public void setCherry(Label cherry) {
        Cherry = cherry;
    }

    public void setScoreLabel(Label scoreLabel) {
        ScoreLabel = scoreLabel;
    }

    public boolean isFalling() {
        return isFalling;
    }

    public void setFalling(boolean falling) {
        isFalling = falling;
    }
}