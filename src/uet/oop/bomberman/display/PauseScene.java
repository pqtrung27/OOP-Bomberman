package uet.oop.bomberman.display;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import uet.oop.bomberman.Main;
import uet.oop.bomberman.entities.Option;

public class PauseScene extends Menu {
    public PauseScene() {
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setBackground(new Background(new BackgroundFill(Color.BLACK,
                CornerRadii.EMPTY,
                Insets.EMPTY)));

        menuItems = new Option[2];

        menuItems[0] = new Option("CONTINUE");
        menuItems[0].setOnActivate(() -> Main.setPlayingStatus(1, "continue"));

        menuItems[1] = new Option("RETURN");
        menuItems[1].setOnActivate(() -> {
            Main.setPlayingStatus(0, "return");
        });

        currentItem = 0;
        menuItems[currentItem].setActive(true);

        root.getChildren().addAll(menuItems);
        scene = new Scene(root, Main.initialSceneWidth, Main.initialSceneHeight);
    }
}
