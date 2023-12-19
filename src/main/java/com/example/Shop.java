package com.example;
import java.io.IOException;

public class Shop extends Page implements GoToLandingPage{

    public void options(){

    }

    public void buy(int numberOfCherries){

    }


    public Shop() {

    }

    public void switchToLanding() throws IOException {
        changeScene("LandingPage");
    }

}
