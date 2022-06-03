package com.sofkau.laboratorio.interfaces;

import com.sofkau.laboratorio.utils.Player;
import com.sofkau.laboratorio.utils.Question;

public interface ValidatorInterface {
    public Boolean check(String answerCorrect,String answerSelected, int level);
    public Boolean win(int level,String answerCorrect,String answerSelected);
    public Boolean gameOver(int condicion);
}
