package supakorn.Button;

import javafx.scene.image.ImageView;

public class FavoriteButton extends BaseButton{

    public FavoriteButton(ImageView Img, float FitHeight, float FitWidth) {
        super(Img, FitHeight, FitWidth);
    }

    @Override
    public void handleClick() {
        super.handleClick();
        //Set FavBoolean = !FavBoolean
    }
}
