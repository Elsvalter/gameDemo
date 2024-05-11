package com.example.gameuidemo;
import javafx.scene.paint.Color;
import java.util.List;
import java.util.Random;


public class ColorsAndColorNames {
    private List<String> colorNames;
    private List<Color> colors;
    private Random random;
    private int correctAnswerIndex;
    private String[] randomColorNames;

    public ColorsAndColorNames() {
        colorNames = List.of(new String[]{"Punane", "Sinine", "Roheline", "Kollane", "Oranž", "Lilla", "Roosa", "Pruun", "Must"});
        colors = List.of(new Color[]{Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.PURPLE, Color.PINK, Color.BROWN, Color.BLACK});
        random = new Random();
        setCorrectAnswerIndex();
        setRandomColorNames();

    }


    public void setRandomColorNames() {
        String[] randomWords = new String[4];
        randomWords[correctAnswerIndex] = colorNames.get(random.nextInt(colorNames.size()));

        for (int i = 0; i < randomWords.length; i++) {
            if (i != correctAnswerIndex) {
                randomWords[i] = colorNames.get(random.nextInt(colorNames.size()));
            }
        }
        randomColorNames = randomWords;
    }

    public String[] getRandomColorNames() {
        return randomColorNames;
    }

    public Color[] getRandomColors() {
        Color[] randomColors = new Color[4];
        randomColors[correctAnswerIndex] = getColorByName(randomColorNames[correctAnswerIndex]);

        for (int i = 0; i < randomColors.length; i++) {
            if (i != correctAnswerIndex) {
                randomColors[i] = colors.get(random.nextInt(colors.size()));
            }
        }
        return randomColors;
    }

    public Color getColorByName(String colorName) {
        int index = colorNames.indexOf(colorName);
        if (index != -1) {
            return colors.get(index);
        } else return null;
    }

    private void setCorrectAnswerIndex(){
        correctAnswerIndex = random.nextInt(4);
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public List<String> getColorNames() {
        return colorNames;
    }

    public List<Color> getColors() {
        return colors;
    }
}
