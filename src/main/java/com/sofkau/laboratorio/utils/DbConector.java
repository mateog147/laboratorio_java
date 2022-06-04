package com.sofkau.laboratorio.utils;
import java.nio.file.Path;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;

/**
 * Clase de conección a la base de datos.
 *
 * Se conecta a la base de datos usando el driver del JDBS de SQLite.
 *
 * Consulta pregunta.
 * Guarda los usuarios y sus puntajes.
 * Consulta jugadores.
 *
 * @author Mateo Gutierrez <mateog147@hotmail.com>
 * @version 1.0.0 2022/06/03
 * @since 1.0.0
 */
public class DbConector {
    private String rootPath ;
    private String path;
    /**
     * Constantes Estirng para conexión y consulta
     */
    private static final String DBMS ="org.sqlite.JDBC";
    private static final String JDBC ="jdbc:sqlite:";
    private static final String DESCOL ="DESCRIPCION";
    private static final String CORCOL ="CORRECTA";
    private static final String CATCOL ="CATEGORIA";

    /**
     * Intancia de la clase Random
     */
    private Random random = new Random();

    /**
     * Unica intancia de clase que se retornara al pedirla dentro de la aplicación
     */
    private static final   DbConector CONECTOR = new DbConector();

    /**
     * Retorna la instancia de clase.
     * @return DbConector
     */
    public static DbConector getInstance(){
        return CONECTOR;
    }
    private DbConector(){
        this.rootPath = Path.of("").toAbsolutePath().toString();
        this.path = rootPath + "/src/main/java/com/sofkau/laboratorio/data/gameDb.db";
    }

    /**
     * Retorna un objeto pregunta.
     * De las clases hijas de question.
     * Devuelve la clase segunel nivel ingresado.
     * @param level int Nivel de la pregunta
     * @return Question
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Question getQuestion(int level) throws SQLException,ClassNotFoundException{

        int index = random.nextInt(5) +1;
        Class.forName(DBMS);
        Connection conec = DriverManager.getConnection(JDBC+path);
        try(Statement sta = conec.createStatement()){
            ResultSet res = sta.executeQuery("SELECT * FROM preguntas where nivel = '"+level+"' AND PREGUNTA == '"+index+"'");
            if(res.next()){
                Question newQuestion = createQuestion(res,level);
                conec.close();
                return newQuestion;
            }
            else{
                conec.close();
                throw new SQLException("Error al cargar");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Metodo para almacenar un jugador en la bae de datos.
     *
     * @param player Player
     * @return Integer 1 si fue exitoso 0 si no lo fue
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Integer savePlayer(Player player) throws SQLException,ClassNotFoundException{
        LocalDate date = LocalDate.now();
        Class.forName(DBMS);
        Connection conec = DriverManager.getConnection(JDBC+this.path);
        try(Statement sta = conec.createStatement()){
            int res = sta.executeUpdate("INSERT INTO usuarios(USER,PUNTAJE,FECHA) VALUES('"+player.getName()+"','"+player.getScore()+"','"+date+"')");

            if(res>0){
                conec.close();
                return 1;
            }else{
                conec.close();
                return 0;
            }
        }
    }

    /**
     * Metodo que retorna el Top 5 de Jugadores.
     *
     * @return ArrayList<Player>
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ArrayList<Player> topPlayers() throws SQLException,ClassNotFoundException{
        ArrayList<Player> players = new ArrayList<>();
        int counter = 1;
        Class.forName(DBMS);
        Connection conec = DriverManager.getConnection(JDBC+this.path);

        try(Statement sta = conec.createStatement()){
            ResultSet res = sta.executeQuery("SELECT * FROM usuarios ORDER BY puntaje DESC, fecha DESC");
            while(res.next() && counter<=5){
                Player player = new Player(res.getString("USER"),res.getInt("puntaje"));
                players.add(player);
                counter+=1;
            }
            conec.close();
            return players;
        }
    }

    private Question createQuestion(ResultSet res, int level){

        try {
            ArrayList<String> answerList = new ArrayList<>();
            answerList.add(res.getString("RESPUESTA1"));
            answerList.add(res.getString("RESPUESTA2"));
            answerList.add(res.getString("RESPUESTA3"));
            answerList.add(res.getString("RESPUESTA4"));

            switch (level){
                case 1 -> {
                    return new LevelOne(res.getString(DESCOL), res.getString(CORCOL), answerList, res.getString(CATCOL));
                }

                case 2 -> {
                    return new LevelTwo(res.getString(DESCOL), res.getString(CORCOL), answerList, res.getString(CATCOL));
                }

                case 3 -> {
                    return new LevelThree(res.getString(DESCOL), res.getString(CORCOL), answerList, res.getString(CATCOL));
                }

                case 4 -> {
                    return new LevelFour(res.getString(DESCOL), res.getString(CORCOL), answerList, res.getString(CATCOL));
                }

                case 5 -> {
                    return new LevelFive(res.getString(DESCOL), res.getString(CORCOL), answerList, res.getString(CATCOL));
                }

                default -> {
                    return null;
                }
            }

        }catch (Exception e){
            Logger logger = Logger.getLogger("logger");
            logger.warning("error");
        }
        return null;
    }

}
