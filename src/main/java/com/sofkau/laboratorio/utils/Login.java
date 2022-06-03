package com.sofkau.laboratorio.utils;

import org.jboss.logging.Logger;

import java.util.Scanner;

public class Login {
    private String name;

    protected static final Scanner scanner = new Scanner(System.in);
    static final org.jboss.logging.Logger logger = Logger.getLogger("logger");

    public Login(){
        String nameUser = printLogin();
        while (nameUser.equals("")){
            printLogin();
        }
        this.name=nameUser;
    }


    public String printLogin(){
    logger.info("Bienvenido a SofkaU Quiz \n"+"Por favor ingrese su nombre para empezar a jugar: ");
     String nameUser = scanner.next();

     return nameUser;
    }

    public String getName() {
        return name;
    }
}
