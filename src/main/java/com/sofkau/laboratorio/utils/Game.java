package com.sofkau.laboratorio.utils;

import com.sofkau.laboratorio.interfaces.ValidatorInterface;

import org.jboss.logging.Logger;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * Clase juego, engloba toda la logica para la impresión del juego, 
 * el manejo de los eventos mediante el override de los metodos implementados desde la interface Validator
 * @class 
 * @author @erickdiaz01 @DiegoFelipe7 @mateog147
* {@code @date} 03-06-2022
 */
public class Game implements ValidatorInterface {
    /**
     * Atributos de las clase Player y la clase abstracta Question, el juego cuenta con un gameScore
     * global que dependiendo el evento del juego aumenta segun el nivel, se mantiene o se iguala a cero
     */
    private final Player player;
    private Question quiz;
    private int gameScore;
    private int level;
    protected static final Scanner scanner = new Scanner(System.in);
    private static final Logger logger = Logger.getLogger("logger");
/**
 * Atributos de las clase Player y la clase abstracta Question, inicializa el gameScore en cero y el nivel
 * del juego en cero
 * @param player Es un objeto de la clase Player, contiene un name y un score
 * @param quiz Es un objeto de la clase Question, contiene una descripcion, un ArrayList de opciones, un nivel y una respuesta correcta
 */
    public Game(Player player, Question quiz) {
        this.player = player;
        this.quiz = quiz;
        this.gameScore = 0;
        this.level=1;
    }
/**
 * Realiza la impresión del String retornado por el quiz actual, el cual contiene la informacion 
 * de la pregunta
* {@code @function}
 * */
    public void renderQuestion(){
        logger.info(quiz.toString());
    }

   
    @Override
     /*
      Implementacion del metodo 'abstracto' de la interfaz Validator
     */
    public Boolean check() {
        String answerCorrect= quiz.getCorrectAnswer();
        int indexOption = scanner.nextInt();
        int indexCorrect = quiz.getOption().indexOf(answerCorrect)+1;
        if(indexOption==indexCorrect){
            this.level++;
            if(level>5){
                win();
                return false;
            }
            //Logica de los puntajes
            this.gameScore+=this.quiz.getScore();

            try{
                DbConector conectorCheck = DbConector.getInstance();
                this.quiz = conectorCheck.getQuestion(level);
                logger.info(this.level);
            }catch (Exception error){
                logger.warn(error.getMessage());
            }
            return true;

        }else {
            logger.info("Respuesta incorrecta");
            gameOver(0);
        }
        return false;
    }

    /**
     * Funcion que imprime un mensaje que indica que el jugador ha ganado el juego con su puntaje final, adicionalmente
     * guarda el registro en la base de datos
     * {@code @function}
     */
    @Override
    public void win() {
            this.player.setScore(this.gameScore);
            logger.info("¡HAS GANADO! !FELICITACIONES! \n Puntaje final: " + this.player.getScore());
            savePlayer();
    }

    /**
     * Funcion que maneja el evento de escoger una respuesta incorrecta o el retirarse del juego
     * @param condicion Es una condicion binaria mandada desde el metodo check, 0 para respuesta errada
     *                  y 1 para retirarse del juego
     */
    @Override
    public void gameOver(int condicion) {

        if (condicion == 0) {
            logger.info("HAS PERDIDO, RESPUESTA INCORRECTA \n Puntaje final: 0");
            savePlayer();
        } else {
            this.player.setScore(this.gameScore);
            logger.info("TE HAS RETIRADO, GRACIAS POR PARTICIPAR \n Puntaje final: " + this.player.getScore());
            savePlayer();
        }
        savePlayer();
        printRanking();
    }

    /**
     * Funcion que instancia el conector de la base de datos utilizada, emplea el metodo de guardar el jugador
     * {@code @function}
     */
    private void savePlayer(){
        DbConector conector = DbConector.getInstance();
        try {
            conector.savePlayer(this.player);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    /**
     * Funcion que imprime finalmente el ranking del top 5 cuando se acaba el juego
     * instancia el conector de la base de datos y trae en un ArrayList los 5 jugadores con el mayor puntaje
     * {@code @function}
     */
    public void printRanking (){
        try {
            DbConector conector = DbConector.getInstance();
            ArrayList<Player> players=  conector.topPlayers();
            StringBuilder stringPlayers = new StringBuilder();
            stringPlayers.append("\nJugador     Score\n");
            players.forEach(

                x-> stringPlayers.append(x.getName()).append("     ").append(x.getScore()).append("\n")            );
            logger.info(stringPlayers.toString());
        }catch (Exception error){
          logger.warn(error);
        }

    }

}


