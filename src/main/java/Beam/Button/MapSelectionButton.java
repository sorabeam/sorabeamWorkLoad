package Beam.Button;

import Beam.Asset;
import GameLogic.GameLogic;
import javafx.scene.image.ImageView;


public class MapSelectionButton extends BaseButton{
    private int mapNo;
    private MapPopupButton targetBtn;

    public MapSelectionButton(ImageView img , MapPopupButton targetBtn, int mapNo) {
        super(img);
        this.mapNo = mapNo;
        this.targetBtn = targetBtn;
    }

    @Override
    public void handleClick() {
        super.handleClick();

        GameLogic.setMap(mapNo);
        targetBtn.setImg(Asset.createImageView("MAP"+mapNo+"P",0,400));
    }
}
