package com.example.gameuidemo;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class EndScene {
    private BaseGridPane endGrid;
    private Scene endScene;
    private Label label;

    public EndScene() {
        endGrid = new BaseGridPane();
        endScene = new Scene(endGrid, 400, 350);
        timerLabel();
    }

    private void timerLabel(){
        label = new Label("Aeg: " + GameTimer.getTimePlayed());
        label.setFont(Font.font("Calibri", FontWeight.BOLD, 22));
        endGrid.add(label, 0, 0, 2 , 1);
    }

    public Scene getEndScene() {
        return endScene;
    }
}
