package com.example.gameuidemo;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Window;


public class EndScene {

    // ma lihtsalt katsetasin mingit basic lõpu stseeni
    // et vaadata seda üleminekut mängust siia
    // selle sisu täitsa lamp hetkel, et saad sellega teha
    // mida tahad
    private BaseGridPane endGrid;
    private Scene endScene;

    public EndScene() {
        endGrid = new BaseGridPane();
        endScene = new Scene(endGrid, 400, 350);




        Label gameOverLabel = new Label("Mäng on läbi! Teenisid ühest voorust vähem kui 3 punkti.");
        gameOverLabel.setFont(Font.font("Calibri", FontWeight.BOLD, 22));
        endGrid.add(gameOverLabel, 0, 0);
        Button buttonToHomePage = new GameButtons("Tagasi avalehele", 350, 55, Color.BLACK);
        endGrid.add(buttonToHomePage, 0, 1);


        buttonToHomePage.setOnMouseClicked(mouseEvent -> {
            OpeningScene openingScene = new OpeningScene();
            Scene scene = openingScene.getOpeningScene();
            Window window = endScene.getWindow();
            if (window instanceof Stage) {
                Stage stage = (Stage) window;
                stage.setScene(scene);
            }

        });
    }


    public Scene getEndScene() {
        return endScene;
    }
}
