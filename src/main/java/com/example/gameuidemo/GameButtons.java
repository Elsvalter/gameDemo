package com.example.gameuidemo;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class GameButtons extends Button {

    // abiklass nuppude kiireks tegemiseks
    public GameButtons(String text, int width, int height, Color textColor){
        super(text);
        this.setMinWidth(width);
        this.setMinHeight(height);
        this.setTextFill(textColor);
    }
}
