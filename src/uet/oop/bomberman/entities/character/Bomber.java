/******************************************************************************
 *
 *  Dependency: MovingEntity.java
 *
 *  The data type for Bomber, the main character of this game.
 *
 ******************************************************************************/

package uet.oop.bomberman.entities.character;

/**
 * The {@code Bomber} class is the data type for the
 * Bomber, main character of this game.
 *
 * <p>
 *
 * @author Phu Quoc Trung
 * @author Tran Thuy Duong
 */

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import uet.oop.bomberman.Main;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.MovingEntity;
import uet.oop.bomberman.entities.breakable.Item;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.util.gameUtil.Controller;

public class Bomber extends MovingEntity implements CanLayBomb {

    private final int xPadding = Sprite.SCALED_SIZE * 4;
    private Sprite _sprite = Sprite.player_right;
    private int _direction;

    private static int maxBombCount = 1;
    private static int bombRange = 1;
    private int bombCount = 0;
    private int _x;
    private int _y;

    public Bomber(int xUnit, int yUnit) {
        super(xUnit, yUnit, Sprite.player_right.getFxImage());
        _x = xUnit * Sprite.SCALED_SIZE;
        _y = yUnit * Sprite.SCALED_SIZE;
        this.isKilled = false;
        this.isDead = false;
        this.isMoving = false;
        this.spriteOffsetTop = 8 * 3;
        this.spriteOffsetRight = 4 * 3;
    }

    @Override
    public void update() {
        animate();
        chooseSprite();
        this.x = _x;
        this.y = _y;
        this.img = _sprite.getFxImage();
        if (!isKilled && !isDead) {
            powerUp();
            calculateMove();
        }
    }

    public int getMaxBombCount() {
        return maxBombCount;
    }

    public void setMaxBombCount(int maxBombCount) {
        this.maxBombCount = maxBombCount;
    }

    public int getBombCount() {
        return bombCount;
    }

    public void setBombCount(int bombCount) {
        this.bombCount = bombCount;
    }

    public int getBombRange() {
        return bombRange;
    }

    public void setBombRange(int bombRange) {
        this.bombRange = bombRange;
    }

    public static void reset() {
        maxBombCount = 1;
        bombRange = 1;
    }

    protected void powerUp() {
        Entity entity = board.getEntityCollideWith(this, 0, 0);
        if (entity != null && board.collide(this, entity) && entity.isItem()) {
            ((Item) entity).powerUp(this);
            (new MediaPlayer(
                    new Media(getClass().getResource("/audio/PowerUpSE.wav").toString())
            )).play();
        }
    }

    public boolean isInPortal() {
        Entity entity = board.getEntityCollideWith(this, 0, 0);
        return entity != null &&board.collide(this, entity) && entity.isPortal();
    }

    protected void calculateMove() {
        int addX = 0;
        int addY = 0;
        if (Controller.direction[directionUp]) {
            addY--;
            _direction = directionUp;
        } else if (Controller.direction[directionDown]) {
            addY++;
            _direction = directionDown;
        } else if (Controller.direction[directionLeft]) {
            addX--;
            _direction = directionLeft;
        } else if (Controller.direction[directionRight]) {
            addX++;
            _direction = directionRight;
        }

        if (addX != 0 || addY != 0) {
            move(addX * speed, addY * speed);
        } else isMoving = false;

        if (_direction == MovingEntity.directionRight && _x + Sprite.SCALED_SIZE > Main.initialSceneWidth + board.boardOffsetX - xPadding) {
            board.boardOffsetX = Math.min(board.nCol * Sprite.SCALED_SIZE - Main.initialSceneWidth
                    , _x + Sprite.SCALED_SIZE - Main.initialSceneWidth + xPadding);
        }
        if (_direction == MovingEntity.directionLeft && _x <= xPadding + board.boardOffsetX) {
            board.boardOffsetX = Math.max(0, _x - xPadding);
        }

        if (_direction == MovingEntity.directionDown && _y + Sprite.SCALED_SIZE > Main.initialSceneHeight + board.boardOffsetY - xPadding) {
            board.boardOffsetY = Math.min((board.nRow+1) * Sprite.SCALED_SIZE - Main.initialSceneHeight
                    , _y + Sprite.SCALED_SIZE - Main.initialSceneHeight + xPadding);
        }
        if (_direction == MovingEntity.directionUp && _y <= xPadding + board.boardOffsetY) {
            board.boardOffsetY = Math.max(0, _y - xPadding);
        }
    }

    public void move(double addX, double addY) {
        isMoving = true;
        Entity entity = board.getEntityCollideWith(this, addX, 0);
        if (entity == null || entity.canBePassed() || entity.isEnemy()) {
            _x += addX;
        } else {
            addX = 0;
            if (entity.getTopY() >= _y + Sprite.SCALED_SIZE - 31) {
                addY = -speed;
            } else if (entity.getTopY() + Sprite.SCALED_SIZE - 31 <= _y) {
                addY = speed;
            }
            move(addX, addY);
            return;
        }
        entity = board.getEntityCollideWith(this, 0, addY);
        if (entity == null || entity.canBePassed() || entity.isEnemy()) {
            _y += addY;
        } else {
            addY = 0;
            if (entity.getTopX() >= _x + Sprite.SCALED_SIZE - 31) {
                addX = -speed;
            } else if (entity.getTopX() + Sprite.SCALED_SIZE - 31 <= _x) {
                addX = speed;
            }
            move(addX, addY);
        }
    }

    private void chooseSprite() {
        if (isKilled) {
            _sprite = Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2, Sprite.player_dead3, animate, 35);
        } else {
            switch (_direction) {
                case MovingEntity.directionUp:
                    _sprite = Sprite.player_up;
                    if (isMoving) {
                        _sprite = Sprite.movingSprite(Sprite.player_up_1, Sprite.player_up_2, animate, 20);
                    }
                    break;
                case MovingEntity.directionDown:
                    _sprite = Sprite.player_down;
                    if (isMoving) {
                        _sprite = Sprite.movingSprite(Sprite.player_down_1, Sprite.player_down_2, animate, 20);
                    }
                    break;
                case MovingEntity.directionLeft:
                    _sprite = Sprite.player_left;
                    if (isMoving) {
                        _sprite = Sprite.movingSprite(Sprite.player_left_1, Sprite.player_left_2, animate, 20);
                    }
                    break;
                case MovingEntity.directionRight:
                    _sprite = Sprite.player_right;
                    if (isMoving) {
                        _sprite = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2, animate, 20);
                    }
                    break;
                default:
                    break;
            }
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