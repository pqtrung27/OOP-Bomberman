/******************************************************************************
 *
 *  Main class to run the game.
 *
 ******************************************************************************/

package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import uet.oop.bomberman.graphics.Sprite;


/**
 * The {@code BombermanGame} class is a data type for start and loop the
 * bomber man game.
 * <p>
 * This implementation uses JavaFx entirely to render screen and entity.
 *
 * @author Phu Quoc Trung
 * @author Tran Thuy Duong
 */

public class Main extends Application {

    public static final int WIDTH = 20;
    public static final int HEIGHT = 13;

    public static final double initialSceneWidth = WIDTH * Sprite.SCALED_SIZE;

    public static final double initialSceneHeight = HEIGHT * Sprite.SCALED_SIZE;

    public static boolean playing = false;

    public static Menu menu;
    public static BombermanGame game;
    public static final Font FONT = Font.font("res/font/font.ttf", FontWeight.BOLD, 30);

    public static void main(String[] args) {
        menu = new Menu();
        game = new BombermanGame();
        Application.launch(Main.class);
    }

    @Override
    public void start(Stage stage) {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                stage.setScene(chooseScene());
                update();
                render();
            }
        };
        timer.start();

        stage.show();

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
    }

    public Scene chooseScene() {
        if (playing) {
            return game.getScene();
        }
        return menu.getScene();
    }

    public void update() {
        // System.out.println(playing);
        if (playing) {
            game.update();
        } else {
            menu.update();
        }
    }

    public void render() {
        if (playing) {
            game.render();
        }
    }

    public static void switchPlayingStatus() {
        if (playing) {
            playing = false;
            menu.reset();
        } else {
            playing = true;
            game.reset();
        }
    }
}