package com.sofkau.laboratorio.utils;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

public class DbConector {
    private String rootPath ;
    private String path;

    private static  DbConector CONECTOR = new DbConector();

    public static DbConector getInstance(){
        return CONECTOR;
    }
    private DbConector(){
        this.rootPath = Path.of("").toAbsolutePath().toString();
        this.path = rootPath + "/src/main/java/com/sofkau/laboratorio/data/gameDb.db";
    }

    public void getQuestion(int level) throws Exception{
        int index = (int) Math.floor(Math.random()*5 + 1);
        Class.forName("org.sqlite.JDBC");
        Connection conec = DriverManager.getConnection("jdbc:sqlite:"+path);
        try(Statement sta = conec.createStatement()){
            ResultSet res = sta.executeQuery("SELECT * FROM preguntas where nivel = '"+level+"' AND PREGUNTA == '"+index+"'");
            if(res.next()){
                //Question newQuestion = new Question(res.getString("DESCRIPCION"), res.getString("RESPUESTA1"), res.getString("RESPUESTA2"), res.getString("RESPUESTA3"), res.getString("RESPUESTA4"), res.getString("CORRECTA"));
                System.out.println("Conectado");
                System.out.println(res.getString("DESCRIPCION"));
                conec.close();
                //return newQuestion;
            }
            else{
                conec.close();
                throw new Exception("Error al cargar");
            }
        }
    }

    public Integer savePlayer(String name, int score) throws Exception{
        LocalDate date = LocalDate.now();
        //me conecto a la base de datos
        Class.forName("org.sqlite.JDBC");
        Connection conec = DriverManager.getConnection("jdbc:sqlite:"+this.path);
        try(Statement sta = conec.createStatement()){
            int res = sta.executeUpdate("INSERT INTO usuarios(USER,PUNTAJE,FECHA) VALUES('"+name+"','"+score+"','"+date+"')");
            if(res>0){
                conec.close();
                return 1;
            }else{
                conec.close();
                return 0;
            }
        }
    }

}
