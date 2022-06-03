package com.sofkau.laboratorio.utils;

import java.util.ArrayList;

public class LevelFour extends  Question{
    private String category;
    private static final int award=400;
    public LevelFour(String description, String correctAnswer, ArrayList<String> option, String category) {
        super(description, correctAnswer, option);
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
