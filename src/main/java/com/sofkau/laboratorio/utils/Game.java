package com.sofkau.laboratorio.utils;

import com.sofkau.laboratorio.interfaces.ValidatorInterface;

import org.jboss.logging.Logger;

import java.util.ArrayList;
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
            if(level>5){
                win();
                return false;
            };
            //Logica de los puntajes
            this.gameScore+=this.quiz.getScore();



            try{
                DbConector conectorCheck = DbConector.getInstance();
                this.quiz = conectorCheck.getQuestion(level);
            }catch (Exception error){
                logger.warn(error.getMessage());
            }
            return true;
        }else if(indexOption==0) {
            gameOver(1);
        }else{

            gameOver(0);
        }
        return false;
    }


    @Override
    public void win() {
            this.player.setScore(this.gameScore);
            logger.info("¡HAS GANADO! !FELICITACIONES! \n Puntaje final: " + this.player.getScore());
            savePlayer();
            printRanking();
    }

    @Override
    public void gameOver(int condicion) {

        if (condicion == 0) {
            logger.info("HAS PERDIDO, RESPUESTA INCORRECTA \n Puntaje final: 0");
            savePlayer();
            printRanking();
        } else {
            this.player.setScore(this.gameScore);
            logger.info("TE HAS RETIRADO, GRACIAS POR PARTICIPAR \n Puntaje final: " + this.player.getScore()+" DOLARES!!");
            savePlayer();
            printRanking();
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

    public void printRanking (){
        try {
            DbConector conector = DbConector.getInstance();
            ArrayList<Player> players=  conector.topPlayers();
            StringBuilder stringPlayers = new StringBuilder();
            stringPlayers.append("\nJugador     Score\n");
            players.forEach(

                    (x)-> stringPlayers.append(x.getName()+"     "+x.getScore()+"\n")
            );
            logger.info(stringPlayers.toString());
        }catch (Exception error){
          logger.warn(error);
        }

    }

}


