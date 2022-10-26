/******************************************************************************
 *  Dependency: Menu.java.
 *
 *  The class for the paused scene.
 *
 ******************************************************************************/

package uet.oop.bomberman.display.scene.menu;

/**
 * The {@code PausedScene} class extend Menu class to create
 * the game paused scene.
 * <p>
 * This implementation uses JavaFx entirely.
 * <p>
 * @author Phu Quoc Trung
 * @author Tran Thuy Duong
 */

import uet.oop.bomberman.Main;
import uet.oop.bomberman.display.scene.MenuScene;

public class PauseScene extends MenuScene {
    public PauseScene() {
        text.setText("GAME PAUSED");

        menuItems = new Option[2];

        menuItems[0] = new Option("CONTINUE");
        menuItems[0].setOnActivate(() -> Main.setPlayingStatus(1, "continue"));

        menuItems[1] = new Option("RETURN");
        menuItems[1].setOnActivate(() -> {
            Main.setPlayingStatus(0, "return");
        });

        currentItem = 0;
        menuItems[currentItem].setActive(true);

        root.getChildren().add(text);
        root.getChildren().addAll(menuItems);
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