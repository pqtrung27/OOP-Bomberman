/******************************************************************************
 *  Dependency: HBox.java.
 *
 *  The class for the selection box (option).
 *
 ******************************************************************************/

package uet.oop.bomberman.display.scene.menu;

/**
 * The {@code Option} class extend Menu class is the
 * class for the selection box (option).
 * <p>
 * This implementation uses JavaFx entirely.
 * <p>
 * @author Phu Quoc Trung
 * @author Tran Thuy Duong
 */

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import uet.oop.bomberman.Main;

/**
 * Tham khảo youtuber Almas Baimagambetov.
 */
public class Option extends HBox {
    private ImageView leftArrow;
    private ImageView rightArrow;
    private Text text;
    private Runnable script;

    public Option(String name) {
        super(15);
        setAlignment(Pos.CENTER);

        Image la = new Image("file:res/textures/leftarrow.png");
        Image ra = new Image("file:res/textures/rightarrow.png");
        leftArrow = new ImageView(la);
        rightArrow = new ImageView(ra);

        text = new Text(name);
        text.setFont(Main.FONT);

        getChildren().addAll(leftArrow, text, rightArrow);
        setActive(false);
    }

    public void setActive(boolean active) {
        leftArrow.setVisible(active);
        rightArrow.setVisible(active);
        text.setFill(active ? Color.WHITE : Color.GRAY);
    }

    public void setOnActivate(Runnable r) {
        script = r;
    }

    public void activate() {
        if (script != null) {
            script.run();
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