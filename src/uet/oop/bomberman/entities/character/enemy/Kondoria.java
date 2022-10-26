/******************************************************************************
 *
 *  Dependency: Enemy.java
 *
 *  The data type for the Kondoria enemy.
 *
 ******************************************************************************/

package uet.oop.bomberman.entities.character.enemy;

/**
 * The {@code Kondoria} class is the data type for the Kondoria enemy.
 *
 * <p>
 * Kondoria moves really slow, making Kondoria the slowest,
 * but it can move through Bricks. It appears cyan-colored,
 * just as the Oneal are. Kondoria is very smart, it will
 * commonly attempt to chase Bomberman and it can evade bombs.
 * <p>
 *
 * @author Phu Quoc Trung
 * @author Tran Thuy Duong
 */

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.CanLayBomb;
import uet.oop.bomberman.entities.character.Enemy;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.util.gameUtil.StdRandom;

import java.util.Timer;
import java.util.TimerTask;

public class Kondoria extends Enemy implements CanLayBomb {

    private boolean justLayBomb = false;
    private int bombRange = 1;
    private int bombCount = 0;

    public Kondoria(int x, int y) {
        super(x, y);
        point = 1000;
        super.spriteList = new Sprite[]{
                Sprite.kondoria_left1,
                Sprite.kondoria_left2,
                Sprite.kondoria_left3,
                Sprite.kondoria_right1,
                Sprite.kondoria_right2,
                Sprite.kondoria_right3,
                Sprite.kondoria_dead,
                Sprite.kondoria_score
        };
        super.speed = 0.3 + (double) ((int) (StdRandom.uniformDouble() * 10)) / 10;
        if (speed == 0.5) speed = 0.6;
        super.img = spriteList[0].getFxImage();
    }

    @Override
    protected boolean canPass(Entity entity) {
        if (!entity.canBePassed()) {
            return entity.isBrick();
        }
        return true;
    }

    public boolean didJustLayBomb() {
        return justLayBomb;
    }

    public void setJustLayBomb() {
        justLayBomb = true;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                justLayBomb = false;
            }
        }, 50000L);
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