package Main.Scene;

import Main.Button.PetsButton;
import Main.UI.PetsUI.PetSelectionBG;
import Main.UI.PetsUI.PetSelectionBtn;
import Main.UI.PetsUI.SelectingView;
import Main.UI.PetsUI.SettingZone;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import Main.CharacterData;
import Main.Image.OutlineTextImage;

/**
 * Scene used for selecting pets.
 *
 * This scene displays available pets, their preview image,
 * background image, name, and description. Selecting a pet
 * updates the current pet stored in {@link CharacterData}.
 */
public class PetsSelectionScene extends BaseScene {

    /**
     * Image showing the selected pet.
     */
    ImageView showImage;

    /**
     * Background image for the selected pet preview.
     */
    ImageView backGroundImage;

    /**
     * UI text displaying the pet name.
     */
    OutlineTextImage name;

    /**
     * UI text displaying the pet description.
     */
    OutlineTextImage description;

    /**
     * Buttons representing selectable pets.
     */
    PetsButton ins1, ins2, ins3, ins4;

    /**
     * Default description text of the currently selected pet.
     */
    private String txt = CharacterData.getCurrent_Pet().getDescription();

    /**
     * Creates the pet selection scene and initializes all UI
     * components including the pet buttons, preview area,
     * settings section, and layout containers.
     */
    public PetsSelectionScene(){
        super();

        ins1 = new PetsButton(CharacterData.MOJI,28,50);
        ins2 = new PetsButton(CharacterData.Chilly,28,50);
        ins3 = new PetsButton(CharacterData.SALAD,28,50);
        ins4 = new PetsButton(CharacterData.LOCKING,28,50);

        HBox box = new HBox(spacer('H'),ins1, ins2, ins3, ins4);
        box.setSpacing(-15);
        box.setPadding(new Insets(0,0,0,0));

        PetSelectionBtn petSelectionBtn = new PetSelectionBtn(spacer('H'));
        SettingZone settingZone = new SettingZone(root,spacer('H'));
        SelectingView selectingView = new SelectingView(root,this);

        VBox leftVBox = new VBox(selectingView);
        VBox rightVBox = new VBox(settingZone,box,petSelectionBtn);
        VBox.setMargin(settingZone,new Insets(20,20,0,0));

        HBox mainHBox = new HBox(leftVBox,spacer('H'),rightVBox);

        HBox.setMargin(rightVBox,new Insets(8,-12,0,0));

        root.getChildren().addAll(
                new PetSelectionBG(),
                mainHBox
        );

        enableSwap(ins1);
        enableSwap(ins2);
        enableSwap(ins3);

        leftVBox.setPadding(new Insets(0,0,0,50));
        leftVBox.setAlignment(Pos.CENTER);
        rightVBox.setSpacing(50);
    }

    /**
     * Returns the preview image of the selected pet.
     */
    public ImageView getShowImage() { return showImage; }

    /**
     * Sets the preview image of the selected pet.
     */
    public void setShowImage(ImageView showImage) { this.showImage = showImage; }

    /**
     * Returns the background image of the selected pet.
     */
    public ImageView getBackGroundImage() { return backGroundImage; }

    /**
     * Sets the background image of the selected pet.
     */
    public void setBackGroundImage(ImageView backGroundImage) { this.backGroundImage = backGroundImage; }

    /**
     * Returns the pet name text element.
     */
    public OutlineTextImage getName() { return name; }

    /**
     * Sets the pet name text element.
     */
    public void setName(OutlineTextImage name) { this.name = name; }

    /**
     * Returns the pet description text element.
     */
    public OutlineTextImage getDescription() { return description; }

    /**
     * Sets the pet description text element.
     */
    public void setDescription(OutlineTextImage description) { this.description = description; }

    /**
     * Returns the first pet button.
     */
    public PetsButton getIns1() { return ins1; }

    /**
     * Sets the first pet button.
     */
    public void setIns1(PetsButton ins1) { this.ins1 = ins1; }

    /**
     * Returns the second pet button.
     */
    public PetsButton getIns2() { return ins2; }

    /**
     * Sets the second pet button.
     */
    public void setIns2(PetsButton ins2) { this.ins2 = ins2; }

    /**
     * Returns the third pet button.
     */
    public PetsButton getIns3() { return ins3; }

    /**
     * Sets the third pet button.
     */
    public void setIns3(PetsButton ins3) { this.ins3 = ins3; }

    /**
     * Returns the fourth pet button.
     */
    public PetsButton getIns4() { return ins4; }

    /**
     * Sets the fourth pet button.
     */
    public void setIns4(PetsButton ins4) { this.ins4 = ins4; }

    /**
     * Returns the current pet description text.
     */
    public String getTxt() { return txt; }

    /**
     * Sets the pet description text.
     */
    public void setTxt(String txt) { this.txt = txt; }

    /**
     * Enables swapping the selected pet when a pet button is pressed.
     *
     * When the button is clicked, the selected pet is updated in
     * {@link CharacterData} and the preview UI elements are refreshed.
     *
     * @param button the pet selection button
     */
    private void enableSwap(PetsButton button) {

        var oldAction = button.getOnAction();

        button.setOnAction(e -> {

            if (oldAction != null) {
                oldAction.handle(e);
            }

            CharacterData.setCurrent_Pet(button.getPet());

            showImage.setImage(button.getPetImage());
            backGroundImage.setImage(button.getBackGround());
            name.setText(button.getName());
            description.setText(button.getDescription());

        });

    }
}