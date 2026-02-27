package GuiAndScene.UI.InGameUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import supakorn.Asset;
import supakorn.Image.OutlineText;

public class ShowScore extends StackPane {
    int iscore;
    ImageView fadeBg = Asset.createImageView("ScoreBG",100,0);
    OutlineText score = new OutlineText("0 Score",'M',30);

    public ShowScore(){

        setMaxSize(1,1);
        setAlignment(Pos.CENTER_RIGHT);
        score.setMaxSize(1,1);
        score.setPadding(new Insets(-5,30,0,0));

        getChildren().addAll(
                fadeBg,
                score
        );
    }

    public void setScore(int nscore){
        iscore = nscore;
        score.setText(nscore + " Score");
    }
}
