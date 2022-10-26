/******************************************************************************
 *
 *  Dependency: BreakableEntity.java
 *
 *  The data type for the Brick.
 *
 ******************************************************************************/

package uet.oop.bomberman.entities.breakable;

/**
 * The {@code Brick} class is the data type for the Brick
 * <p>
 * Bricks could be destroyed by flame.
 * <p>
 *
 * @author Phu Quoc Trung
 * @author Tran Thuy Duong
 */

import uet.oop.bomberman.entities.BreakableEntity;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends BreakableEntity {
    /**
     * Khởi tạo đối tượng sử dụng phương thức khởi tạo của lớp cha BreakableEntity.
     */
    public Brick(int xUnit, int yUnit) {
        super(xUnit, yUnit, Sprite.brick.getFxImage());
    }

    @Override
    public void update() {
        if (!isExploding) {
            return;
        }
        animate();
        Sprite sprite = Sprite.movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1, Sprite.brick_exploded2, animate, 35);
        this.img = sprite.getFxImage();
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