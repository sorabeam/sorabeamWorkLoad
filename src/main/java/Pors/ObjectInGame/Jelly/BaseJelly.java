package Pors.ObjectInGame.Jelly;

import Beam.Cookies.Cookie;
import Beam.Media.JooxBox;
import Got.GameLogic.GameLogic;
import Pors.ObjectInGame.Interactable;

public class BaseJelly implements Interactable {
    private String name;
    private int score;

    public BaseJelly(String name, int score)
    {
        setName(name);
        setScore(score);
    }

    @Override
    public void interact(Cookie cookie) {
        GameLogic.addScore(getScore());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
