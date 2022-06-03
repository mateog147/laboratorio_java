package com.sofkau.laboratorio.utils;

import java.util.Scanner;
import java.util.logging.Logger;

public class Login {
    String name;

    protected static final Scanner scanner = new Scanner(System.in);
    private static final Logger LOGGER = Logger.getLogger("com.sofkau.laboratorio.utils.Login");

    public Login(){
        String nameUser = printLogin();
        while (nameUser.equals("")){
            printLogin();
        }
        this.name=nameUser;
    }


    public String printLogin(){
    LOGGER.info("Bienvenido a SofkaU Quiz"+"\n"+"Por favor ingrese su nombre para empezar a jugar: ");
     String nameUser = scanner.next();

     return nameUser;
    }

}
