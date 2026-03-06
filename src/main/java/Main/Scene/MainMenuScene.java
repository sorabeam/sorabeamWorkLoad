package Main.Scene;

import Main.Animation.Animate;
import Main.CharacterData;
import Main.UI.MainMenuUI.*;

import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

/**
 * Represents the main menu scene of the game.
 *
 * This scene displays the player's profile, character preview,
 * main menu buttons, decorative elements, and settings access.
 * It also runs a small animation loop to update the character preview.
 */
public class MainMenuScene extends BaseScene {

    /**
     * Creates the main menu scene and initializes all UI components
     * such as profile display, settings zone, character preview,
     * menu buttons, and background decorations.
     */
    public MainMenuScene() {
        super();

        StackPane profile = new MainMenuProfile(
                CharacterData.getCurrent_Cookie().get_Name(),
                CharacterData.getCurrent_Cookie().get_Score() + "",
                root
        );

        HBox setting = new SettingZone(root, spacer('H'));
        StackPane mainMenuButtons = new MainMenuButtons();
        StackPane glassDecoration = new GlassDecoration();

        CharacterShow imgShow = new CharacterShow();
        Animate cookie = imgShow.getCookie();

        /**
         * Animation loop used to update the preview cookie animation
         * displayed on the main menu screen.
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

                cookie.update(dt);
            }
        };

        timer.start();

        root.getChildren().addAll(
                new MainMenuBG(),
                profile,
                setting,
                imgShow,
                mainMenuButtons,
                glassDecoration
        );

        StackPane.setAlignment(glassDecoration, Pos.CENTER_RIGHT);
        StackPane.setAlignment(mainMenuButtons, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(setting, Pos.TOP_RIGHT);
        StackPane.setMargin(setting, new Insets(20, 20, 0, 0));
        StackPane.setAlignment(profile, Pos.TOP_LEFT);
    }
}