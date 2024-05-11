package com.example.gameuidemo;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MemoryGameScene {
    private BaseGridPane memoryGridPane;
    private Scene memoryScene;
    private TextField memoryGameUserAnswerField;
    private Label memoryGameLabel;
    private GameButtons startButton;
    private Scene popUpScene;
    private ColorsAndColorNames colorsAndNames;
    private Color[] currentColors;

    public MemoryGameScene() throws InterruptedException {
        memoryGridPane = new BaseGridPane();
        memoryScene = new Scene(memoryGridPane, 600, 600, Color.SILVER);
        colorsAndNames = new ColorsAndColorNames();
        currentColors = new Color[6];
        matchingGameLabel();
        newMemoryGameUserAnswerField();
        instructionsText();
        startGameButton();

    }

    private void newMemoryGameUserAnswerField() {
        memoryGameUserAnswerField = new TextField();
        memoryGridPane.add(memoryGameUserAnswerField, 0, 2);

        Button submitButton = new Button("Vasta");
        memoryGridPane.add(submitButton, 1, 2);

        submitButton.setOnMouseClicked(mouseEvent -> {
            String input = memoryGameUserAnswerField.getText();
            String[] insertedColors = input.split(" ");
            System.out.println(Arrays.toString(insertedColors));
            int points = 0;
            int i = 0;
            while (i < currentColors.length && i < insertedColors.length && colorsAndNames.getColorByName(insertedColors[i]) == currentColors[i]) {
                i++;
                System.out.println("point");
                points++;
            }
            memoryGameUserAnswerField.setText("Vastatud! Teenisite " + points + " punkti.");
        });

    }

    private void matchingGameLabel() {
        memoryGameLabel = new Label("Mälumäng");
        memoryGameLabel.setFont(Font.font("Calibri", FontWeight.BOLD, 22));
        memoryGridPane.add(memoryGameLabel, 0, 0);
        memoryGridPane.add(new Text("Kirjuta kõik nähud värvid tühikuga eraldatult ja õiges järjekorras:"), 0, 1);
    }

    // näitab ära useri valitud raskustaseme
    private void instructionsText() {
        // Kuvab kõik võimalikud värvid
        Text info = new Text("Saadaval värvid:");
        info.setFont(Font.font("Calibri", FontWeight.NORMAL, 15));
        memoryGridPane.add(info, 0, 3);

        List<String> colorNames =  colorsAndNames.getColorNames();
        List<Color> colorList = colorsAndNames.getColors();
        HBox hbox = new HBox();
        hbox.setSpacing(7);
        hbox.setFillHeight(true);

        // iga värvi kuvamine ekraanile, mille seast saab kasutaja valida
        for (int i = 0; i < colorNames.size(); i++) {
            String colorName = colorNames.get(i);
            Color color = colorList.get(i);
            Text colorInfo = new Text(colorName);
            colorInfo.setFont(Font.font("Calibri", FontWeight.NORMAL, 15));
            colorInfo.setFill(color);
            hbox.getChildren().add(colorInfo);
        }
        memoryGridPane.add(hbox, 0, 4);


        // raskuse info
        String difficulty = DifficultyCurrentState.getDifficultyLevel();
        Text difficultyText = new Text("Valitud raskustase: " + difficulty);
        difficultyText.setFont(Font.font("Calibri", FontWeight.NORMAL, 15));
        memoryGridPane.add(difficultyText, 0, 5);
    }

    public Scene getMemoryScene() {
        return memoryScene;
    }

    private void startGameButton() throws InterruptedException {
        startButton = new GameButtons("Näita värve", 130, 55, Color.BLACK);
        memoryGridPane.add(startButton, 0, 6);

        // raskustaseme põhjal ajaperioodi arvutamine
        String difficulty = DifficultyCurrentState.getDifficultyLevel();
        int timePeriod = -1; // hoiab endas ajaperioodi, kui kaua näidatakse kasutajale värve
        switch (difficulty) {
            case "Lihtne":
                timePeriod = 15;
                break;
            case "Keskmine":
                timePeriod = 10;
                break;
            case "Raske":
                timePeriod = 4;
                break;
            default: // kui raskusastmeks on null
                System.exit(1); // TODO: null raskusastet ei tohiks saada sisestada
                break;

        }

        // näitab korraks värve - vastavalt valitud raskusastmele
        int finalTimePeriod = timePeriod;
        startButton.setOnAction(actionEvent -> {

            // kirjutab stseni värvidega üle
            try {
                showColors(finalTimePeriod); // värvide näitamise ajaperiood sõltub raskustasemest
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

    }

    public void showColors(int timePeriod) throws InterruptedException {

        // uue stseeni loomine ja selle esiletõstmine lavale
        GridPane popUpPane = new BaseGridPane();
        popUpScene = new Scene(popUpPane, 600, 600);
        addColors(popUpPane);

        Window window = memoryScene.getWindow();
        if (window instanceof Stage) {
            Stage stage = (Stage) window;
            stage.setScene(popUpScene);
        }

        // aja lugemine kuni 0ni
        Timer timer = new Timer();
        TimerTask task = new MemoryGameTimer(timePeriod, this);

        timer.scheduleAtFixedRate(task, 0, 1000L);

        synchronized (this) {
            this.wait();
            timer.cancel();
            timer.purge();
            showMemoryScene(); // kui taimer lõpetab tiksub 0ni, siis kuvatakse eelmine stseen
        }
    }

    private void addColors(GridPane popUpPane) {
        // saab juhuslikult valitud värvid colorsAndNames isendilt
        System.arraycopy(colorsAndNames.getRandomColors(), 0, currentColors, 0, 4);
        System.arraycopy(colorsAndNames.getRandomColors(), 0, currentColors, 4, 2);

        int i = 0;
        for (Color color : currentColors) { // värvid kuvatakse ristkülikutena ekraanile
            popUpPane.add(new Rectangle(200, 30, color), 0, i++);
        }
    }

    public void showMemoryScene() { // algsele stseenile tagasi vahetamine
        // algse stseeni kuvamine
        System.out.println("Stseeni vahetus");
        Window window = popUpScene.getWindow();
        if (window instanceof Stage) {
            Stage stage = (Stage) window;
            stage.setScene(memoryScene);
        }
    }



}
