package uet.oop.bomberman.display.scene.menu;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import uet.oop.bomberman.Main;
import uet.oop.bomberman.display.scene.MenuScene;

public class About extends MenuScene {
    public About() {
        text.setText("ABOUT");

        VBox description = new VBox();
        description.setSpacing(5);
        description.setAlignment(Pos.CENTER);

        description.getChildren().add(createText("a game project made under"));
        description.getChildren().add(createText("object-oriented programming course"));
        description.getChildren().add(createText("INT2204-UET-VNU"));
        description.getChildren().add(createText(""));
        description.getChildren().add(createText("AUTHOR:"));
        description.getChildren().add(createText("TRAN THUY DUONG"));
        description.getChildren().add(createText("PHU QUOC TRUNG"));
        description.getChildren().add(createText(""));
        description.getChildren().add(createText("this is free software: you can redistribute it "));
        description.getChildren().add(createText("and/or modify it under the terms of the gnu"));
        description.getChildren().add(createText("general public license as published by"));
        description.getChildren().add(createText("the free software foundation,"));
        description.getChildren().add(createText("either version 3 of the license,"));
        description.getChildren().add(createText("or (at your option) any later version"));

        menuItems = new Option[1];
        menuItems[0] = new Option("BACK");
        menuItems[0].setOnActivate(() -> Main.setPlayingStatus(0, "back"));
        menuItems[0].setActive(true);

        root.setSpacing(20);
        root.getChildren().add(text);
        root.getChildren().add(description);
        root.getChildren().addAll(menuItems);
    }

    private Text createText(String mes) {
        Text text = new Text(mes);
        text.setFont(Main.FONT);
        text.setFill(Color.WHITE);
        text.setStyle("-fx-font-size: 20");
        text.setTextAlignment(TextAlignment.CENTER);
        return text;
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