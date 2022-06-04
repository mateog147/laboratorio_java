package com.sofkau.laboratorio.utils;

import com.sofkau.laboratorio.interfaces.ValidatorInterface;

import org.jboss.logging.Logger;

import java.util.Scanner;

public class Game implements ValidatorInterface {
    private Player player;
    private Question quiz;
    private int gameScore;
    private int level;
    protected static final Scanner scanner = new Scanner(System.in);
    private static final Logger logger = Logger.getLogger("logger");

    public Game(Player player, Question quiz) {
        this.player = player;
        this.quiz = quiz;
        this.gameScore = 0;
        this.level=1;
    }

    public void renderQuestion(){
        logger.info(quiz.toString());
    }
    @Override
    public Boolean check() {
        String answerCorrect= quiz.getCorrectAnswer();
        int indexOption = scanner.nextInt();
        int indexCorrect = quiz.getOption().indexOf(answerCorrect)+1;
        if(indexOption==indexCorrect){
            this.level++;
            if(level>5){win();};
            //Logica de los puntajes
            logger.info("Respuesta correcta");
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


    @Override
    public void win() {
            this.player.setScore(this.gameScore);
            logger.info("¡HAS GANADO! !FELICITACIONES! \n Puntaje final: " + this.player.getScore());
            savePlayer();
    }

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
    }
    private void savePlayer(){
        DbConector conector = DbConector.getInstance();
        try {
            conector.savePlayer(this.player);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

}


