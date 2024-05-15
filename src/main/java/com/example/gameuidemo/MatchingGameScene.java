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
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
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
    private int points;


    public MatchingGameScene(String userName) throws IOException {
        matchingGridPane = new BaseGridPane();
        matchingScene = new Scene(matchingGridPane, 600, 650);
        colorLabels = new ArrayList<>(); // loob Labelite Listi
        for (int i = 0; i < 4; i++) {
            colorLabels.add(new Label()); // Loob 4 uut Labelit ja lisab Listi
        }
        points = 0;
        matchingGameLabel();
        newUserAnswerField();
        answerFeedback = new Text();
        roundsLeftToPlay = getRoundsFromDifficulty();
        playRound(userName);

    }
    private void matchingGameLabel(){
        matchingGameLabel2 = new Label("Paaride sobitamise mäng");
        matchingGameLabel2.setFont(Font.font("Calibri", FontWeight.BOLD, 22));
        matchingGridPane.add(matchingGameLabel2, 0, 0, 2 , 1);
    }

    // kui on veel rounde mängida, siis kuvab uued värvid
    // kui roundid otsas siis viib EndScene'i
    private void playRound(String userName) throws IOException {
        if (roundsLeftToPlay > 0){
            displayRandomColorNames(userName);
            roundsLeftToPlay--;
        } else {
            int multiplier = 1;
            switch (DifficultyCurrentState.getDifficultyLevel()) {
                case "Keskmine":
                    multiplier *= 2;
                    break;
                case "Raske":
                    multiplier *= 3;
                    break;
            }
            EndScene endScene = new EndScene("Katsete arv on otsas!", points, multiplier, userName, true);
            Window window = matchingScene.getWindow();
            if (window instanceof Stage){
                Stage stage = (Stage) window;
                stage.setScene(endScene.getEndScene());
            }
        }
    }

    private void displayRandomColorNames(String userName){
        // tekitab juhuslike värvide ja sõnade massiivid
        ColorsAndColorNames colorsAndColorNames = new ColorsAndColorNames();
        randomColors = colorsAndColorNames.getRandomColors();
        randomColorNames = colorsAndColorNames.getRandomColorNames();

        VBox vBox = new VBox(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().add(answerFeedback);

        // Set'ib Labelite tekstid ja värvid vastavalt juhuslikele värvidele (randomColors)
        // ja nimedele (randomColorNames)
        for (int x = 0; x < colorLabels.size(); x++) {
            colorLabels.get(x).setFont(Font.font(20));
            colorLabels.get(x).setText(randomColorNames[x]);
            colorLabels.get(x).setTextFill(randomColors[x]);
            vBox.getChildren().add(colorLabels.get(x));
        }

// kui kasutaja kirjutab vastuse ja vajutab enter --> kontrollib kas vastus õige + lisab punkti
        userAnswerField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER){
                String userAnswer = userAnswerField.getText().toLowerCase();
                String correctColorName = randomColorNames[colorsAndColorNames.getCorrectAnswerIndex()].toLowerCase();
                if (userAnswer.equals(correctColorName)) {
                    points += 1;
                    answerFeedback.setText("Õige vastus! Punkte kokku: " + points);
                } else {
                    answerFeedback.setText("Vale vastus. Õige vastus: " + randomColorNames[colorsAndColorNames.getCorrectAnswerIndex()]);
                }

                userAnswerField.clear(); // kustutab kasutaja eelmise sisestuse
                try {
                    playRound(userName); // järgmine round
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });
        matchingGridPane.add(vBox, 1, 3);
    }

    // tagastab mängu roundide arvu vastavalt valitud raskustasemele
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
