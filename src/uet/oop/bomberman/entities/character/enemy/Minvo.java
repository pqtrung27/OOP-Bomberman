/******************************************************************************
 *
 *  Dependency: Enemy.java
 *
 *  The data type for the Minvo enemy.
 *
 ******************************************************************************/

package uet.oop.bomberman.entities.character.enemy;

/**
 * The {@code Minvo} class is the data type for the Minvo enemy.
 *
 * <p>
 * Minvos move like fast Oneals, and are orange smiley faces.
 * These are encountered after the Doll. They will commonly pursue Bomber
 * if he's nearby.
 * <p>
 *
 * @author Phu Quoc Trung
 * @author Tran Thuy Duong
 */

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Enemy;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.util.gameUtil.StdRandom;
public class Minvo extends Enemy {
    public Minvo(int x, int y) {
        super(x, y);
        point = 800;
        super.spriteList = new Sprite[]{
                Sprite.minvo_left1,
                Sprite.minvo_left2,
                Sprite.minvo_left3,
                Sprite.minvo_right1,
                Sprite.minvo_right2,
                Sprite.minvo_right3,
                Sprite.minvo_dead,
                Sprite.minvo_score
        };
        super.speed = 1.5 + (double) ((int) (StdRandom.uniformDouble()/3 * 10)) / 10;
        if (speed == 1.5) speed = 1.6;
        super.img = spriteList[0].getFxImage();
    }

    @Override
    protected boolean canPass(Entity entity) {
        return entity.canBePassed();
    }

    @Override
    protected void calculateMove() {
        aiMovement();
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