package supakorn.Button;

import javafx.scene.image.ImageView;

public class PopUpButton extends BaseButton{

    public PopUpButton(ImageView Img, float FitHeight, float FitWidth) {
        super(Img, FitHeight, FitWidth);
    }

    @Override
    public void handleClick() {
        super.handleClick();
        // ChangeScene
    }
}
