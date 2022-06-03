package com.sofkau.laboratorio.utils;

import java.util.ArrayList;

class DbConectorTest {
    public static void main(String[] args) {
        DbConector conector = DbConector.getInstance();
        Player testPlayer = new Player("Prueba", 100);
        ArrayList<Player> top = new ArrayList<>();
        try {
            conector.getQuestion(1);
            System.out.println(conector.savePlayer(testPlayer));
            top=conector.topPlayers();

            for (Player player:
                 top) {
                System.out.println( player.toString());
            }
        }catch(Exception e){
            System.out.print(e.getMessage());
        }
    }
}