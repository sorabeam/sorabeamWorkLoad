package CleanCode.UI.InGameUI;

import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import supakorn.Image.OutlineText;

public class LastRecord extends StackPane {
    int topscore = 12656234;
    String formattedTS = String.format("%,d", topscore);
    String text = "Last Record\n"+formattedTS;
    OutlineText txt = new OutlineText(text,'C',30);

    public LastRecord(){
        getChildren().addAll(
                txt
        );

        txt.setMaxSize(100,100);
        txt.setColor(Color.web("#FFF59D"));
        setMaxSize(100,100);
        txt.setTextAlignment(TextAlignment.CENTER);
    }
}
