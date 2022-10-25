/******************************************************************************
 *
 *  Main class to run the game.
 *
 ******************************************************************************/

package uet.oop.bomberman.display;

/**
 * The {@code BombermanGame} class is a data type for start and loop the
 * bomber man game.
 * <p>
 * This implementation uses JavaFx entirely to render screen and entity.
 *
 * @author Phu Quoc Trung
 * @author Tran Thuy Duong
 */

import com.sun.webkit.dom.EntityImpl;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import uet.oop.bomberman.Main;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.util.Board;
import uet.oop.bomberman.util.Controller;

import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;


public class BombermanGame extends DisplayScene {
    private GraphicsContext gc;
    private Canvas canvas;
    private int level = 0;
    public static int score = 0;
    public static int lives = 0;
    public static final Font INFOFONT = Font.loadFont("file:res/font/RetroGaming.ttf", 30);

    private Controller controller = new Controller();
    private MediaPlayer stageClearBgm;

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

        //bay lac
        String bgmFile = "/audio/MainBGM.mp3";
        Media bgm = new Media(getClass().getResource(bgmFile).toString());
        bgmPlayer = new MediaPlayer(bgm);

        stageClearBgm = new MediaPlayer(
                new Media(getClass().getResource("/audio/StageClear.mp3").toString())
        );
    }

    @Override
    public void reset() {
        level = 0;
        score = 0;
        lives = 2;
        Bomber.reset();
        loadNextLevel();
    }

    @Override
    public void close() {
        stopBGM();
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
        if (stageClearBgm.getStatus().equals(MediaPlayer.Status.PLAYING)) {
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
            stageClearBgm.play();
            (new Timer()).schedule(new TimerTask() {
                @Override
                public void run() {
                    stageClearBgm.stop();
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