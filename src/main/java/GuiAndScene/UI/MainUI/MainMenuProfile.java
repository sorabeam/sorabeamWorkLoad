package GuiAndScene.UI.MainUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import supakorn.Asset;
import supakorn.Button.FavBtn;
import supakorn.Image.OutlineText;

public class MainMenuProfile extends StackPane {

    public MainMenuProfile(String name, String score) {

        ImageView LillyBar = Asset.createImageView("LillyBar",0,400);
        ImageView LillyProfile = Asset.createImageView("LillyProfile",0,400);

        FavBtn CfavIco = new FavBtn();

        OutlineText CharactorName = new OutlineText(name,'C',30);
        StackPane.setAlignment(CharactorName, Pos.TOP_LEFT);
        CharactorName.setMaxHeight(1);
        CharactorName.setMaxWidth(1);

        OutlineText BestScore = new OutlineText("Best Score : " + score ,'M',20);
        StackPane.setAlignment(BestScore,Pos.TOP_LEFT);
        BestScore.setMaxHeight(1);
        BestScore.setMaxWidth(1);

        setMaxWidth(500);
        getChildren().addAll(LillyBar,LillyProfile,BestScore,CharactorName,CfavIco);

        // alignment / margin

        setAlignment(LillyBar,Pos.TOP_LEFT);
        setAlignment(LillyProfile,Pos.TOP_LEFT);
        setAlignment(CharactorName,Pos.TOP_LEFT);
        setAlignment(CfavIco,Pos.TOP_LEFT);
        setMargin(LillyBar,new Insets(20,0,0,20));
        setMargin(LillyProfile,new Insets(20,0,0,20));
        setMargin(CharactorName,new Insets(20 +  LillyProfile.getLayoutBounds().getHeight()/3.1,0,0,50));
        setMargin(BestScore,new Insets(30 + LillyProfile.getLayoutBounds().getHeight(),0,0,30));
        setMargin(CfavIco,new Insets(30 + LillyProfile.getLayoutBounds().getHeight(),0,0,20 + LillyBar.getLayoutBounds().getWidth() - 50));


    }
}