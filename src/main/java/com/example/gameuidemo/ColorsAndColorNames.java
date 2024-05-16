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
    private Color[] randomColors;

    public ColorsAndColorNames() {
        colorNames = List.of(new String[]{"Punane", "Sinine", "Roheline", "Kollane", "Oranž", "Lilla", "Roosa", "Pruun", "Must"});
        colors = List.of(new Color[]{Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.PURPLE, Color.PINK, Color.BROWN, Color.BLACK});
        random = new Random();
        setCorrectAnswerIndex();
        setRandomColorNames();
        setRandomColors();

    }

    // loob juhuslike värvinimedega sõnemassiivi
    public void setRandomColorNames() {
        String[] randomWords = new String[4];
        // täidab massiivi suvaliste värvinimedega
        for (int i = 0; i < randomWords.length; i++) {
            randomWords[i] = colorNames.get(random.nextInt(colorNames.size()));
        }
        randomColorNames = randomWords;
    }

    public String[] getRandomColorNames() {
        return randomColorNames;
    }

   // loob juhuslike värvidega Color massiivi
    public void setRandomColors() {
        Color[] randColors = new Color[4];
        // paneb õige vastuse indeksi kohale õige värvi vastavalt sõnale, mis sellel indeksil juba on
        randColors[correctAnswerIndex] = getColorByName(randomColorNames[correctAnswerIndex]);

        // täidab ülejäänud värvid suvaliselt
        for (int i = 0; i < randColors.length; i++) {
            if (i != correctAnswerIndex) {
                randColors[i] = colors.get(random.nextInt(colors.size()));
            }
        }
        randomColors = randColors;
    }

    public Color[] getRandomColors() {
        return randomColors;
    }

    // tagastab Color tüüpi objekti värvi nime järgi
    public Color getColorByName(String colorName) {
        int index = colorNames.indexOf(colorName);
        if (index != -1) {
            return colors.get(index);
        } else return null;
    }

    // setib ühe suvalise indeksi õigeks vastuseks
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
