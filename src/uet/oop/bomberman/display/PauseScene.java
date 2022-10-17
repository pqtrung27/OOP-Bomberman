package uet.oop.bomberman.display;

import uet.oop.bomberman.Main;
import uet.oop.bomberman.entities.Option;

public class PauseScene extends Menu {
    public PauseScene() {
        text.setText("GAME PAUSED");
        text.setStyle("-fx-font-size: 40");

        menuItems = new Option[2];

        menuItems[0] = new Option("CONTINUE");
        menuItems[0].setOnActivate(() -> Main.setPlayingStatus(1, "continue"));

        menuItems[1] = new Option("RETURN");
        menuItems[1].setOnActivate(() -> {
            Main.setPlayingStatus(0, "return");
        });

        currentItem = 0;
        menuItems[currentItem].setActive(true);

        root.getChildren().add(text);
        root.getChildren().addAll(menuItems);
    }
}
