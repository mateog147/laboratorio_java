package com.sofkau.laboratorio;
import com.sofkau.laboratorio.utils.*;
import org.jboss.logging.Logger;
public class Main {
    static final Logger logger = Logger.getLogger("logger");
    public static void main(String[] args) {

        try {
            DbConector conector = DbConector.getInstance();
            Login login = new Login();
            Player user = new Player(login.getName());
            Question quiz = conector.getQuestion(1);
            Game newGame = new Game(user, quiz);
            newGame.renderQuestion();
            newGame.check();
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }
}
