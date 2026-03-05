package Beam.Scene;

import Beam.Animation.Animate;
import Beam.Animation.AnimationType;
import Beam.UI.GameOverUI.*;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;

public class GameOverScene extends BaseScene {

    //change to game data later, after game really done
    public GameOverScene() {
        super();

        Buttons buttons = new Buttons();
        CharactorShow imgShow = new CharactorShow();

        Animate cookie = imgShow.getCookie();
        DVDShow dvdShow = new DVDShow();

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

                //แก้บัค งง เหมือนกันว่าใน imgShow ก็เขียนแล้ว แต่มันไม่ติดเฉยเลย

                if (!cookie.getAnimationState().equals(AnimationType.IDLE)) {
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
                dvdShow,
                buttons
        );

        setAlignment(dvdShow,Pos.CENTER_RIGHT);
        setMargin(dvdShow,new Insets(0,0,-70,0));
        StackPane.setAlignment(buttons,Pos.BOTTOM_RIGHT);
        StackPane.setMargin(buttons,new Insets(0,30,0,0));;
    }
}
