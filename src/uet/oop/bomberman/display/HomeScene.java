package uet.oop.bomberman.display;

import uet.oop.bomberman.Main;
import uet.oop.bomberman.entities.Option;

public class HomeScene extends Menu {
    public HomeScene() {
        text.setText("BOMBERMAN");
        text.setStyle("-fx-font-size: 80");

        menuItems = new Option[6];

        menuItems[0] = new Option("CONTINUE");
        menuItems[0].setOnActivate(() -> Main.setPlayingStatus(1, "continue"));

        menuItems[1] = new Option("START");
        menuItems[1].setOnActivate(() -> Main.setPlayingStatus(1, "start"));

        menuItems[2] = new Option("INSTRUCTION");

        menuItems[3] = new Option("LEADERBOARD");
        menuItems[3].setOnActivate(() -> Main.setPlayingStatus(5, "leaderboard"));

        menuItems[4] = new Option("ABOUT");

        menuItems[5] = new Option("QUIT");
        menuItems[5].setOnActivate(() -> System.exit(0));

        currentItem = 0;
        menuItems[currentItem].setActive(true);

        root.getChildren().add(text);
        root.getChildren().addAll(menuItems);
    }

    public void setCanContinue(boolean canContinue) {
        minPos = (canContinue ? 0 : 1);
        reset();
        menuItems[0].setVisible(canContinue);
    }
}
