package uet.oop.bomberman;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

public class Menu {
    private Scene scene;
    Button startButton = new Button("START");
    Button quitButton = new Button("QUIT");

    public Menu() {
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);

        //startButton.setBackground(null);
        startButton.setPrefSize(100, 60);
        startButton.setStyle("-fx-font-size: 20");

        quitButton.setPrefSize(100, 60);
        quitButton.setStyle("-fx-font-size: 20");

        root.getChildren().addAll(startButton, quitButton);

        scene = new Scene(root, Main.initialSceneWidth, Main.initialSceneHeight);
    }

    public Scene getScene() {
        return this.scene;
    }

    public void update() {
        startButton.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                Main.switchPlayingStatus();
            }
        });
        quitButton.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                System.exit(0);
            }
        });
    }
}
