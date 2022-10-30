/******************************************************************************
 *  Dependency: Menu.java.
 *
 *  The class for entering the new record.
 *
 ******************************************************************************/

package uet.oop.bomberman.display.scene.menu;

/**
 * The {@code NewHighScore} class extend Menu class is the
 * class for entering the new record.
 * <p>
 * This implementation uses JavaFx entirely.
 * <p>
 * @author Phu Quoc Trung
 * @author Tran Thuy Duong
 */

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import uet.oop.bomberman.Main;
import uet.oop.bomberman.display.scene.BombermanGame;
import uet.oop.bomberman.display.scene.MenuScene;

public class NewHighScore extends MenuScene {
    TextField textField;
    public NewHighScore() {
        text.setText("NEW HIGH SCORE");

        Text mes = new Text("Congratulations!\nYou made it to leaderboard!\nPlease enter your name");
        mes.setFont(Main.FONT);
        mes.setFill(Color.WHITE);
        mes.setTextAlignment(TextAlignment.CENTER);
        mes.setLineSpacing(5);

        textField = new TextField();
        textField.setMinWidth(500);
        textField.setMaxWidth(500);
        textField.setMinHeight(50);
        textField.setMaxHeight(50);
        textField.setFont(Main.FONT);
        textField.setAlignment(Pos.CENTER);
        textField.setStyle("-fx-border-color: YELLOW; -fx-border-width: 3; -fx-text-fill: YELLOW; -fx-font-size: 20");
        textField.setBackground(null);

        menuItems = new Option[1];
        menuItems[0] = new Option("ENTER");
        menuItems[0].setOnActivate(() -> {
            String name = textField.getText();
            if (name.length() > 0) {
                LeaderBoard.setHighScore(name.toUpperCase(), BombermanGame.getScore());
                Main.setPlayingStatus(4, "game over");
            }
        });
        menuItems[0].setActive(true);

        root.setSpacing(20);
        root.getChildren().add(text);
        root.getChildren().add(mes);
        root.getChildren().add(textField);
        root.getChildren().addAll(menuItems);
    }

    public void reset() {
        textField.setEditable(true);
        textField.clear();
    }

    public void close() {
        textField.setEditable(false);
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