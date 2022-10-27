/******************************************************************************
 *
 *  Dependency: Entity.java
 *
 *  The abstract data type for the Moving Entity in general.
 *
 ******************************************************************************/

package uet.oop.bomberman.entities;

/**
 * The {@code MovingEntity} class is the data type for all the Moving Entity
 * in this game in general.
 *
 * <p>
 * It has all the method and variable that a normal Unbreakable Entity
 * would have.
 * <p>
 *
 * @author Phu Quoc Trung
 * @author Tran Thuy Duong
 */

import javafx.scene.image.Image;

import java.util.Timer;
import java.util.TimerTask;

public abstract class MovingEntity extends Entity {
    protected int animate = 0;
    protected final int animate_MAX_VALUE = 7500;

    protected double speed = 3.0;
    public static final int directionNone = 0;
    public static final int directionUp = 1;
    public static final int directionRight = 2;
    public static final int directionDown = 3;
    public static final int directionLeft = 4;
    protected boolean isMoving;
    protected boolean isDead;
    protected boolean isKilled;

    public MovingEntity(int xUnit, int yUnit) {
        super(xUnit, yUnit);
    }

    public MovingEntity(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void kill() {
        if (isKilled || isDead) return;
        isKilled = true;

        (new Timer()).schedule(new TimerTask() {
            @Override
            public void run() {
                isDead = true;
            }
        }, 750L);
    }

    public boolean isDead() {
        return isDead;
    }

    public boolean isKilled() {
        return isKilled;
    }

    protected void animate() {
        if (animate < animate_MAX_VALUE) {
            animate++;
        } else animate = 0;
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