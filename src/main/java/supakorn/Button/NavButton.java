package supakorn.Button;

import javafx.scene.image.ImageView;

public class NavButton extends BaseButton{

    public NavButton(ImageView Img, float FitHeight, float FitWidth) {
        super(Img, FitHeight, FitWidth);
    }

    @Override
    public void handleClick() {
        super.handleClick();
        // ChangeScene
    }
}
