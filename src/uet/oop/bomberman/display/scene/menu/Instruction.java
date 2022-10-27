/******************************************************************************
 *  Dependency: Menu.java.
 *
 *  The class for the game instruction scene.
 *
 ******************************************************************************/

package uet.oop.bomberman.display.scene.menu;

/**
 * The {@code Instruction} class extend Menu class is the
 * class for the instruction scene.
 * <p>
 * This implementation uses JavaFx entirely.
 * <p>
 * @author Phu Quoc Trung
 * @author Tran Thuy Duong
 */

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import uet.oop.bomberman.Main;
import uet.oop.bomberman.display.scene.MenuScene;
import uet.oop.bomberman.graphics.Sprite;

public class Instruction extends MenuScene {
    private final Page[] pages;
    private int currentPage = 0;
    public Instruction() {
        pages = new Page[6];
        pages[0] = new Page("PLAYER");
        pages[1] = new Page("CONTROLS");
        pages[2] = new Page("ENTITIES");
        pages[3] = new Page("ENEMIES");
        pages[4] = new Page("ENEMIES");
        pages[5] = new Page("POWER-UPS");

        setPlayerBody();
        setControlsBody();
        setEntitiesBody();
        setEnemies1Body();
        setEnemies2Body();
        setPowerUpsBody();
    }

    private void setPlayerBody() {
        pages[0].addBody(createInstruction(
                createLabel("BOMBER", Sprite.player_right.getFxImage()),
                createDescription("you control bomberman\nthroughout the game.\n" +
                        "in order to pass each stage,\nyou must kill every enemy,\n" +
                        "and reveal the portal which\ncan only be used once\n" +
                        "every enemy is defeated")
        ));
    }

    private void setControlsBody() {
        pages[1].addBody(createInstruction(
                createLabel(" ←↑→↓", null),
                createDescription("use up, down, left, right keys\nto direct bomberman player")
        ));
        pages[1].addBody(createInstruction(
                createLabel("SPACE", null),
                createDescription("use space to lay a bomb\nin player's current position")
        ));
        pages[1].addBody(createInstruction(
                createLabel("ESCAPE", null),
                createDescription("use escape to pause the game")
        ));
        pages[1].addBody(createInstruction(
                createLabel("ENTER", null),
                createDescription("use enter to select any options")
        ));
    }

    private void setEntitiesBody() {
        pages[2].addBody(createInstruction(
                createLabel("BOMB", Sprite.bomb.getFxImage()),
                createDescription("bombs can be laid down\n" +
                                "to break  other entities,\n" +
                                "can be powered-up when\n" +
                                "player take power-up items")
        ));
        pages[2].addBody(createInstruction(
                createLabel("WALL", Sprite.wall.getFxImage()),
                createDescription("walls aren't damaged\n" +
                                "by bomb flames")
        ));
        pages[2].addBody(createInstruction(
                createLabel("BRICK", Sprite.brick.getFxImage()),
                createDescription("bricks dissolve when hit by\n" +
                                "bomb flames, sometimes they hide\n" +
                                "portal and power-up items under")
        ));
        pages[2].addBody(createInstruction(
                createLabel("PORTAL", Sprite.portal.getFxImage()),
                createDescription("portal is used to\n" +
                                "proceed to next level and\n" +
                                "can be damaged by bomb flames")
        ));
    }

    private void setEnemies1Body() {
        Text description = new Text("there are 8 types of enemy characters\n" +
                "each type moves in its own particular fashion\n" +
                "player will earn points when an enemy is killed");
        description.setFont(Main.FONT);
        description.setFill(Color.YELLOW);
        description.setStyle("-fx-font-size: 25");
        description.setTextAlignment(TextAlignment.CENTER);
        HBox desBox = new HBox(description);
        desBox.setAlignment(Pos.CENTER);
        pages[3].addBody(desBox);

        pages[3].addBody(createInstruction(
                createLabel("", null),
                createDescription("points   speed   smart   wallpass")
        ));
        pages[3].addBody(createInstruction(
                createLabel("BALLOM", Sprite.ballom_left1.getFxImage()),
                createDescription("100      slow    low     no")
        ));
        pages[3].addBody(createInstruction(
                createLabel("ONEAL", Sprite.oneal_left1.getFxImage()),
                createDescription("200      normal  normal  no")
        ));
        pages[3].addBody(createInstruction(
                createLabel("DOLL", Sprite.doll_left1.getFxImage()),
                createDescription("400      normal  low     no")
        ));
    }

