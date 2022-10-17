/******************************************************************************
 *
 *  Main class to run the game.
 *
 ******************************************************************************/

package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import uet.oop.bomberman.display.*;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Timer;
import java.util.TimerTask;


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

    /**
     * Thuộc tính lưu trạng thái hiện tại của game.
     * status = 0: Home scene
     * status = 1: Playing
     * status = 2: Pause scene
     * status = 3: Level name
     * status = 4: Game over
     * status = 5: Leaderboard
     */
    public static int status;

    public static DisplayScene[] scenes = new DisplayScene[6];
    public static final Font FONT = Font.loadFont("file:res/font/font.ttf", 30);

    public static void main(String[] args) {
        launch(Main.class);
    }

    @Override
    public void start(Stage stage) {
        scenes[0] = new HomeScene();
        scenes[1] = new BombermanGame();
        scenes[2] = new PauseScene();
        scenes[3] = new FixedScene("Stage");
        scenes[4] = new FixedScene("GAME OVER");
        scenes[5] = new LeaderBoard();
        status = 0;
        ((HomeScene) scenes[0]).setCanContinue(false);

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

        stage.setOnCloseRequest(t -> {
            Platform.exit();
            System.exit(0);
        });
    }

    public Scene chooseScene() {
        return scenes[status].getScene();
    }

    public void update() {
        scenes[status].update();
    }

    public void render() {
        scenes[status].render();
    }

    public static void setPlayingStatus(int newStatus, String mes) {
        switch (newStatus) {
            case 0:
                if (mes.equals("return")) {
                    BombermanGame.stopBGM();
                    ((HomeScene) scenes[0]).setCanContinue(true);
                }
                break;
            case 1:
                if (mes.equals("continue")) {
                    BombermanGame.startBGM();
                    status = newStatus;
                    return;
                }
                break;
            case 3: // mes == "STAGE " + level
                scenes[3] = new FixedScene(mes);
                (new Timer()).schedule(new TimerTask() {
                    @Override
                    public void run() {
                        status = 1;
                    }
                }, 1000L);
                break;
            case 4: // mes = "game over"
                ((HomeScene) scenes[0]).setCanContinue(false);
                (new Timer()).schedule(new TimerTask() {
                    @Override
                    public void run() {
                        status = 0;
                    }
                }, 1000L);
                break;
        }
        status = newStatus;
        scenes[status].reset();
    }
}