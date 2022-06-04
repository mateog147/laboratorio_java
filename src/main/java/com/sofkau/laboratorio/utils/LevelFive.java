package com.sofkau.laboratorio.utils;
import java.util.List;
/**
 * Esta extendiende los metodos get y set de su clase padre Question como sus atributos
 * @author: Diego Felipe Muñoz Mosquera
 * @version: 1.0.0
 */
public class LevelFive extends Question{
    private final String category;
    /**
     * Constructor para la serie de números aleatorios
     * @param description El parámetro se define la pregunta
     * @param correctAnswer El parámetro se define la respuesta correcta
     * @param option Listado de las opciones de respuesta
     * @param category con la categoria de la pregunta
     */
    public LevelFive(String description, String correctAnswer, List<String> option, String category) {
        super(description, correctAnswer, option,810);
        this.category=category;
    }
    /**
     * Método que una pregunta y sus opciones
     * @return toString con la descripcion y opcines de respuesta y su respectiva categoria
     */
    @Override
    public String toString() {
        return "\nCategoria: "+this.category+"\nNIVEL 5"+super.toString();
    }


}
