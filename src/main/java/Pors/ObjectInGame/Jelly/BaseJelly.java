package Pors.ObjectInGame.Jelly;

import Beam.Cookies.Cookie;
import GameLogic.GameLogic;
import Pors.ObjectInGame.Interactable;

import java.util.Objects;

public class BaseJelly implements Interactable {
    private String name;
    private int score;

    public BaseJelly(String name)
    {
        setName(name);
        if(Objects.equals(name, "Jelly2"))
        {
            setScore(200);
        }
        else
        {
            setScore(100);
        }
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
