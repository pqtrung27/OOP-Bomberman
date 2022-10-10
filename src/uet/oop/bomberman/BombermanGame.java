/******************************************************************************
 *
 *  Main class to run the game.
 *
 ******************************************************************************/

package uet.oop.bomberman;

/**
 * The {@code BombermanGame} class is a data type for start and loop the
 * bomber man game.
 * <p>
 * This implementation uses JavaFx entirely to render screen and entity.
 *
 * @author Phu Quoc Trung
 * @author Tran Thuy Duong
 */

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import uet.oop.bomberman.util.Controller;

import java.io.File;

import java.io.FileNotFoundException;

public class BombermanGame {
    private GraphicsContext gc;
    private Canvas canvas;
    private Scene scene;
    MediaPlayer bgmPlayer;

    public BombermanGame() {
        // Tao Canvas
        canvas = new Canvas(Main.initialSceneWidth, Main.initialSceneHeight);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        scene = new Scene(root, Main.initialSceneWidth, Main.initialSceneHeight);

        //tao input event handler.
        Controller controller = new Controller();
        scene.setOnKeyPressed(controller::listen);
        scene.setOnKeyReleased(controller::release);

        createMap();

        //bay lac
        String bgmFile = "res/audio/MainBGM.mp3";
        Media bgm = new Media(new File(bgmFile).toURI().toString());
        bgmPlayer = new MediaPlayer(bgm);
    }

    public Scene getScene() {
        return this.scene;
    }

    public void createMap() {
        try {
            Entity.board.LoadLevel(2);
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found!!!");
        }
    }

    public void update() {
        bgmPlayer.play();
        Entity.board.update();
        if (Entity.board.endGame){
            Main.switchPlayingStatus();
            bgmPlayer.stop();
        }
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        Entity.board.render(gc);
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