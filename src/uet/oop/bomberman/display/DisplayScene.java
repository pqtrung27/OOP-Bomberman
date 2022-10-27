/******************************************************************************
 *  Dependency: DisplaceScene.java
 *
 *  The abstract class for all the Scene in this game.
 *
 ******************************************************************************/

package uet.oop.bomberman.display;

/**
 * The {@code DisplayScene}  class is the abstract
 * class for all the Scene in this game.
 * <p>
 * This implementation uses JavaFx entirely.
 * <p>
 *
 * @author Phu Quoc Trung
 * @author Tran Thuy Duong
 */

import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public abstract class DisplayScene {
    protected Scene scene;

    protected MediaPlayer BGM;

    public Scene getScene() {
        return this.scene;
    }

    public void update() {

    }

    public void render() {

    }

    public void reset() {
        startBGM();
    }

    public void close() {
        stopBGM();
    }

    public void startBGM() {
        if (BGM != null) {
            BGM.play();
            BGM.setOnEndOfMedia(() -> {
                BGM.seek(Duration.ZERO);
                BGM.play();
            });
        }
    }

    public void stopBGM() {
        if (BGM != null) {
            BGM.stop();
        }
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