package supakorn.Animation;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import static javafx.application.Application.launch;
import static supakorn.Asset.BobaCookie;

public class Tester extends Application {

    private Scene scene;
    private Pane root;

    @Override
    public void start(Stage stage) throws Exception {
        root = new StackPane();
        root.setPrefSize(800, 600);
        scene = new Scene(root);

        Animate Tester = new Animate(BobaCookie.getImage(),6,4,400,400);

        root.getChildren().add(Tester);
        stage.setTitle("CEDT Cookie Run -main");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
