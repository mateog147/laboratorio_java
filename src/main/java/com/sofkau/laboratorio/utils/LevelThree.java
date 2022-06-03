package com.sofkau.laboratorio.utils;

import java.util.ArrayList;

public class LevelThree extends Question{
    private String category;
    private static final int SCORE=600;
    public LevelThree(String description, String correctAnswer, ArrayList<String> option, String category) {
        super(description, correctAnswer, option,350);
        this.category=category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "\nCategoria: "+this.category+"\nNIVEL 3"+super.toString();
    }
    @Override
    public int level() {
        return 0;
    }
}

