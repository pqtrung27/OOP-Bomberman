/******************************************************************************
 *  Dependency: DisplaceScene.java
 *
 *  The class to create the fixed scenes.
 *
 ******************************************************************************/

package uet.oop.bomberman.display.scene;

/**
 * The {@code FixedScene} class extend DisplayScene class to create
 * all the fixed scene.
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

public class FixedScene extends DisplayScene {
    public FixedScene(String mes) {
        Text text = new Text(mes);
        text.setFont(Main.FONT);
        text.setFill(Color.WHITE);

        VBox root = new VBox(text);
        root.setAlignment(Pos.CENTER);
        root.setBackground(new Background(new BackgroundFill(Color.BLACK,
                CornerRadii.EMPTY,
                Insets.EMPTY)));

        scene = new Scene(root, Main.initialSceneWidth, Main.initialSceneHeight);
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