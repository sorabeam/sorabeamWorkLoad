package Beam.UI.CookiesUI;

import Beam.Button.BaseButton;
import javafx.scene.layout.GridPane;
import Beam.Button.CharacterButton;

public class  GridDisplay extends GridPane {

    public GridDisplay(CharacterButton B1, CharacterButton B2, CharacterButton B3, BaseButton B4)
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