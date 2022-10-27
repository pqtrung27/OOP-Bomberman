/******************************************************************************
 *
 *  Dependency: Entity.java
 *
 *  The abstract data type for all the Entity in general.
 *
 ******************************************************************************/

package uet.oop.bomberman.entities;

/**
 * The {@code Enemy} class is the data type for all the Entity
 * in this game in general.
 * <p>
 * It has all the method and variable that a normal Unbreakable Entity
 * would have.
 * <p>
 *
 * @author Phu Quoc Trung
 * @author Tran Thuy Duong
 */

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.breakable.Bomb;
import uet.oop.bomberman.entities.breakable.Brick;
import uet.oop.bomberman.entities.breakable.Portal;
import uet.oop.bomberman.entities.breakable.Item;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemy.Ballom;
import uet.oop.bomberman.entities.character.Enemy;
import uet.oop.bomberman.entities.character.enemy.Kondoria;
import uet.oop.bomberman.entities.character.enemy.Oneal;
import uet.oop.bomberman.entities.unbreakable.Grass;
import uet.oop.bomberman.entities.unbreakable.Wall;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.util.gameUtil.Board;

public abstract class Entity {
    public static Board board = new Board();

    //Tọa độ X tính từ góc trái trên trong Canvas
    protected double x;
    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected double y;

    protected int spriteOffsetTop = 0;
    protected int spriteOffsetBot = 0;
    protected int spriteOffsetLeft = 0;
    protected int spriteOffsetRight = 0;

    protected Image img;

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity(int xUnit, int yUnit) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
    }

    public Entity(int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
    }

    public Entity() {

    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getTopX() {
        return this.x + spriteOffsetLeft;
    }

    public double getTopY() {
        return this.y + spriteOffsetTop;
    }

    public double getBotX() {
        return this.x + Sprite.SCALED_SIZE - spriteOffsetRight - 1;
    }

    public double getBotY() {
        return this.y + Sprite.SCALED_SIZE - spriteOffsetBot - 1;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x - board.boardOffsetX, y + Sprite.SCALED_SIZE - board.boardOffsetY);
    }

    public boolean isWall() {
        return this instanceof Wall;
    }

    public boolean isGrass() {
        return this instanceof Grass;
    }

    public boolean isPortal() {
        return this instanceof Portal;
    }

    public boolean isBreakable() {
        return this instanceof BreakableEntity;
    }

    public boolean isItem() {
        return this instanceof Item;
    }

    public boolean isBrick() {
        return this instanceof Brick;
    }

    public boolean isBomb() {
        return this instanceof Bomb;
    }

    public boolean isBallon() {
        return this instanceof Ballom;
    }

    public boolean isOneal() {
        return this instanceof Oneal;
    }

    public boolean isKondoria() {
        return this instanceof Kondoria;
    }

    public boolean isEnemy() {
        return this instanceof Enemy;
    }

    public boolean isBomber() {
        return this instanceof Bomber;
    }

    public boolean canBePassed() {
        return !isBrick() && !isWall() && !isBomb();
    }

    public abstract void update();
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