package Beam.UI.GameOverUI;

import Beam.Asset;
import Beam.Button.NavigationButton;
import Beam.CharactorData;
import Beam.Image.OutlineTextImage;
import GameLogic.GameLogic;
import GameLogic.GameState;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class DVDShow extends StackPane {

    public DVDShow(){
        StackPane wrapper = new StackPane();
        wrapper.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        ImageView statsImg = Asset.createImageView("statsBox", 450, 0);
        VBox statVBox = new VBox();
        statVBox.setMaxHeight(50);
        StackPane.setAlignment(statVBox, Pos.TOP_CENTER);
        StackPane.setMargin(statVBox,new Insets(170,0,0,0));
        OutlineTextImage score = new OutlineTextImage(GameLogic.getScore()+" score!",'C',24);

        int bestscore = Math.max(CharactorData.getCurrent_Cookie().get_Score(),GameLogic.getScore());
        if(GameLogic.getScore() > CharactorData.getCurrent_Cookie().get_Score()){
            CharactorData.getCurrent_Cookie().setScore(GameLogic.getScore());
        }

        OutlineTextImage bestScore = new OutlineTextImage("Highest " + bestscore + " score!",'C',16);
        wrapper.setMaxHeight(500);
        wrapper.setMaxWidth(500);

        statVBox.setAlignment(Pos.CENTER);

        statVBox.getChildren().addAll(score, bestScore);

        wrapper.setPadding(new Insets(16));
        wrapper.getChildren().addAll(statsImg, statVBox);

        StackPane wrapper2 = new StackPane();
        wrapper2.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        ImageView curCharBox = Asset.createImageView("charBox", 300, 0);

        ImageView img = Asset.createImageView(CharactorData.getCurrent_Cookie().get_Bid(),180,0);
        NavigationButton show = new NavigationButton(img, GameState.SELECTCHAR);
        StackPane.setMargin(show,new Insets(-50,-60,0,0));
        wrapper2.getChildren().addAll(curCharBox,show);
        StackPane.setMargin(wrapper2,new Insets(0,475,0,0));
        StackPane.setAlignment(wrapper,Pos.CENTER_RIGHT);

        setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        getChildren().addAll(wrapper2,wrapper);
    }
}
