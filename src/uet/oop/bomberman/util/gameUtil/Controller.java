/******************************************************************************
 *
 *  The data type for the game input control.
 *
 ******************************************************************************/

package uet.oop.bomberman.util.gameUtil;

/**
 * The {@code Controller} class is the data type for hearing the input event.
 * <p>
 * @author Phu Quoc Trung
 * @author Tran Thuy Duong
 */

import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.Main;
import uet.oop.bomberman.entities.character.Bomber;

public class Controller {
    public static boolean[] direction = new boolean[5];
    public static boolean layBomb;

    public Controller() {
        direction[Bomber.directionNone] = true;
        for (int i = 1; i <= 4; ++i) {
            direction[i] = false;
        }
        layBomb = false;
    }

    public void listen(KeyEvent event) {
        switch (event.getCode()) {
            case ESCAPE:
                Main.setPlayingStatus(2, "pause");
                break;
            case SPACE:
                layBomb = true;
                break;
            case UP:
                direction[Bomber.directionUp] = true;
                break;
            case DOWN:
                direction[Bomber.directionDown] = true;
                break;
            case LEFT:
                direction[Bomber.directionLeft] = true;
                break;
            case RIGHT:
                direction[Bomber.directionRight] = true;
                break;
            default:
                direction[Bomber.directionNone] = false;
                break;
        }
    }

    public void release(KeyEvent event) {
        switch (event.getCode()) {
            case UP:
                direction[Bomber.directionUp] = false;
                break;
            case DOWN:
                direction[Bomber.directionDown] = false;
                break;
            case LEFT:
                direction[Bomber.directionLeft] = false;
                break;
            case RIGHT:
                direction[Bomber.directionRight] = false;
                break;
            default:
                direction[Bomber.directionNone] = true;
                break;
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
