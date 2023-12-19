package com.example;
import java.io.IOException;
import java.util.Objects;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public abstract class Page {


    @FXML
    public void changeScene(String fxml)  {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml + ".fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
