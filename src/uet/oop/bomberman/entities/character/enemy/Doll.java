/******************************************************************************
 *
 *  Dependency: Enemy.java
 *
 *  The data type for the Doll enemy.
 *
 ******************************************************************************/

package uet.oop.bomberman.entities.character.enemy;

/**
 * The {@code Doll} class is the data type for the Doll enemy.
 *
 * <p>
 * Doll moves at a slightly fast speed, doing some bouncy moves
 * (it don't do jumps, but it does some bouncy moves).
 * Doll are not smart, even less intelligent than Balloms
 * and they won't try to chase Bomber, prefering to move from
 * the left to right, sometimes switching to up and down.
 * <p>
 *
 * @author Phu Quoc Trung
 * @author Tran Thuy Duong
 */

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Enemy;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.util.gameUtil.StdRandom;

public class Doll extends Enemy {

    private final double initSpeed;
    private double accel;

    public Doll(int x, int y) {
        super(x, y);
        point = 400;
        super.spriteList = new Sprite[]{
                Sprite.doll_left1,
                Sprite.doll_left2,
                Sprite.doll_left3,
                Sprite.doll_right1,
                Sprite.doll_right2,
                Sprite.doll_right3,
                Sprite.doll_dead,
                Sprite.doll_score
        };
        super.speed = 1.6 + (double) ((int) (StdRandom.uniformDouble() * 10)) / 10;
        super.img = spriteList[0].getFxImage();
        this._direction = 0;
        initSpeed = super.speed;
        accel = initSpeed;
    }

    @Override
    protected boolean canPass(Entity entity) {
        return entity.canBePassed();
    }

    private int reverseDirection(int dir) {
        switch (dir) {
            case directionUp:
                return directionDown;
            case directionDown:
                return directionUp;
            case directionLeft:
                return directionRight;
            case directionRight:
                return directionLeft;
            default:
                return 0;
        }
    }

    @Override
    protected void calculateMove() {
        if (this._direction == 0){
            calMoveForNone();
        } else {
            if (!canMove(this._direction)) {
                accel = initSpeed;
                _direction = reverseDirection(_direction);
            }
            this.speed = initSpeed + accel;
            if (accel > 0) accel -= 0.1;
            else accel = 0;
            super.move();
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