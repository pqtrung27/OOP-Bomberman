package uet.oop.bomberman.display;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import uet.oop.bomberman.Main;
import uet.oop.bomberman.entities.Option;

public abstract class Menu extends DisplayScene {
    protected int minPos = 0;
    protected int currentItem = 0;
    protected Option[] menuItems;
    protected Text text;
    protected VBox root;

    public Menu() {
        root = new VBox();
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);
        root.setBackground(new Background(new BackgroundFill(Color.BLACK,
                CornerRadii.EMPTY,
                Insets.EMPTY)));

        text = new Text("SCENE NAME");
        text.setFont(Main.FONT);
        text.setStyle("-fx-font-size: 40");
        text.setFill(Color.YELLOW);

        scene = new Scene(root, Main.initialSceneWidth, Main.initialSceneHeight);
    }

    @Override
    public void reset() {
        startBGM();
        menuItems[currentItem].setActive(false);
        currentItem = minPos;
        menuItems[currentItem].setActive(true);
    }

    @Override
    public void update() {
        scene.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case UP:
                    if (currentItem > minPos) {
                        menuItems[currentItem].setActive(false);
                        menuItems[--currentItem].setActive(true);
                    }
                    break;
                case DOWN:
                    if (currentItem < menuItems.length - 1) {
                        menuItems[currentItem].setActive(false);
                        menuItems[++currentItem].setActive(true);
                    }
                    break;
                case ENTER:
                    menuItems[currentItem].activate();
                    break;
            }
        });
    }
}
