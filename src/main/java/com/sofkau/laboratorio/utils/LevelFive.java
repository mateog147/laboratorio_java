package com.sofkau.laboratorio.utils;

import java.util.ArrayList;

public class LevelFive extends Question{
    private String category;
    private static final int SCORE=800;
    public LevelFive(String description, String correctAnswer, ArrayList<String> option, String category) {
        super(description, correctAnswer, option,810);
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
        return "\nCategoria: "+this.category+"\nNIVEL 5"+super.toString();
    }

    @Override
    public int level() {
        return 0;
    }
}
