package GuiAndScene.UI.InGameUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import supakorn.Asset;

public class HpDisplayZone extends StackPane {

    int max_hp = 500;
    int current_hp = 0;
    double hpWidth;

    public HpDisplayZone(){
        ImageView hpBar = Asset.createImageView("HpBar",0,1000);
        ImageView hpPotion = Asset.createImageView("HpPotion",0,70);
        CalculateHpWidth();

        getChildren().addAll(
                hpBar,
                hpPotion
        );

        setMaxSize(200,10);
        setAlignment(Pos.TOP_LEFT);
        setMargin(hpPotion,new Insets(30,0,0,hpWidth-35));
        setMargin(hpBar,new Insets(50,0,0,0));
        //setBackground(new Background(new BackgroundFill(Color.LAVENDERBLUSH,null,null)));
    }

    public void CalculateHpWidth(){

         hpWidth = 1000.0 * current_hp / max_hp;

    }

    public void setCurrent_hp(int current_hp) {
        this.current_hp = current_hp;
        CalculateHpWidth();
    }
}
