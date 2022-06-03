package com.sofkau.laboratorio.utils;

import java.util.ArrayList;

public abstract class Question {
    private String description;
    private String correctAnswer;
    private ArrayList<String> option;

    public Question(String description, String correctAnswer, ArrayList<String> option) {
        this.description = description;
        this.correctAnswer = correctAnswer;
        this.option = option;
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

    public abstract int level();

    @Override
    public String toString() {
        return "Question{" +
                "description='" + description + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", option=" + option +
                '}';
    }


}
