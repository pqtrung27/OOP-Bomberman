/******************************************************************************
 *  Dependency: DisplaceScene.java
 *
 *  The abstract class for all the item in the menu-like scene.
 *
 ******************************************************************************/

package uet.oop.bomberman.display.scene;

/**
 * The {@code MenuScene} class extend DisplayScene class is the abstract
 * class for all the item in the menu-like scene.
 * <p>
 * This implementation uses JavaFx entirely.
 * <p>
 * @author Phu Quoc Trung
 * @author Tran Thuy Duong
 */

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import uet.oop.bomberman.Main;
import uet.oop.bomberman.display.DisplayScene;
import uet.oop.bomberman.display.scene.menu.Option;

public abstract class MenuScene extends DisplayScene {
    protected int minPos = 0;
    protected int currentItem = 0;
    protected Option[] menuItems;
    protected Text text;
    protected VBox root;

    public MenuScene() {
        root = new VBox();
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);
        root.setBackground(new Background(new BackgroundFill(Color.BLACK,
                CornerRadii.EMPTY,
                Insets.EMPTY)));

        text = new Text("SCENE NAME");
        text.setFont(Main.FONT);
        text.setStyle("-fx-font-size: 40");
        text.setFill(Color.YELLOW);

        scene = new Scene(root, Main.initialSceneWidth, Main.initialSceneHeight);
    }

    @Override
    public void reset() {
        startBGM();
        menuItems[currentItem].setActive(false);
        currentItem = minPos;
        menuItems[currentItem].setActive(true);
    }

    @Override
    public void update() {
        scene.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case UP:
                    if (currentItem > minPos) {
                        menuItems[currentItem].setActive(false);
                        menuItems[--currentItem].setActive(true);
                    }
                    break;
                case DOWN:
                    if (currentItem < menuItems.length - 1) {
                        menuItems[currentItem].setActive(false);
                        menuItems[++currentItem].setActive(true);
                    }
                    break;
                case ENTER:
                    menuItems[currentItem].activate();
                    break;
            }
        });
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