package com.example.gameuidemo;

public class DifficultyCurrentState {
    // kasutaja valitud raskustase
    private static String difficultyLevel;

    public static String getDifficultyLevel(){
        return difficultyLevel;
    }
    public static void setDifficultyLevel(String level){
        difficultyLevel = level;
    }
}
