package com.sofkau.laboratorio;

import com.sofkau.laboratorio.utils.Login;
import com.sofkau.laboratorio.utils.Player;
import org.jboss.logging.Logger;
public class Main {
    static final Logger logger = Logger.getLogger("logger");
    public static void main(String[] args) {
        Login newLogueo = new Login();
        Player newPlayer = new Player(newLogueo.getName(),0);
        logger.info(newPlayer);




    }
}
