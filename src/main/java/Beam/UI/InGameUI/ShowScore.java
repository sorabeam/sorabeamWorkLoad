package Beam.UI.InGameUI;

import Got.GameLogic.GameLogic;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import Beam.Asset;
import Beam.Image.OutlineTextImage;

public class ShowScore extends StackPane {
    int iscore;
    ImageView fadeBg = Asset.createImageView("ScoreBG",100,0);
    OutlineTextImage score = new OutlineTextImage("0 Score",'M',30);

    public ShowScore(){

        setMaxSize(1,1);
        setAlignment(Pos.CENTER_RIGHT);
        score.setMaxSize(1,1);
        score.setPadding(new Insets(-5,30,0,0));

        GameLogic.scoreProperty().addListener((obs, oldVal, newVal) -> {
            score.setText(newVal + " Score");
        });

        // set ค่าเริ่มต้น
        score.setText(GameLogic.getScore() + " Score");

        getChildren().addAll(
                fadeBg,
                score
        );
    }

    public void setScore(int nscore){
        iscore = nscore;
        score.setText(GameLogic.getScore() + " Score");
    }
}
