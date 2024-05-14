package com.example.gameuidemo;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class EndScene {

    private BaseGridPane endGrid;
    private Scene endScene;

    public EndScene(String infoStr, int points, String userName, boolean isMatchingGame) throws IOException {
        endGrid = new BaseGridPane();
        endScene = new Scene(endGrid, 400, 350);

        File file; // hoiab endas praegusele mängule vastavat File isendit
        if (isMatchingGame) file = new File("matchingGame.dat");
        else file = new File("memoryGame.dat");

        Map<String, Integer> scoreMap;
        if (file.exists() && file.length() != 0) {
            scoreMap = readFile(file);
            // kui mängijal on rohkem punkte, kui tema rekord
            int highscore = scoreMap.getOrDefault(userName, 0);

            if (points > highscore) {
                scoreMap.put(userName, points);
                writeToFile(scoreMap, file);
            }
        } else {
            // kui .dat fail on tühi või selles ei ole midagi kirjas
            scoreMap = new HashMap<>();
            scoreMap.put(userName, points);
            writeToFile(scoreMap, file);
        }

        System.out.println(scoreMap);
        Label gameOverLabel = new Label("Mäng on läbi!");
        Label infoLabel = new Label(infoStr + "\nTeenisite " + points + " punkti.");
        gameOverLabel.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
        infoLabel.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));

        endGrid.add(gameOverLabel, 0, 0);
        endGrid.add(infoLabel, 0, 1);
        Button buttonToHomePage = new GameButtons("Tagasi avalehele", 350, 55, Color.BLACK);
        endGrid.add(buttonToHomePage, 0, 2);


        buttonToHomePage.setOnMouseClicked(mouseEvent -> {
            OpeningScene openingScene = new OpeningScene();
            Scene scene = openingScene.getOpeningScene();
            Window window = endScene.getWindow();
            if (window instanceof Stage) {
                Stage stage = (Stage) window;
                stage.setScene(scene);
            }

        });

    }

    private void writeToFile(Map<String, Integer> scoreMap, File file) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
            dos.writeInt(scoreMap.size());
            for (Map.Entry<String, Integer> entry : scoreMap.entrySet()) {
                dos.writeUTF(entry.getKey());
                dos.writeInt(entry.getValue());
            }
        }
    }

    private Map<String, Integer> readFile(File file) throws IOException {
        Map<String, Integer> scoreMap = new HashMap<>();
        try (DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
            int differentUsers = dis.readInt();
            for (int i = 0; i < differentUsers; i++) {
                scoreMap.put(dis.readUTF(), dis.readInt());
            }
        }
        return scoreMap;
    }


    public Scene getEndScene() {
        return endScene;
    }
}