    private void setEnemies2Body() {
        Text description = new Text("there are 8 types of enemy characters\n" +
                "each type moves in its own particular fashion\n" +
                "player will earn points when an enemy is killed");
        description.setFont(Main.FONT);
        description.setFill(Color.YELLOW);
        description.setStyle("-fx-font-size: 25");
        description.setTextAlignment(TextAlignment.CENTER);
        HBox desBox = new HBox(description);
        desBox.setAlignment(Pos.CENTER);
        pages[4].addBody(desBox);

        pages[4].addBody(createInstruction(
                createLabel("", null),
                createDescription("points   speed   smart   wallpass")
        ));
        pages[4].addBody(createInstruction(
                createLabel("MINVO", Sprite.minvo_left1.getFxImage()),
                createDescription("800      fast    normal  no")
        ));
        pages[4].addBody(createInstruction(
                createLabel("KONDORIA", Sprite.kondoria_left1.getFxImage()),
                createDescription("1000     slow    high    yes")
        ));
    }

    private void setPowerUpsBody() {
        pages[5].addBody(createInstruction(
                createLabel("BOMBS", Sprite.powerup_bombs.getFxImage()),
                createDescription("increase  the amount of bombs\n" +
                                        "dropped at a time by 1\n" +
                                        "collectible multiple times")
        ));
        pages[5].addBody(createInstruction(
                createLabel("FLAMES", Sprite.powerup_flames.getFxImage()),
                createDescription("increases bomb explosion range\n" +
                                        "in four directions by 1\n" +
                                        "collectible multiple times")
        ));
        pages[5].addBody(createInstruction(
                createLabel("SPEED", Sprite.powerup_speed.getFxImage()),
                createDescription("increases movement speed")
        ));
    }

    private HBox createInstruction(VBox label, VBox description) {
        HBox ins = new HBox(label, description);
        ins.setSpacing(50);
        // ins.setAlignment(Pos.CENTER);
        ins.setVisible(true);
        return ins;
    }

    private VBox createLabel(String mes, Image img) {
        Text label = new Text(mes);
        label.setFont(Main.FONT);
        label.setFill(Color.YELLOW);
        label.setStyle("-fx-font-size: 25");

        VBox labelBox = new VBox(label);
        labelBox.setAlignment(Pos.CENTER_RIGHT);
        labelBox.setMinWidth(230);
        labelBox.setMaxWidth(230);
        labelBox.setSpacing(10);

        if (img != null) {
            labelBox.getChildren().add(new ImageView(img));
        }
        // labelBox.setStyle("-fx-border-color: WHITE; -fx-border-width: 2");
        return labelBox;
    }

    private VBox createDescription(String mes) {
        Text description = new Text(mes);
        description.setFont(Main.FONT);
        description.setFill(Color.WHITE);
        description.setStyle("-fx-font-size: 25");

        VBox descriptionBox = new VBox(description);
        descriptionBox.setAlignment(Pos.CENTER_LEFT);
        return descriptionBox;
    }

    @Override
    public Scene getScene() {
        return pages[currentPage].getScene();
    }

    @Override
    public void update() {
        pages[currentPage].update();
    }

    @Override
    public void reset() {
        startBGM();
        currentPage = 0;
        pages[currentPage].reset();
    }

    private void nextPage() {
        if (currentPage < pages.length - 1) {
            pages[++currentPage].reset();
        }
    }

    private void prevPage() {
        if (currentPage > 0) {
            --currentPage;
        }
    }

    private class Page extends MenuScene {
        private final VBox body = new VBox();
        public Page(String name) {
            text.setText(name);

            body.setSpacing(10);

            menuItems = new Option[3];

            menuItems[0] = new Option("NEXT");
            menuItems[0].setOnActivate(Instruction.this::nextPage);

            menuItems[1] = new Option("BACK");
            menuItems[1].setOnActivate(Instruction.this::prevPage);

            menuItems[2] = new Option("RETURN");
            menuItems[2].setOnActivate(() -> Main.setPlayingStatus(0, "back"));

            menuItems[currentItem].setActive(true);
            root.getChildren().add(text);
            root.getChildren().add(new Text());
            root.getChildren().add(body);
            root.getChildren().add(new Text());
            root.getChildren().addAll(menuItems);
        }

        public void addBody(HBox ins) {
            body.getChildren().add(ins);
        }

        @Override
        public void reset() {
            menuItems[currentItem].setActive(false);
            currentItem = minPos;
            menuItems[currentItem].setActive(true);
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