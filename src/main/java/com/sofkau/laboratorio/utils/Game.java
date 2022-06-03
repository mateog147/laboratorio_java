package com.sofkau.laboratorio.utils;

import com.sofkau.laboratorio.interfaces.ValidatorInterface;

import org.jboss.logging.Logger;

import java.util.Scanner;

public class Game implements ValidatorInterface {
    Player player;
    Question quiz;
    int gameScore;
    int level;
    protected static final Scanner scanner = new Scanner(System.in);
    static final Logger logger = Logger.getLogger("logger");


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
           //Logica de los puntajes


            logger.info("Respuesta correcta");
            try{
                DbConector conectorCheck = DbConector.getInstance();
                this.quiz = conectorCheck.getQuestion(level);
                logger.info(this.level);
            }catch (Exception error){

            }

            return true;

        }else {
            logger.info("Respuesta incorrecta");
        }
return false;

    }


    @Override
    public Boolean win(int level, String answerCorrect, String answerSelected) {
        Boolean checkedAnswer = check();
        if (Boolean.TRUE.equals(checkedAnswer && level == 5)) {

            this.player.setScore(this.gameScore);
            logger.info("¡HAS GANADO! !FELICITACIONES! \n Puntaje final: " + this.player.getScore());
//Mandar la informacion del juego a la base de datos
            return true;
        }

        return false;


    }

    @Override
    public Boolean gameOver(int condicion) {
        if (condicion == 0) {

            this.player.setScore(this.gameScore);
            logger.info("HAS PERDIDO, RESPUESTA INCORRECTA \n Puntaje final: " + this.player.getScore());
            //Mandar la informacion a la base de datos del juego
        } else {
            this.player.setScore(this.gameScore);
            logger.info("TE HAS RETIRADO, GRACIAS POR PARTICIPAR \n Puntaje final: " + this.player.getScore());
            //Mandar la informacion a la base de datos del juego
        }
        return true;
    }

}



