package com.sofkau.laboratorio;

import com.sofkau.laboratorio.utils.*;
import org.jboss.logging.Logger;

/**
 * Clase Ejecutable de la clase.
 * Corre el metodo main.
 * @author Mateo Gutierrez <mateog147@hotmail.com>
 * @version 1.0.0 2022/06/03
 * @since 1.0.0
 */
public class Main {
    /**
     * Intancia de la clase Logger para imprimir mensajes por consola.
     */
    static final Logger logger = Logger.getLogger("logger");

    /**
     * Metodo main.
     * Intancia un conector a la base de datos.
     * Usa una intanicia de la clase Login para ingresar un nombre.
     * Intancia un objeto de la clase Player y Crea un objeto Game.
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            DbConector conector = DbConector.getInstance();
            Login login = new Login();
            Player user = new Player(login.getName());
            Question quiz = conector.getQuestion(1);
            Game newGame = new Game(user, quiz);
            newGame.renderQuestion();
            /**
             * Mientras la respuesta sea correcta continua el ciclo.
             */
            while (Boolean.TRUE.equals(newGame.check())){
                newGame.renderQuestion();
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }


    }
}

