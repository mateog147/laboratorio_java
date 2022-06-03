package com.sofkau.laboratorio.utils;

import org.jboss.logging.Logger;

import java.util.Scanner;

/**
 *Clase Login que modela el registro de un nuevo jugador en el juego
 * @class
 * @author @erickdiaz01
 */
public class Login {

    private final String name;

    protected static final Scanner scanner = new Scanner(System.in);
    static final org.jboss.logging.Logger logger = Logger.getLogger("logger");

    /**
     * Constructor que instancia el metodo que imprime el mensaje del login y captura el valor del nombre
     * del usuario para darle ese valor al name
     * {@code @constructor}
     */
    public Login(){
        String nameUser = printLogin();
        while (nameUser.equals("")){
            logger.warn("Ingrese un usuario valido");
            printLogin();
        }
        this.name=nameUser;
    }

    /**
     * Funcion que imprime en consola el mensaje para que el usuario ingrese su nombre
     * @return El valor del nombre ingresado
     */
    public String printLogin(){
    logger.info("Bienvenido a SofkaU Quiz \n"+"Por favor ingrese su nombre para empezar a jugar: ");

        return scanner.next();
    }

    /**
     *
     * @return El valor del nombre almacenado en el objeto
     */
    public String getName() {
        return name;
    }
}
