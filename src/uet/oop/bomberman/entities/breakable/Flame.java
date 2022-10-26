/******************************************************************************
 *
 *  Dependency: BreakableEntity.java
 *
 *  The data type for the Flame.
 *
 ******************************************************************************/

package uet.oop.bomberman.entities.breakable;

/**
 * The {@code Flame} class is the data type for the Flame
 * <p>
 * Flames are being created when the bombs explode.
 * Flame could destroy everything in its range, excepts for the Walls.
 * <p>
 *
 * @author Phu Quoc Trung
 * @author Tran Thuy Duong
 */

import uet.oop.bomberman.entities.BreakableEntity;
import uet.oop.bomberman.graphics.Sprite;

public class Flame extends BreakableEntity {
    public static final int horizontalLeft = 0;
    public static final int horizontalRight = 1;
    public static final int verticalTop = 2;
    public static final int verticalDown = 3;

    private final int direction;
    private final boolean isLast;
    public Flame(int xUnit, int yUnit, int direction, boolean isLast) {
        super(xUnit, yUnit, Sprite.bomb.getFxImage());
        this.direction = direction;
        this.isLast = isLast;
        breakEntity();
    }

    @Override
    public void update() {
        if (isBroken) {
            board.flames.remove(this);
            return;
        }
        Sprite sprite = Sprite.bomb;
        switch (direction) {
            case horizontalLeft:
                if (isLast) {
                    sprite = Sprite.movingSprite(Sprite.explosion_horizontal_left_last,
                            Sprite.explosion_horizontal_left_last1,
                            Sprite.explosion_horizontal_left_last2,
                            animate, 35);
                } else {
                    sprite = Sprite.movingSprite(Sprite.explosion_horizontal,
                            Sprite.explosion_horizontal1,
                            Sprite.explosion_horizontal2,
                            animate, 35);
                }
                break;
            case horizontalRight:
                if (isLast) {
                    sprite = Sprite.movingSprite(Sprite.explosion_horizontal_right_last,
                            Sprite.explosion_horizontal_right_last1,
                            Sprite.explosion_horizontal_right_last2,
                            animate, 35);
                } else {
                    sprite = Sprite.movingSprite(Sprite.explosion_horizontal,
                            Sprite.explosion_horizontal1,
                            Sprite.explosion_horizontal2,
                            animate, 35);
                }
                break;
            case verticalTop:
                if (isLast) {
                    sprite = Sprite.movingSprite(Sprite.explosion_vertical_top_last,
                            Sprite.explosion_vertical_top_last1,
                            Sprite.explosion_vertical_top_last2,
                            animate, 35);
                } else {
                    sprite = Sprite.movingSprite(Sprite.explosion_vertical,
                            Sprite.explosion_vertical1,
                            Sprite.explosion_vertical2,
                            animate, 35);
                }
                break;
            case verticalDown:
                if (isLast) {
                    sprite = Sprite.movingSprite(Sprite.explosion_vertical_down_last,
                            Sprite.explosion_vertical_down_last1,
                            Sprite.explosion_vertical_down_last2,
                            animate, 35);
                } else {
                    sprite = Sprite.movingSprite(Sprite.explosion_vertical,
                            Sprite.explosion_vertical1,
                            Sprite.explosion_vertical2,
                            animate, 35);
                }
                break;
        }
        this.img = sprite.getFxImage();
        animate();
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