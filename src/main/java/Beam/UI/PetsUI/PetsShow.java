package Beam.UI.PetsUI;

import Beam.CharactorData;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import Beam.Asset;
import Beam.Button.SelectPBtn;

public class PetsShow extends HBox {
    public PetsShow(Region spacer,SelectPBtn ins1,SelectPBtn ins2,SelectPBtn ins3,SelectPBtn ins4){

        ins1 =new SelectPBtn(CharactorData.MOJINIGA.getBtnView(),
                CharactorData.MOJINIGA.getView().getImage(),
                CharactorData.MOJINIGA.getBg().getImage(),
                CharactorData.MOJINIGA.getName(),
                CharactorData.MOJINIGA.getDesc(),28,50);

        ins2 = new SelectPBtn(CharactorData.KANGFUNIGA.getBtnView(),
                CharactorData.KANGFUNIGA.getView().getImage(),
                CharactorData.KANGFUNIGA.getBg().getImage(),
                CharactorData.KANGFUNIGA.getName(),
                CharactorData.KANGFUNIGA.getDesc(), 28,50);

        ins3 = new SelectPBtn(CharactorData.PANDAFANG.getBtnView(),
                CharactorData.PANDAFANG.getView().getImage(),
                CharactorData.PANDAFANG.getBg().getImage(),
                CharactorData.PANDAFANG.getName(),
                CharactorData.PANDAFANG.getDesc(), 28,50);

        ins4 = new SelectPBtn(CharactorData.LOCKING.getBtnView(),
                CharactorData.LOCKING.getView().getImage(),
                CharactorData.LOCKING.getBg().getImage(),
                CharactorData.LOCKING.getName(),
                CharactorData.LOCKING.getDesc(), 28,50);

        getChildren().addAll(spacer, ins1, ins2, ins3, ins4);
        setSpacing(-15);
        setPadding(new Insets(0,10,0,0));

    }
}
