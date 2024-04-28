package com.example.gameuidemo;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.Random;


public class ColorsAndColorNames {
    private List<String> colorNames;
    private List<Color> colors;
    private Random random;

    public ColorsAndColorNames() {
        colorNames = List.of(new String[]{"Punane", "Sinine", "Roheline", "Kollane", "Oranž", "Lilla", "Roosa", "Pruun", "Must"});
        colors = List.of(new Color[]{Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.PURPLE, Color.PINK, Color.BROWN, Color.BLACK});
        random = new Random();

    }


    public String[] getRandomColorNames() {
        String[] randomWords = new String[4];
        for (int i = 0; i < randomWords.length; i++) {
            int index = random.nextInt(colorNames.size());
            randomWords[i] = colorNames.get(index);
        }
        return randomWords;
    }

    public Color[] getRandomColors() {
        Color[] randomColors = new Color[4];
        for (int i = 0; i < randomColors.length; i++) {
            int index = random.nextInt(colors.size());
            randomColors[i] = colors.get(index);
        }
        return randomColors;
    }

    public boolean checkIfColorAndColorNameMatch(String colorName, Color color) {
        int colorIndex = colors.indexOf(colorName);
        int colorNameIndex = colorNames.indexOf(colorName);
        if (colorNameIndex == colorIndex) {
            return true;
        } else return false;
    }

    public Color getColorByName(String colorName) {
        int index = colorNames.indexOf(colorName);
        if (index != -1) {
            return colors.get(index);
        } else return null;
    }

}
