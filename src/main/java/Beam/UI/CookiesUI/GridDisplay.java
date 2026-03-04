package Beam.UI.CookiesUI;

import Beam.Button.BaseButton;
import javafx.scene.layout.GridPane;
import Beam.Button.CDBtn;

public class  GridDisplay extends GridPane {

    public GridDisplay(CDBtn B1, CDBtn B2, CDBtn B3, BaseButton B4)
    {

        setMaxHeight(455);
        setMaxWidth(455);

        setHgap(15); // ระยะห่างแนวนอน
        setVgap(15); // ระยะห่างแนวตั้ง

        add(B1, 0, 0);
        add(B2, 1, 0);
        add(B3, 0, 1);
        add(B4, 1, 1);
    }
}