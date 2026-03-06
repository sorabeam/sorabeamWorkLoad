package Main.Button;

import Main.Asset;
import Main.Image.OutlineTextImage;
import Main.MediaPlayer;
import Main.Scene.GameplayScene;
import Main.GameLogic.GameLogic;
import Main.GameLogic.GameState;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Slider;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.Objects;

/**
 * Button used to open the settings popup.
 *
 * This class extends BaseButton and displays a popup overlay
 * containing volume controls and navigation buttons.
 */
public class SettingsPopUpButton extends BaseButton{

    /**
     * Root container where the settings popup overlay is added.
     */
    private final Pane root;

    /**
     * Overlay container used to display the settings popup.
     */
    private StackPane overlay;

    /**
     * Indicates whether the settings popup is currently open.
     */
    private boolean isOpen = false;

    /**
     * Initializes the settings popup button with image and root container.
     *
     * @param img image used as the button graphic
     * @param root root container where the popup overlay will be displayed
     */
    public SettingsPopUpButton(ImageView img, Pane root) {
        super(img);
        this.root = root;
    }

    /**
     * Handles the click event and displays the settings popup.
     *
     * If the game is currently in IN_GAME state, the gameplay scene
     * will be paused before opening the settings popup.
     */
    @Override
    public void handleClick() {
        super.handleClick();
        if(GameLogic.getGameState().equals(GameState.IN_GAME)){
            if (!GameLogic.getGameroot().getChildren().isEmpty()) {
                Node child = GameLogic.getGameroot().getChildren().getFirst();

                if (child instanceof GameplayScene) {
                    GameplayScene inGameScene = (GameplayScene) child;
                    inGameScene.stopGameByBool();
                }
            }
        }
        showSetting();
    }

    /**
     * Creates and displays the settings popup overlay.
     *
     * The popup contains sliders for main volume and SFX volume,
     * along with navigation buttons for different game states.
     */
    private void showSetting() {

        if(isOpen) return;
        isOpen = true;

        DropShadow shadow = new DropShadow();
        shadow.setRadius(10);
        shadow.setBlurType(BlurType.GAUSSIAN);
        shadow.setColor(Color.rgb(255,255,255,0.3));

        overlay = new StackPane();

        Slider MainvolumeSlider = new Slider(0, 100, GameLogic.getMusicVolume());

        MainvolumeSlider.setMinSize(500,30);
        MainvolumeSlider.setMaxSize(500,30);
        MainvolumeSlider.setPrefSize(500,30);

        MainvolumeSlider.setMajorTickUnit(0.25);

        Slider SFXvolumeSlider = new Slider(0, 100, GameLogic.getSFXVolume());

        SFXvolumeSlider.setMinSize(500,30);
        SFXvolumeSlider.setMaxSize(500,30);
        SFXvolumeSlider.setPrefSize(500,30);
        SFXvolumeSlider.setMajorTickUnit(0.25);


        MainvolumeSlider.skinProperty().addListener((obs, oldSkin, newSkin) -> {
            if (newSkin != null) {
                Node track = MainvolumeSlider.lookup(".track");
                Node thumb = MainvolumeSlider.lookup(".thumb");
                if (track != null) {
                    track.setStyle(
                            "-fx-background-color: black, white;" +
                                    "-fx-background-radius: 5;" +
                                    "-fx-padding: 3px;"
                    );
                }
                if (thumb != null) {
                    thumb.setStyle(
                            "-fx-background-color: white, rgb(57, 44, 62);" +
                                    "-fx-background-insets: 0, 4;" +
                                    "-fx-background-radius: 50;" +
                                    "-fx-pref-width: 30px;" +
                                    "-fx-pref-height: 30px;"
                    );
                }
            }
        });

        SFXvolumeSlider.skinProperty().addListener((obs, oldSkin, newSkin) -> {
            if (newSkin != null) {
                Node track = SFXvolumeSlider.lookup(".track");
                Node thumb = SFXvolumeSlider.lookup(".thumb");
                if (track != null) {
                    track.setStyle(
                            "-fx-background-color: black, white;" +
                                    "-fx-background-radius: 5;" +
                                    "-fx-padding: 3px;"
                    );
                }
                if (thumb != null) {
                    thumb.setStyle(
                            "-fx-background-color: white, rgb(57, 44, 62);" +
                                    "-fx-background-insets: 0, 4;" +
                                    "-fx-background-radius: 50;" +
                                    "-fx-pref-width: 30px;" +
                                    "-fx-pref-height: 30px;"
                    );
                }
            }
        });



        OutlineTextImage setting = new OutlineTextImage("setting",'C',30);
        setting.setDropShadow(shadow);
        VBox.setMargin(setting,new Insets(100,0,0,0));

        OutlineTextImage mvolume = new OutlineTextImage("Main Volume",'C',25);
        mvolume.setDropShadow(shadow);
        OutlineTextImage sfxvolume = new OutlineTextImage("SFX Volume",'C',25);
        sfxvolume.setDropShadow(shadow);

        OutlineTextImage intmv = new OutlineTextImage(GameLogic.getMusicVolume()+"",'M',25);
        intmv.setDropShadow(shadow);
        intmv.setPadding(new Insets(0,0,0,-35));
        OutlineTextImage intsfxv = new OutlineTextImage(GameLogic.getSFXVolume()+"",'M',25);
        intsfxv.setDropShadow(shadow);
        intsfxv.setPadding(new Insets(0,0,0,-60));

        HBox volumBGSetting = new HBox(mvolume,MainvolumeSlider,intmv);
        volumBGSetting.setMaxHeight(200);
        volumBGSetting.setSpacing(40);
        volumBGSetting.setPadding(new Insets(0,50,0,100));

        HBox volumSFXSetting = new HBox(sfxvolume,SFXvolumeSlider,intsfxv);
        volumSFXSetting.setMaxHeight(200);
        volumSFXSetting.setSpacing(55);
        volumSFXSetting.setPadding(new Insets(0,50,0,100));

        GridPane BtnPane = new GridPane();
        BtnPane.setAlignment(Pos.CENTER_RIGHT);

        BtnPane.setHgap(30);
        BtnPane.setVgap(30);

        MainvolumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {

            GameLogic.setMusicVolume(newVal.intValue());
            intmv.setText(GameLogic.getMusicVolume() + "");
            MediaPlayer.getInstance().updateBGMVolume();
        });

        SFXvolumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            GameLogic.setSFXVolume(newVal.intValue());
            intsfxv.setText(GameLogic.getSFXVolume() + "");
            MediaPlayer.getInstance().updateSFXVolume();
        });


//        NavSettingBtn play = new NavSettingBtn(GameState.INGAME,"PLAY",this);
//        play.setInset(new Insets(24,0,0,82));

        NavSettingButton selectPets = new NavSettingButton(GameState.SELECT_PET, "Pets",this);
        selectPets.setInset(new Insets(24,0,0,86));

        NavSettingButton selectChar = new NavSettingButton(GameState.SELECT_CHAR, "Cookies",this);
        selectChar.setInset(new Insets(24,0,0,62));

        NavSettingButton menu = new NavSettingButton(GameState.INTRO,"Menu",this);
        menu.setInset(new Insets(24,0,0,79));

        NavSettingButton leave = new NavSettingButton(null,"Leave",this);
        leave.setInset(new Insets(24,0,0,80));

        NavSettingButton resumeBtn = new NavSettingButton(null,"Resume",this);
        resumeBtn.setInset(new Insets(24,0,0,62));



        //deleteThis(play);
        deleteThis(selectPets);
        deleteThis(selectChar);
        deleteThis(menu);
        deleteThis(leave);
        runItBack(resumeBtn);


        BtnPane.add(menu, 0, 0);         BtnPane.add(resumeBtn, 1, 0);
        BtnPane.add(selectPets, 0, 1);   BtnPane.add(leave, 1, 1);
        BtnPane.add(selectChar, 0, 2);


        VBox popupBox = new VBox(setting,volumBGSetting,volumSFXSetting,BtnPane);
        popupBox.setSpacing(50);
        StackPane.setAlignment(popupBox, Pos.CENTER_LEFT);
        popupBox.setMaxWidth(960);

        ImageView bg = Asset.createImageView("SettingBG",1,0);
        bg.fitHeightProperty().bind(root.heightProperty());
        StackPane.setAlignment(bg,Pos.CENTER_LEFT);

        overlay.getChildren().addAll(bg,popupBox);
        root.getChildren().addAll(overlay);
    }

    /**
     * Wraps the button action and removes the settings popup
     * overlay after the button is clicked.
     *
     * @param button navigation button associated with the popup
     */
    private void deleteThis(BaseButton button) {

        EventHandler<ActionEvent> oldAction = button.getOnAction();

        button.setOnAction(e -> {

            if (oldAction != null) {
            oldAction.handle(e);
            }

            if (((NavSettingButton)button).getSwitchState() == null ){

                if (Objects.equals(((NavSettingButton) button).getTxt(), "Leave")) {
                    System.exit(0);
                }

                root.getChildren().remove(overlay);
                return;
            }

            if(((NavSettingButton) button).getSwitchState() == GameLogic.getGameState()) {
                root.getChildren().remove(overlay);
                return;
            }


            root.getChildren().remove(overlay);
        });
    }

    /**
     * Handles the resume action for the resume button.
     *
     * If the game is currently running, the gameplay scene
     * will resume and the settings popup will be removed.
     *
     * @param button resume button used to trigger this action
     */
    private void runItBack(BaseButton button){

        EventHandler<ActionEvent> oldAction = button.getOnAction();

        button.setOnAction(e -> {

            if (oldAction != null) {
                oldAction.handle(e);
            }

        if(GameLogic.getGameState().equals(GameState.IN_GAME)){
            if (!GameLogic.getGameroot().getChildren().isEmpty()) {
                Node child = GameLogic.getGameroot().getChildren().getFirst();

                if (child instanceof GameplayScene) {
                    GameplayScene inGameScene = (GameplayScene) child;
                    inGameScene.resumeGameByBool();
                }
            }
        }
            root.getChildren().remove(overlay);

        });
    }

    /**
     * Returns whether the settings popup is currently open.
     *
     * @return true if the popup is open, otherwise false
     */
    public Boolean getOpen() {
        return isOpen;
    }

    /**
     * Sets the open state of the settings popup.
     *
     * @param open new open state of the popup
     */
    public void setOpen(Boolean open) {
        isOpen = open;
    }
}
