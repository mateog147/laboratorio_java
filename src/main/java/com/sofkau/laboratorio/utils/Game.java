package com.sofkau.laboratorio.utils;

import com.sofkau.laboratorio.interfaces.ValidatorInterface;

import org.jboss.logging.Logger;

public class Game implements ValidatorInterface {
    Player player;
    Question quiz;
    int gameScore;

    static final Logger logger = Logger.getLogger("logger");


    public Game(Player player, Question quiz) {
        this.player = player;
        this.quiz = quiz;
        this.gameScore = 0;
    }

    public void renderQuestion(Question quiz){
        logger.info("Pregunta: "+ quiz.getDescription());
    }
    @Override
    public Boolean check(String answerCorrect, String answerSelected, int level) {
        if (answerSelected.equalsIgnoreCase(answerCorrect)) {
            switch (level) {
                case 1 -> this.gameScore += 100;
                case 2 -> this.gameScore += 200;
                case 3 -> this.gameScore += 300;
                case 4 -> this.gameScore += 400;
                case 5 -> {
                    this.gameScore += 500;
                    win(level, answerCorrect, answerSelected);
                }
                default -> this.gameScore = 0;
            }

            return true;

        } else {
            this.gameScore = 0;
            gameOver(0);
        }
        return false;
    }

    @Override
    public Boolean win(int level, String answerCorrect, String answerSelected) {
        Boolean checkedAnswer = check(answerCorrect, answerSelected, level);
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

    public void startGame (){
        Login newLogin = new Login();
        Player newPlayer = new Player(newLogin.getName()) ;
        try {
            DbConector conector = DbConector.getInstance();
            Question quiz = conector.getQuestion(1);
            Game newGame = new Game(newPlayer,quiz);
        }catch (Exception error){

        }



=    }
}



