package supakorn.Image;

import javafx.scene.image.ImageView;

public class ModifyImage extends ImageView{

    public ModifyImage(ImageView img, double fitHeight, double fitWidth){
        super(img.getImage());

        if (fitHeight > 0) {
            super.setFitHeight(fitHeight);
        }
        if (fitWidth > 0) {
            super.setFitWidth(fitWidth);
        }
        super.setPreserveRatio(true);
    }

}
