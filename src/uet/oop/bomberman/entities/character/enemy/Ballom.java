/******************************************************************************
 *
 *  Dependency: Enemy.java
 *
 *  The data type for the Ballom enemy.
 *
 ******************************************************************************/

package uet.oop.bomberman.entities.character.enemy;

/**
 * The {@code Ballom} class is the data type for the Ballom enemy.
 * <p>
 * Ballom has a very unpredictable movement pattern.
 * It takes one hit to defeat.
 * <p>
 *
 * @author Phu Quoc Trung
 * @author Tran Thuy Duong
 */


import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Enemy;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.util.gameUtil.StdRandom;

public class Ballom extends Enemy {
    public Ballom(int x, int y) {
        super(x, y);
        point = 100;
        super.spriteList = new Sprite[]{
                Sprite.ballom_left1,
                Sprite.ballom_left2,
                Sprite.ballom_left3,
                Sprite.ballom_right1,
                Sprite.ballom_right2,
                Sprite.ballom_right3,
                Sprite.ballom_dead,
                Sprite.ballom_score
        };
        super.speed = 0.5 + (double) ((int) (StdRandom.uniformDouble() * 10)) / 10;
        super.img = spriteList[0].getFxImage();
        super.spriteOffsetTop = 0;
        super.spriteOffsetBot = 0;
    }

    @Override
    protected void calculateMove() {
        if (this._direction == 0) {
            calMoveForNone();
        } else {
            if ((x + speed) % Sprite.SCALED_SIZE <= speed * 2 && (y + speed) % Sprite.SCALED_SIZE <= speed * 2) {
                super.randomMovement();
            }
            super.move();
        }
    }

    @Override
    protected boolean canPass(Entity entity) {
        return entity.canBePassed();
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