package com.example.gameuidemo;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;


public class MatchingGameScene {
    private BaseGridPane matchingGridPane;
    private Scene matchingScene;
    private Label matchingGameLabel2;
    private String[] randomColorNames;
    private Color[] randomColors;

    private List<Label> colorLabels;
    private TextField userAnswerField;

    private Text answerFeedback;
    private int roundsLeftToPlay;


    public MatchingGameScene(){
        matchingGridPane = new BaseGridPane();
        matchingScene = new Scene(matchingGridPane, 400, 350);
        colorLabels = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            colorLabels.add(new Label());
        }
        matchingGameLabel();
        newUserAnswerField();
        answerFeedback = new Text();
        roundsLeftToPlay = getRoundsFromDifficulty();
        playRound();

    }
    private void matchingGameLabel(){
        matchingGameLabel2 = new Label("Paaride sobitamise mäng");
        matchingGameLabel2.setFont(Font.font("Calibri", FontWeight.BOLD, 22));
        matchingGridPane.add(matchingGameLabel2, 0, 0, 2 , 1);
    }
    private void playRound(){
        if (roundsLeftToPlay > 0){
            displayRandomColorNames();
            roundsLeftToPlay--;
        }
    }

    private void displayRandomColorNames(){
        ColorsAndColorNames colorsAndColorNames = new ColorsAndColorNames();
        randomColors = colorsAndColorNames.getRandomColors();
        randomColorNames = colorsAndColorNames.getRandomColorNames();

        VBox vBox = new VBox(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().add(answerFeedback);

        for (int x = 0; x < colorLabels.size(); x++) {
            //colorLabels.set(x, new Label());
            colorLabels.get(x).setFont(Font.font(20));
            colorLabels.get(x).setText(randomColorNames[x]);
            colorLabels.get(x).setTextFill(randomColors[x]);
            vBox.getChildren().add(colorLabels.get(x));
        }


        userAnswerField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER){
                String userAnswer = userAnswerField.getText().toLowerCase();
                String correctColorName = randomColorNames[colorsAndColorNames.getCorrectAnswerIndex()].toLowerCase();
                if (userAnswer.equals(correctColorName)) {
                    answerFeedback.setText("Õige vastus");
                } else {
                    answerFeedback.setText("Vale vastus. õige indeks " + colorsAndColorNames.getCorrectAnswerIndex());
                }
                userAnswerField.clear();
                playRound();

            }
        });
        matchingGridPane.add(vBox, 1, 3);
    }


    private int getRoundsFromDifficulty(){
        String difficulty = DifficultyCurrentState.getDifficultyLevel();
        if (difficulty.equals("Lihtne")){
            return 4;
        } else if (difficulty.equals("Keskmine")) {
            return 8;
        }else return 12;
    }
    private void newUserAnswerField() {
        userAnswerField = new TextField();
        matchingGridPane.add(userAnswerField, 1, 5);

    }

    public Scene getMatchingGameScene(){
        return matchingScene;
    }
}
