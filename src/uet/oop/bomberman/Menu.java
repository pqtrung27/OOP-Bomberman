package uet.oop.bomberman;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

public class Menu {
    private Scene scene;
    Button startButton = new Button("START");

    public Menu() {
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);

        //startButton.setBackground(null);
        startButton.setPrefSize(100, 100);

        root.getChildren().add(startButton);

        scene = new Scene(root, Main.initialSceneWidth, Main.initialSceneHeight);
    }

    public Scene getScene() {
        return this.scene;
    }

    public boolean update() {
        startButton.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                    Main.switchPlayingStatus();
                }
            }
        });
        return false;
    }
}
