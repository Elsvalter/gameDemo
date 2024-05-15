package com.example.gameuidemo;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class MatchingGameIntroScene {
    private BaseGridPane matchingIntroGridPane;
    private Scene matchingIntroScene;

    private Text gameIntroduction;

    public Label matchingGameLabel;
    private GameButtons startGameButton;


    public MatchingGameIntroScene(String userName) {
        matchingIntroGridPane = new BaseGridPane();
        matchingIntroScene = new Scene(matchingIntroGridPane, 600, 650);
        matchingGameLabel();
        newGameIntroduction();
        newStartGameButton(userName);
    }
    private void matchingGameLabel(){
        matchingGameLabel = new Label("Paaride sobitamise mäng");
        matchingGameLabel.setFont(Font.font("Calibri", FontWeight.BOLD, 22));
        matchingIntroGridPane.add(matchingGameLabel, 0, 0, 2 , 1);
    }

    private void newGameIntroduction(){
        String difficulty = DifficultyCurrentState.getDifficultyLevel(); // näitab ära useri valitud raskustaseme
        Text difficultyText = new Text("Valitud raskustase: "+difficulty);
        difficultyText.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));

        gameIntroduction = new Text("Vali esitatud sõnade seast see,\nkus sõna ja värv sobivad kokku!\n\nNäiteks nii: ");
        gameIntroduction.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
        Label correctExample = new Label("Roosa"); // näide õigest vastusest
        correctExample.setTextFill(Color.PINK);
        correctExample.setFont(Font.font("Calibri", FontWeight.BOLD, 20));


        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(difficultyText, gameIntroduction, correctExample);
        matchingIntroGridPane.add(vBox, 1, 4);

    }
    private void newStartGameButton(String userName){
        // nupp mängu alustamiseks
        startGameButton = new GameButtons("Alusta mängu!", 130, 55, Color.BLACK);
        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().add(startGameButton);
        matchingIntroGridPane.add(hBox, 1, 6);

        // nupule vajutamisel püüab avada MatchingGameScene'i, kus toimub mäng
        startGameButton.setOnAction(event -> {
            MatchingGameScene matchingGameScene = null;
            try {
                matchingGameScene = new MatchingGameScene(userName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Window window = matchingIntroScene.getWindow();
            if (window instanceof Stage){
                Stage stage = (Stage) window;
                stage.setScene(matchingGameScene.getMatchingGameScene());
            }
        });
    }
    public Scene getMatchingIntroScene() {
        return matchingIntroScene;
    }
}
