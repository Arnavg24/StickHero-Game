package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;
//assumptions
// cherry is added only when player is below while passing through the cherry
// serialize and deserialize the game state
// if a person collects a cherry it will be counted even if he dies in that process
// comparator to compare the scores






/**
 * JavaFX App
 */
public class App extends Application {
    public static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Stick Hero");

        scene = new Scene(loadFXML("landing"));
        stage.setScene(scene);
        stage.show(); // this should always be at last and this is what runs the ui
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static Object getController(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.getController();
    }

    public static void main(String[] args) {
        launch(args);
    }
}