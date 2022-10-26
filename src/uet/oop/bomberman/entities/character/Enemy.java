/******************************************************************************
 *
 *  Dependency: MovingEntity.java
 *
 *  The abstract data type for the enemies in general.
 *
 ******************************************************************************/

package uet.oop.bomberman.entities.character;

/**
 * The {@code Enemy} class is the data type for all the enemies
 * in this game in general.
 * <p>
 * It has all the method and variable that a normal enemy
 * would have.
 * <p>
 *
 * @author Phu Quoc Trung
 * @author Tran Thuy Duong
 */

import uet.oop.bomberman.display.scene.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.MovingEntity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.util.gameUtil.StdRandom;

import java.util.Timer;
import java.util.TimerTask;

public abstract class Enemy extends MovingEntity {
    private Sprite _sprite = Sprite.player_right;
    protected Sprite[] spriteList = new Sprite[8];

    protected final int leftSprite = 0;
    protected final int rightSprite = 3;
    protected final int deadSprite = 6;
    protected final int scoreSprite = 7;
    protected int _direction = directionNone;

    protected boolean isBlocked;
    protected double _x;
    protected double _y;
    protected int point; // Số điểm nhận được sau khi giết Enemy, giá trị cụ thể được gán trong phương thức khởi tạo Enemy
    protected boolean isShowingPoint = false;
    private boolean isVanishing = false;

    public Enemy(int x, int y) {
        super(x, y);
        _x = x * Sprite.SCALED_SIZE;
        _y = y * Sprite.SCALED_SIZE;
        this.isDead = false;
        this.isBlocked = true;
    }

    @Override
    public void update() {
        animate();
        chooseSprite();
        this.img = _sprite.getFxImage();
        if (!isKilled && !isDead) {
            calculateMove();
            this.x = _x;
            this.y = _y;
        }
    }

    @Override
    public void kill() {
        if (isKilled || isDead) return;
        isKilled = true;
        (new Timer()).schedule(new TimerTask() {
            @Override
            public void run() {
                isVanishing = true;
                (new Timer()).schedule(new TimerTask() {
                    @Override
                    public void run() {
                        isShowingPoint = true;
                        (new Timer()).schedule(new TimerTask() {
                            @Override
                            public void run() {
                                isDead = true;
                            }
                        }, 500);
                    }
                }, 600L);
            }
        }, 600L);
        BombermanGame.addScore(point);
        System.out.println("You killed an enemy! Score +" + point + ". Current score: " + BombermanGame.score);
    }

    public void set_direction(int _direction) {
        this._direction = _direction;
    }

    public int get_direction() {
        return this._direction;
    }

    public boolean isKilled() {
        return isKilled;
    }

    protected void randomMovement() {
        if (this._direction == 0 || !canMove(this._direction)) isBlocked = true;
        int temp = 0;
        if (isBlocked || StdRandom.uniformInt(100) % 2 == 0) {
            int cnt = 0;
            temp = StdRandom.uniformInt(4) + 1;
            while (!canMove(temp)) {
                temp++;
                if (temp == 5) {
                    temp = 1;
                }
                cnt++;
                if (cnt == 5) return;
            }
            _direction = temp;
        }
    }

    protected void aiMovement() {
        if (this._direction == 0 || !canMove(this._direction)) isBlocked = true;
        if ((x + speed) % Sprite.SCALED_SIZE <= speed * 2 && (y + speed) % Sprite.SCALED_SIZE <= speed * 2) {
            int temp = board.EnemyAIDirection(this);
            if (temp != 0) _direction = temp;
            else if (isBlocked) randomMovement();
        }
        move();
    }

    protected void calMoveForNone() {
        for (int i = 1; i <= 4; ++i) {
            if (canMove(i)) {
                _direction = i;
                break;
            }
        }
    }

    abstract protected void calculateMove();

    abstract protected boolean canPass(Entity entity);

    public boolean canMove(int direction) {
        int addX = 0;
        int addY = 0;
        if (direction == directionUp) addY--;
        if (direction == directionDown) addY++;
        if (direction == directionLeft) addX--;
        if (direction == directionRight) addX++;
        double tempSpeed = Math.ceil(speed);
        Entity temp = board.getEntityCollideWith(this, addX * tempSpeed, addY * tempSpeed);
        if (temp == null) {
            return true;
        } else return canPass(temp);
    }

    public void move() {
        int addX = 0;
        int addY = 0;
        if (_direction == directionUp) addY--;
        if (_direction == directionDown) addY++;
        if (_direction == directionLeft) addX--;
        if (_direction == directionRight) addX++;
        if (addX != 0 || addY != 0) {
            _x += addX * speed;
            _y += addY * speed;
        }
    }

    private void chooseSprite() {
        if (isDead) return;
        if (isShowingPoint) {
            _sprite = spriteList[scoreSprite];
        } else if (isKilled) {
            _sprite = spriteList[deadSprite];
            if (isVanishing) {
                _sprite = Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, animate, 100);
            }
        } else {
            switch (_direction) {
                case MovingEntity.directionUp:
                case MovingEntity.directionLeft:
                    _sprite = Sprite.movingSprite(spriteList[leftSprite], spriteList[leftSprite + 1], spriteList[leftSprite + 2], animate, 20);
                    break;
                default:
                    _sprite = Sprite.movingSprite(spriteList[rightSprite], spriteList[rightSprite + 1], spriteList[rightSprite + 2], animate, 20);
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