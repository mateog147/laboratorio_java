package com.sofkau.laboratorio.utils;

class DbConectorTest {
    public static void main(String[] args) {
        DbConector conector = DbConector.getInstance();
        try {
            conector.getQuestion(1);
            System.out.println(conector.savePlayer("Prueba",88));
        }catch(Exception e){
            System.out.print(e.getMessage());
        }
    }
}