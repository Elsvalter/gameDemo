package com.example.gameuidemo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

public class MemoryGameScene {
    private BaseGridPane memoryGridPane;
    private Scene memoryScene;
    private TextField memoryGameUserAnswerField;
    private Label memoryGameLabel;
    private GameButtons startButton;

    public MemoryGameScene() {
        memoryGridPane = new BaseGridPane();
        memoryScene = new Scene(memoryGridPane, 400, 350);
        matchingGameLabel();
        newMemoryGameUserAnswerField();
        textBoxWithDifficulty();
        startGameButton();

    }

    private void newMemoryGameUserAnswerField() {
        memoryGameUserAnswerField = new TextField();
        memoryGridPane.add(memoryGameUserAnswerField, 0, 1);

    }
    private void matchingGameLabel(){
        memoryGameLabel = new Label("Mälumäng");
        memoryGameLabel.setFont(Font.font("Calibri", FontWeight.BOLD, 22));
        memoryGridPane.add(memoryGameLabel, 0, 0);
    }

    // näitab ära useri valitud raskustaseme
    private void textBoxWithDifficulty(){
        String difficulty = DifficultyCurrentState.getDifficultyLevel();
        Text difficultyText = new Text("Valitud raskustase: "+difficulty);
        difficultyText.setFont(Font.font("Calibri", FontWeight.NORMAL, 15));
        memoryGridPane.add(difficultyText, 0, 3);
    }
    public Scene getMemoryScene() {
        return memoryScene;
    }

    public void startGameButton() {
        startButton = new GameButtons("Näita värve", 130, 55, Color.BLACK);
        memoryGridPane.add(startButton, 0, 4);

        // raskustaseme põhjal ajaperioodi arvutamine
        String difficulty = DifficultyCurrentState.getDifficultyLevel();
        int timePeriod = -1; // hoiab endas ajaperioodi, kui kaua näidatakse kasutajale värve
        switch (difficulty) {
            case "Lihtne":
                timePeriod = 30;
                break;
            case "Keskmine":
                timePeriod = 20;
                break;
            case "Raske":
                timePeriod = 10;
                break;
            default: // kui raskusastmeks on null
                System.exit(1); // TODO: null raskusastet ei tohiks saada sisestada
                break;

        }

        // näitab korraks värve - vastavalt valitud raskusastmele
        // nurka tekib taimer, mis näitab järelejäänud aega
        int finalTimePeriod = timePeriod;
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // tekita taimer
                System.out.println("taimer"); // TODO: eemaldada - timer teha

                showColors(finalTimePeriod); // värvide näitamise ajaperiood sõltub raskustasemest
            }
        });

    }

    public void showColors(int timePeriod) {
        FlowPane popUpPane = new FlowPane();
        Scene popUpScene = new Scene(popUpPane, 400, 350);

        Window window = memoryScene.getWindow();
        if (window instanceof Stage){
            Stage stage = (Stage) window;
            stage.setScene(popUpScene);
        }
        System.out.println(timePeriod);
    }


}
