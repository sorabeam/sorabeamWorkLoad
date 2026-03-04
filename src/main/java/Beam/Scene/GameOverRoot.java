package Beam.Scene;

import Beam.Animation.Animate;
import Beam.Animation.AnimationType;
import Beam.Asset;
import Beam.Button.BaseButton;
import Beam.Button.NavBtn;
import Beam.Cookies.Cookie;
import Beam.Image.OutlineText;
import Beam.UI.GameOverUI.*;
import Filmmy.Pearl;
import Got.GameLogic.GameLogic;
import Got.GameLogic.GameState;
import javafx.animation.AnimationTimer;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import Beam.CharactorData;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameOverRoot extends BaseRoot {

    //change to game data later, after game really done
    public GameOverRoot() {
        super();

        Buttons btns = new Buttons();
        CharactorShow imgShow = new CharactorShow();

        Animate cookie = imgShow.getCookie();
        DVDShow dvd = new DVDShow();

        setAlignment(imgShow,Pos.BOTTOM_LEFT);
        setMargin(imgShow,new Insets(0,0,-100,-100));
        AnimationTimer timer = new AnimationTimer() {

            long last = 0;

            @Override
            public void handle(long now) {

                if (last == 0) {
                    last = now;
                    return;
                }

                double dt = (now - last) / 1e9;
                last = now;

                if(!cookie.getAnimationState().equals(AnimationType.IDLE)){
                    cookie.changeAnimationState(AnimationType.IDLE);
                }

                cookie.update(dt);
            }
        };

        timer.start();

        getChildren().addAll(
                new GoverBg(),
                imgShow,
                new Banner(),
                dvd,
                btns
        );

        setAlignment(dvd,Pos.CENTER_RIGHT);
        setMargin(dvd,new Insets(0,0,-70,0));
        StackPane.setAlignment(btns,Pos.BOTTOM_RIGHT);
        StackPane.setMargin(btns,new Insets(0,30,0,0));;
    }
}
