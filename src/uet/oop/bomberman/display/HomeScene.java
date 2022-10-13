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

public class HomeScene extends Menu {
    public HomeScene() {
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setBackground(new Background(new BackgroundFill(Color.BLACK,
                CornerRadii.EMPTY,
                Insets.EMPTY)));

        menuItems = new Option[6];

        menuItems[0] = new Option("CONTINUE");
        menuItems[0].setOnActivate(() -> Main.setPlayingStatus(1, "continue"));

        menuItems[1] = new Option("START");
        menuItems[1].setOnActivate(() -> Main.setPlayingStatus(1, "start"));

        menuItems[2] = new Option("INSTRUCTION");

        menuItems[3] = new Option("LEADERBOARD");

        menuItems[4] = new Option("ABOUT");

        menuItems[5] = new Option("QUIT");
        menuItems[5].setOnActivate(() -> System.exit(0));

        currentItem = 0;
        menuItems[currentItem].setActive(true);

        root.getChildren().addAll(menuItems);
        scene = new Scene(root, Main.initialSceneWidth, Main.initialSceneHeight);
    }

    public void setCanContinue(boolean canContinue) {
        minPos = (canContinue ? 0 : 1);
        reset();
        menuItems[0].setVisible(canContinue);
    }
}
