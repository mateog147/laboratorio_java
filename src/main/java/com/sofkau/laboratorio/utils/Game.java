package com.sofkau.laboratorio.utils;

import com.sofkau.laboratorio.interfaces.ValidatorInterface;

import org.jboss.logging.Logger;

import java.util.Scanner;

public class Game implements ValidatorInterface {
    Player player;
    Question quiz;
    int gameScore;
    protected static final Scanner scanner = new Scanner(System.in);
    static final Logger logger = Logger.getLogger("logger");


    public Game(Player player, Question quiz) {
        this.player = player;
        this.quiz = quiz;
        this.gameScore = 0;
    }

    public void renderQuestion(){
        logger.info("Pregunta: "+ this.quiz.toString());
    }
    @Override
    public Boolean check() {
        String correctAnswer = this.quiz.getCorrectAnswer();
        int resolved=scanner.nextInt();
        return true;
    }

    @Override
    public Boolean win(int level, String answerCorrect, String answerSelected) {


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



   }
}



