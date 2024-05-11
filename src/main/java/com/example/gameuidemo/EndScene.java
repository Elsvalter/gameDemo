package com.example.gameuidemo;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class EndScene {

    // ma lihtsalt katsetasin mingit basic lõpu stseeni
    // et vaadata seda üleminekut mängust siia
    // selle sisu täitsa lamp hetkel, et saad sellega teha
    // mida tahad
    private BaseGridPane endGrid;
    private Scene endScene;
    private Label label;

    public EndScene() {
        endGrid = new BaseGridPane();
        endScene = new Scene(endGrid, 400, 350);
    }


    public Scene getEndScene() {
        return endScene;
    }
}
