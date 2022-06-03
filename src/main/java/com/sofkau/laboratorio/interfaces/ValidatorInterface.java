package com.sofkau.laboratorio.interfaces;
/**
 * Interface que modela los metodos empleados para verificar y manejar los eventos del juego
 * @author: @erickdiaz01
 * @interface
 * @date 03-06-2022
 */
public interface ValidatorInterface {
    public Boolean check();
    public void win();
    public void gameOver(int condicion);
}
