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

public class FixedScene extends DisplayScene {
    public FixedScene(String mes) {
        Text text = new Text(mes);
        text.setFont(Main.FONT);
        text.setFill(Color.WHITE);

        VBox root = new VBox(text);
        root.setAlignment(Pos.CENTER);
        root.setBackground(new Background(new BackgroundFill(Color.BLACK,
                CornerRadii.EMPTY,
                Insets.EMPTY)));

        scene = new Scene(root, Main.initialSceneWidth, Main.initialSceneHeight);
    }
}
