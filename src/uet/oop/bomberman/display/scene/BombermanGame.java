/******************************************************************************
 *  Dependency: DisplaceScene.java
 *
 *  The class to run the game scene.
 *
 ******************************************************************************/

package uet.oop.bomberman.display.scene;

/**
 * The {@code BombermanGame} class extend DisplayScene class to create
 * the main game scene.
 * <p>
 * This implementation uses JavaFx entirely
 * <p>
 * @author Phu Quoc Trung
 * @author Tran Thuy Duong
 */

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import uet.oop.bomberman.Main;
import uet.oop.bomberman.display.DisplayScene;
import uet.oop.bomberman.display.scene.menu.LeaderBoard;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.sound.Sound;
import uet.oop.bomberman.util.gameUtil.Board;
import uet.oop.bomberman.util.gameUtil.Controller;

import java.util.Timer;
import java.util.TimerTask;


public class BombermanGame extends DisplayScene {
    private GraphicsContext gc;
    private Canvas canvas;
    private int level = 0;
    public static int score = 0;
    public static int lives = 0;
    public static final Font INFOFONT = Font.loadFont(BombermanGame.class.getResource("/font/RetroGaming.ttf").toString(), 30);

    private Controller controller = new Controller();

    public BombermanGame() {
        // Tao Canvas
        canvas = new Canvas(Main.initialSceneWidth, Main.initialSceneHeight);
        gc = canvas.getGraphicsContext2D();
        gc.setFont(INFOFONT);

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        scene = new Scene(root, Main.initialSceneWidth, Main.initialSceneHeight);

        //tao input event handler.
        scene.setOnKeyPressed(controller::listen);
        scene.setOnKeyReleased(controller::release);

        this.BGM = Sound.MainBGM;
    }

    @Override
    public void reset() {
        level = 0;
        score = 0;
        lives = 2;
        Bomber.reset();
        loadNextLevel();
    }

    private void loadNextLevel() {
        controller = new Controller();
        //Bomb.reset();
        ++level;
        Main.setPlayingStatus(3, "STAGE " + level);
        Entity.board = new Board(level);
    }

    @Override
    public void update() {
        if (Sound.stageClearBgm.getStatus().equals(MediaPlayer.Status.PLAYING)) {
            // stopBGM();
            return;
        }
        // startBGM();
        Entity.board.update();
        if (Entity.board.endGame) {
            stopBGM();
            --lives;
            if (lives > 0) {
                --level;
                loadNextLevel();
                return;
            }
            if (LeaderBoard.checkHighScore(score)) {
                // System.out.println("New high score!");
                Main.setPlayingStatus(6, "new high score");
            }
            else Main.setPlayingStatus(4, "game over");
        }
        if (Entity.board.nextLevel) {
            ++lives;
            stopBGM();
            Sound.stageClearBgm.play();
            (new Timer()).schedule(new TimerTask() {
                @Override
                public void run() {
                    Sound.stageClearBgm.stop();
                    loadNextLevel();
                }
            }, 2500);
        }
    }

    @Override
    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        gc.setFill(Color.GRAY);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        Entity.board.render(gc);

        gc.setFill(Color.WHITE);
        gc.fillText("LEFT: " + lives, 750, 35);
        gc.fillText(String.valueOf(score), 450, 35);
    }

    public static void addScore(int value) {
        score += value;
    }

    public static int getScore() {
        return score;
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