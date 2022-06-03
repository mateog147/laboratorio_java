package com.sofkau.laboratorio.interfaces;

import com.sofkau.laboratorio.utils.Player;
import com.sofkau.laboratorio.utils.Question;

public interface ValidatorInterface {
    public Boolean check();
    public void win();
    public void gameOver(int condicion);
}
