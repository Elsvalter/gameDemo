package com.example.gameuidemo;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class MemoryGameScene {
    private BaseGridPane memoryGridPane;
    private Scene memoryScene;
    private TextField memoryGameUserAnswerField;
    private Label memoryGameLabel;

    public MemoryGameScene() {
        memoryGridPane = new BaseGridPane();
        memoryScene = new Scene(memoryGridPane, 400, 350);
        matchingGameLabel();
        newMemoryGameUserAnswerField();
        textBoxWithDifficulty();

    }

    private void newMemoryGameUserAnswerField() {
        memoryGameUserAnswerField = new TextField();
        memoryGridPane.add(memoryGameUserAnswerField, 1, 1);

    }
    private void matchingGameLabel(){
        memoryGameLabel = new Label("Mälumäng");
        memoryGameLabel.setFont(Font.font("Calibri", FontWeight.BOLD, 22));
        memoryGridPane.add(memoryGameLabel, 0, 0, 2, 1);
    }

    // näitab ära useri valitud raskustaseme
    private void textBoxWithDifficulty(){
        String difficulty = DifficultyCurrentState.getDifficultyLevel();
        Text difficultyText = new Text("Valitud raskustase: "+difficulty);
        difficultyText.setFont(Font.font("Calibri", FontWeight.NORMAL, 15));
        memoryGridPane.add(difficultyText, 1, 3);
    }
    public Scene getMemoryScene() {
        return memoryScene;
    }


}
