package com.example;

import javafx.fxml.FXMLLoader;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GameplayTest {

    @Test
    public void testSaveGame() {

        Gameplay gameplay = new Gameplay();
        int expectedScore = 10;
        int expectedCherryCount = 5;
        int expectedHighScore = 20;

        gameplay.setScore(expectedScore);
        Gameplay.setCherryCount(expectedCherryCount);
        Gameplay.setHighScore(expectedHighScore);


        gameplay.saveState(expectedScore, expectedCherryCount, expectedHighScore);

        Gameplay loadedGameplay = new Gameplay();
        loadedGameplay.loadState();

        assertEquals(expectedScore, loadedGameplay.getScore());
        assertEquals(expectedCherryCount, Gameplay.getCherryCount());
        assertEquals(expectedHighScore, Gameplay.getHighScore());

    }

    @Test
    public void testSaveGame2() {
        // Arrange
        Gameplay gameplay = new Gameplay();
        int expectedScore = 100;
        int expectedCherryCount = 50;
        int expectedHighScore = 200;

        gameplay.setScore(expectedScore);
        Gameplay.setCherryCount(expectedCherryCount);
        Gameplay.setHighScore(expectedHighScore);

//        gameplay.saveState(expectedScore, expectedCherryCount, expectedHighScore);

        Gameplay loadedGameplay = new Gameplay();
        loadedGameplay.loadState();

        assertNotEquals(expectedScore, loadedGameplay.getScore());
        assertNotEquals(expectedCherryCount, Gameplay.getCherryCount());
        assertNotEquals(expectedHighScore, Gameplay.getHighScore());

    }


    @Test
    public void testCollisionDetection() {
        // Create a Gameplay object
        Gameplay gameplay = new Gameplay();

        // Set the hero and the cherry
        ImageView hero = new ImageView();
        gameplay.setHero(hero);
        ImageView cherry = new ImageView();
        gameplay.setCherry(cherry);
        BoundingBox heroBounds = new BoundingBox(0, 0, 10, 10);
        BoundingBox cherryBounds = new BoundingBox(20, 20, 10, 10);

        // Check if collisionDetection() returns false
        Assertions.assertFalse(gameplay.collisionDetection());
    }




}

//    @Test
//    public void collisionDetectionTest(){
//        Gameplay gameplay = new Gameplay();
//
//        ImageView hero = new ImageView();
//        gameplay.setHero(hero);
//
//        ImageView cherry = new ImageView();
//        gameplay.setCherry(cherry);
//        BoundingBox heroBounds = new BoundingBox(0, 0, 10, 10);
//        BoundingBox cherryBounds = new BoundingBox(0, 0, 10, 10);
//        hero = gameplay.getHero();
//        hero.setScaleY(-1);
//        assertTrue(gameplay.collisionDetection(heroBounds,cherryBounds));
//    }

