package com.sofkau.laboratorio.utils;

import java.util.ArrayList;

public abstract class Question {
    private String description;
    private String correctAnswer;
    private ArrayList<String> option;
    private int level;

    public Question(String description, String correctAnswer, ArrayList<String> option, int level) {
        this.description = description;
        this.correctAnswer = correctAnswer;
        this.option = option;
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void getCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public ArrayList<String> getOption() {
        return option;
    }

    public void setOption(ArrayList<String> option) {
        this.option = option;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public abstract int level();

    @Override
    public String toString() {
        return "Question{" +
                "description='" + description + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", option=" + option +
                ", level=" + level +
                '}';
    }


}
