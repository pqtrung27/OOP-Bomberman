/******************************************************************************
 *  Dependency: Menu.java.
 *
 *  The class for the menu scene render right after the game started.
 *
 ******************************************************************************/

package uet.oop.bomberman.display.scene.menu;

/**
 * The {@code HomeScene} class extend Menu class is the
 * class for the main menu scene with all the option.
 * <p>
 * This implementation uses JavaFx entirely.
 * <p>
 * @author Phu Quoc Trung
 * @author Tran Thuy Duong
 */

import uet.oop.bomberman.Main;
import uet.oop.bomberman.display.scene.MenuScene;
import uet.oop.bomberman.sound.Sound;

public class HomeScene extends MenuScene {
    public HomeScene() {

        Sound.TitleBGM.play();
        text.setText("BOMBERMAN");
        text.setStyle("-fx-font-size: 80");

        menuItems = new Option[6];

        menuItems[0] = new Option("CONTINUE");
        menuItems[0].setOnActivate(() -> Main.setPlayingStatus(1, "continue"));

        menuItems[1] = new Option("START");
        menuItems[1].setOnActivate(() -> Main.setPlayingStatus(1, "start"));

        menuItems[2] = new Option("INSTRUCTION");
        menuItems[2].setOnActivate(() -> Main.setPlayingStatus(7, "instruction"));

        menuItems[3] = new Option("LEADERBOARD");
        menuItems[3].setOnActivate(() -> Main.setPlayingStatus(5, "leaderboard"));

        menuItems[4] = new Option("ABOUT");

        menuItems[5] = new Option("QUIT");
        menuItems[5].setOnActivate(() -> System.exit(0));

        currentItem = 0;
        menuItems[currentItem].setActive(true);

        root.getChildren().add(text);
        root.getChildren().addAll(menuItems);
    }

    public void setCanContinue(boolean canContinue) {
        minPos = (canContinue ? 0 : 1);
        reset();
        menuItems[0].setVisible(canContinue);
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