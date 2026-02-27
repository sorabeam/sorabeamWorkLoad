package GuiAndScene.UI.PetsUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import supakorn.Asset;
import supakorn.Button.BaseButton;
import supakorn.Button.FavBtn;
import supakorn.Image.OutlineText;

import static javafx.geometry.Pos.*;

public class SelectingView extends StackPane {
    public SelectingView(){

        setPrefSize(550, 865);
        setMinSize(550, 550);
        setMaxSize(550, 865);

        Rectangle clip = new Rectangle();
        clip.widthProperty().bind(widthProperty());
        clip.heightProperty().bind(heightProperty());

        setClip(clip);

        ImageView Moji = Asset.createImageView("Moji",0,480);
        ImageView SelectingBg = Asset.createImageView("Selecting_Boba",0,350);
        BaseButton DeployBtn = new BaseButton( Asset.createImageView("DeplayBtn",0,330));

        SelectingBg.setPreserveRatio(false);
        SelectingBg.fitHeightProperty().bind(heightProperty());
        StackPane.setAlignment(SelectingBg,CENTER); //??????

        FavBtn fav = new FavBtn();
        fav.setHeight(40);
        StackPane.setMargin(fav, new Insets(0,0,0,20));

        OutlineText PName = new OutlineText("MojiNiga",'C',40);

        OutlineText description = new OutlineText(" is one of the five Ancient \n Heroes in Cookie Run: Kingdom. \n She was the final Ancient Cookie \n to become playable,  being \n released  in the Secrets of \n the Silver  Kingdom update",'M',20);
        description.setTextAlignment(TextAlignment.CENTER);
        StackPane favWrapper = new StackPane(fav);
        favWrapper.setMinSize(350, 80);
        favWrapper.setMaxSize(350, 80);

        favWrapper.setAlignment(TOP_LEFT);

        VBox vbox = new VBox(favWrapper,Moji,PName,DeployBtn,description);

        vbox.setAlignment(Pos.CENTER);
        setAlignment(vbox, Pos.CENTER);
        vbox.setSpacing(30);

        getChildren().addAll(SelectingBg, vbox);

    }
}

