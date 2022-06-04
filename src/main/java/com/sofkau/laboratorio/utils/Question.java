package com.sofkau.laboratorio.utils;
import java.util.List;
/**
 * Esta clase abstracta se defino el molde de las preguntas.Esta superclase sera extendia a sus clases hijas
 * @author: Diego Felipe Muñoz Mosquera
 * @version: 1.0.0
 */
public abstract class Question {
    //Campos de la clase
    private final String description;
    private final String correctAnswer;
    private final List<String> option;
    private  int score;

    /**
     * Constructor para la serie de números aleatorios
     * @param description El parámetro se define la pregunta
     * @param correctAnswer El parámetro se define la respuesta correcta
     * @param option Listado de las opciones de respuesta
     * @param score El puntaje por cada respuesta correcta
     */
    protected Question(String description, String correctAnswer, List<String> option , int score) {
        this.description = description;
        this.correctAnswer = correctAnswer;
        this.option = option;
        this.score=score;
    }
    /**
     * Método que devuelve una pregunta
     * @return String con la pregunta
     */
    public String getDescription() {
        return description;
    }
    /**
     * Método que devuelve una pregunta
     * @return String con la pregunta
     */
    public String getCorrectAnswer() {
        return correctAnswer;
    }
    /**
     * Método que devuelve una listado de opciones
     * @return List con la opciones de respuesta
     */
    public List<String> getOption() {
        return option;
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    /**
     * Método que una pregunta y sus opciones
     * @return toString con la descripcion y opcines de respuesta
     */
    @Override
    public String toString() {

        return "\nPregunta: " + description + '\n' +
                "1. " + option.get(0) + '\n' +
                "2. " + option.get(1) + '\n' +
                "3. " + option.get(2) + '\n' +
                "4. " + option.get(3) + "\n \n" +
                "Si quiere retirarse del juego ingrese 0"
                ;
    }


}