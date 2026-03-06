package Main.Scene;

import Main.Animation.Animate;
import Main.Animation.AnimationType;
import Main.UI.GameOverUI.*;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;

/**
 * Scene displayed when the game ends.
 *
 * This scene shows the game over background, the player's character,
 * UI banners, decorative elements, and action buttons such as retry
 * or returning to the menu.
 */
public class GameOverScene extends BaseScene {

    /**
     * Creates the game over scene and initializes all UI components,
     * animations, and layout positioning.
     *
     * The scene includes:
     * - The background
     * - Character display
     * - Game over banner
     * - Decorative DVD animation
     * - Control buttons
     */
    public GameOverScene() {
        super();

        Buttons buttons = new Buttons();
        CharacterShow imgShow = new CharacterShow();

        Animate cookie = imgShow.getCookie();
        DVDShow dvdShow = new DVDShow();

        setAlignment(imgShow,Pos.BOTTOM_LEFT);
        setMargin(imgShow,new Insets(0,0,-100,-100));

        /**
         * Animation loop used to update the character animation
         * displayed on the game over screen.
         */
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

                // Ensure the character stays in IDLE animation state
                if (!cookie.getAnimationState().equals(AnimationType.IDLE)) {
                    cookie.changeAnimationState(AnimationType.IDLE);
                }

                cookie.update(dt);
            }
        };

        timer.start();

        getChildren().addAll(
                new GameOverBg(),
                imgShow,
                new Banner(),
                dvdShow,
                buttons
        );

        setAlignment(dvdShow,Pos.CENTER_RIGHT);
        setMargin(dvdShow,new Insets(0,0,-70,0));

        StackPane.setAlignment(buttons,Pos.BOTTOM_RIGHT);
        StackPane.setMargin(buttons,new Insets(0,30,0,0));
    }
}