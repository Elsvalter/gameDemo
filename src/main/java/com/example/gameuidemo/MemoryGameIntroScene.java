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

public class MemoryGameIntroScene {
    private BaseGridPane memoryIntroPane;
    private Scene memoryIntroScene;

    private Text gameIntroduction;

    public Label matchingGameLabel;
    private GameButtons startGameButton;


    public MemoryGameIntroScene(String userName) {
        memoryIntroPane = new BaseGridPane();
        memoryIntroScene = new Scene(memoryIntroPane, 600, 650);
        matchingGameLabel();
        newGameIntroduction();
        newStartGameButton(userName);
    }
    private void matchingGameLabel(){
        matchingGameLabel = new Label("Mälumäng");
        matchingGameLabel.setFont(Font.font("Calibri", FontWeight.BOLD, 22));
        memoryIntroPane.add(matchingGameLabel, 0, 0, 2 , 1);
    }

    private void newGameIntroduction(){
        String difficulty = DifficultyCurrentState.getDifficultyLevel(); // näitab ära useri valitud raskustaseme
        Text difficultyText = new Text("Valitud raskustase: "+difficulty);
        difficultyText.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));

        gameIntroduction = new Text("Mängu alustades kuvatakse ekraanile värvid, mida peab\n" +
                "õiges järjekorras meelde jätma.\n" +
                "Värvide järjekord on oluline, sest kui eksida värvide\n" +
                "sisestamisel järjekorraga, siis ei saa vales järjekorras\n" +
                "olevate värvide eest punkte.");
        gameIntroduction.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));

        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(difficultyText, gameIntroduction);
        memoryIntroPane.add(vBox, 1, 4);

    }
    private void newStartGameButton(String userName) throws RuntimeException {
        startGameButton = new GameButtons("Alusta mängu!", 130, 55, Color.BLACK);
        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().add(startGameButton);
        memoryIntroPane.add(hBox, 1, 6);

        startGameButton.setOnAction(event -> {
            MemoryGameScene memoryGameScene = null;
            try {
                memoryGameScene = new MemoryGameScene(userName);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Window window = memoryIntroScene.getWindow();
            if (window instanceof Stage){
                Stage stage = (Stage) window;
                stage.setScene(memoryGameScene.getMemoryScene());
            }
        });
    }
    public Scene getMemoryIntroScene() {
        return memoryIntroScene;
    }
}
