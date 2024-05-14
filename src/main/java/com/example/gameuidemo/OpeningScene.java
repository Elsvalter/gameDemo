package com.example.gameuidemo;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

public class OpeningScene {
    private BaseGridPane grid;
    private Scene scene;
    private Label welcomeLabel;
    private GameButtons memoryGameButton;
    private GameButtons matchingGameButton;
    public ToggleGroup difficultyToggleGroup; // raskustaseme radiobuttonid
    private TextField userName;
    private Text userText; // tekst, mis ilmub, kui userName sisestatud
    public DifficultyCurrentState difficulty; // valitud raskustase


    public OpeningScene() {
        grid = new BaseGridPane();
        scene = new Scene(grid, 400, 350);
        difficulty = new DifficultyCurrentState(); // loob uue raskustaseme isendi
        welcomeLabel();
        userNameTextField();
        newDifficultyToggleGroup();
        newMatchingGameButton();
        newMemoryGameButton();

    }

    // teeb sobitamise mängu nupu ja lisab gridi + vajutamisel vahetab stage'i
    private void newMatchingGameButton() {
        matchingGameButton = new GameButtons("Paaride leidmise mäng", 130, 55, Color.BLACK);
        HBox hBoxForButton = new HBox(10);
        hBoxForButton.setAlignment(Pos.CENTER);
        hBoxForButton.getChildren().add(matchingGameButton);
        grid.add(hBoxForButton, 1, 7);

        matchingGameButton.setOnAction(event -> {
            if (DifficultyCurrentState.getDifficultyLevel() != null && !userName.getText().isEmpty()) {
                MatchingGameIntroScene matchingGameIntroScene = new MatchingGameIntroScene(userName.getText());
                Window window = scene.getWindow();
                if (window instanceof Stage) {
                    Stage stage = (Stage) window;
                    stage.setScene(matchingGameIntroScene.getMatchingIntroScene());
                }
            }
        });
    }

    // teeb mälumängu nupu ja lisab gridi + vajutamisel vahetab stage'i
    private void newMemoryGameButton() {
        memoryGameButton = new GameButtons("Mälumäng", 130, 55, Color.BLACK);
        HBox hBoxForButton2 = new HBox(10);
        hBoxForButton2.setAlignment(Pos.CENTER);
        hBoxForButton2.getChildren().add(memoryGameButton);
        grid.add(hBoxForButton2, 1, 8);

        memoryGameButton.setOnAction(event -> {
            if (DifficultyCurrentState.getDifficultyLevel() != null && !userName.getText().isEmpty()) {
                MemoryGameIntroScene memoryGameIntroScene = null;
                memoryGameIntroScene = new MemoryGameIntroScene(userName.getText());
                Window window = scene.getWindow();
                if (window instanceof Stage) {
                    Stage stage = (Stage) window;
                    stage.setScene(memoryGameIntroScene.getMemoryIntroScene());
                }
            }
        });
    }

    private void welcomeLabel() {
        welcomeLabel = new Label("Tere tulemast mängu!");
        welcomeLabel.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
        grid.add(welcomeLabel, 0, 0, 2, 1);
    }

    // username'i sisestamine ja kui vajutab enter siis tervitustekst
    private void userNameTextField() {
        userName = new TextField();
        grid.add(userName, 1, 1);
        userName.setPromptText("Sisesta oma kasutajanimi");
        userName.setFocusTraversable(false);

        userName.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String enteredUserName = userName.getText();
                userText = new Text("Tere kasutaja: " + enteredUserName + "!");
                grid.add(userText, 1, 2);
            }
        });
    }

    // teeb ToggleGroupi ja 3 radiobuttonit + lisab gridi
    // Listener valitud raskustasemele + DifficultyCurrentState väärtustamine
    private void newDifficultyToggleGroup() {
        Label difficultyLabel = new Label("Vali mängu raskustase: ");
        difficultyToggleGroup = new ToggleGroup();
        RadioButton easy = new RadioButton("Lihtne");
        easy.setToggleGroup(difficultyToggleGroup);
        RadioButton medium = new RadioButton("Keskmine");
        medium.setToggleGroup(difficultyToggleGroup);
        RadioButton hard = new RadioButton("Raske");
        hard.setToggleGroup(difficultyToggleGroup);
        grid.add(difficultyLabel, 1, 3);
        grid.add(easy, 1, 4);
        grid.add(medium, 1, 5);
        grid.add(hard, 1, 6);

        difficultyToggleGroup.selectedToggleProperty().addListener(
                new ChangeListener<Toggle>() {
                    @Override
                    public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle1, Toggle toggle2) {
                        RadioButton selectedRadioButton = (RadioButton) difficultyToggleGroup.getSelectedToggle();
                        if (selectedRadioButton != null) {
                            DifficultyCurrentState.setDifficultyLevel(selectedRadioButton.getText());
                        }
                    }
                }
        );
    }

    public Scene getOpeningScene() {
        return scene;
    }
}
