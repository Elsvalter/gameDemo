package com.example.gameuidemo;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

public class BaseGridPane extends GridPane {
    // abiklass GridPane'ide kiireks tegemiseks
    public BaseGridPane(){
        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(25,25,25,25));
    }
}
