package com.sofkau.laboratorio.utils;

import java.util.ArrayList;

public class LevelThree extends Question{
    private String category;

    public LevelThree(String description, String correctAnswer, ArrayList<String> option, int level, String category) {
        super(description, correctAnswer, option, level);
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

