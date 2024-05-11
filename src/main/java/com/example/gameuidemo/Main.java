package com.example.gameuidemo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        OpeningScene openingScene = new OpeningScene();
        Scene scene = openingScene.getOpeningScene();

        stage.setTitle("Avaekraan");
        stage.setScene(scene);
        stage.show();
    }
}
