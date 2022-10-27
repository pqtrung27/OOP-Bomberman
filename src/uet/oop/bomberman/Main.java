/******************************************************************************
 *
 *  Main class to run the game.
 *
 ******************************************************************************/

package uet.oop.bomberman;

/**
 * The {@code Main} class is a data type for start the
 * bomber man game.
 * <p>
 * This implementation uses JavaFx entirely to render screen and entity.
 *
 * @author Phu Quoc Trung
 * @author Tran Thuy Duong
 */

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import uet.oop.bomberman.display.*;
import uet.oop.bomberman.display.scene.*;
import uet.oop.bomberman.display.scene.menu.*;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;

import java.util.Timer;
import java.util.TimerTask;

public class Main extends Application {

    public static final int WIDTH = 20;
    public static final int HEIGHT = 14;

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
     * status = 6: New high score
     * status = 7: Instruction
     */
    public static int status;

    public static DisplayScene[] scenes = new DisplayScene[8];
    public static final Font FONT = Font.loadFont(Main.class.getResource("/font/font.ttf").toString(), 30);

    public static void main(String[] args) {
        launch(Main.class);
    }



    @Override
    public void start(Stage stage) {
        stage.setResizable(false);
        scenes[0] = new HomeScene();
        scenes[1] = new BombermanGame();
        scenes[2] = new PauseScene();
        scenes[3] = new FixedScene("Stage");
        scenes[4] = new FixedScene("GAME OVER");
        scenes[5] = new LeaderBoard();
        scenes[6] = new NewHighScore();
        scenes[7] = new Instruction();
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
        scenes[status].close();
        switch (newStatus) {
            case 0:
                if (mes.equals("return")) {
                    ((HomeScene) scenes[0]).setCanContinue(true);
                } else if (mes.equals("game over")) {
                    ((HomeScene) scenes[0]).setCanContinue(false);
                }
                break;
            case 1:
                if (mes.equals("continue")) {
                    status = newStatus;
                    return;
                }
                break;
            case 3: // mes == "STAGE " + level
                scenes[3] = new FixedScene(mes);
                Sound.stageStartBGM.play();
                (new Timer()).schedule(new TimerTask() {
                    @Override
                    public void run() {
                        status = 1;
                        Sound.stageStartBGM.stop();
                    }
                }, 2600L);
                break;
            case 4: // mes = "game over"
                Sound.gameOverBGM.play();
                (new Timer()).schedule(new TimerTask() {
                    @Override
                    public void run() {
                        setPlayingStatus(0, "game over");
                        Sound.gameOverBGM.stop();
                    }
                }, 6000L);
                break;
        }
        status = newStatus;
        scenes[status].reset();
    }
}


/******************************************************************************
 *  Copyright 2022, Phu Quoc Trung and Tran Thuy Duong.
 *
 *  This file is part of OOP-Bomberman, which accompanies the course
 *
 *      INT2204 of UET-VNU
 *
 *  OOP-Bomberman is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  OOP-Bomberman is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  See http://www.gnu.org/licenses.
 ******************************************************************************/