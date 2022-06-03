package com.sofkau.laboratorio.utils;

import java.util.ArrayList;

public class LevelOne extends Question{
    private String category;
    public final int SCORE=100;
    public LevelOne(String description, String correctAnswer, ArrayList<String> option, String category) {
        super(description, correctAnswer, option,250);
        this.category=category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public int level() {
        return 0;
    }

}
