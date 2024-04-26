package com.example.gameuidemo;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class MatchingGameScene {
    private BaseGridPane gameGridPane;
    private Scene gameScene;
    private TextField matchingGameUserAnswerField;
    private Label matchingGameLabel;


    public MatchingGameScene() {
        gameGridPane = new BaseGridPane();
        gameScene = new Scene(gameGridPane, 400, 350);
        matchingGameLabel();
        newMatchingGameUserAnswerField();
        textBoxWithDifficulty();


    }
    private void matchingGameLabel(){
        matchingGameLabel = new Label("Paaride sobitamise mäng");
        matchingGameLabel.setFont(Font.font("Calibri", FontWeight.BOLD, 22));
        gameGridPane.add(matchingGameLabel, 0, 0, 2 , 1);
    }

    // näitab ära useri valitud raskustaseme
    private void textBoxWithDifficulty(){
        String difficulty = DifficultyCurrentState.getDifficultyLevel();
        Text difficultyText = new Text("Valitud raskustase: "+difficulty);
        difficultyText.setFont(Font.font("Calibri", FontWeight.NORMAL, 15));
        gameGridPane.add(difficultyText, 1, 3);
    }

    private void newMatchingGameUserAnswerField() {
        matchingGameUserAnswerField = new TextField();
        gameGridPane.add(matchingGameUserAnswerField, 1, 1);

    }

    public Scene getGameScene() {
        return gameScene;
    }
}
