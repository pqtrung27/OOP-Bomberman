/******************************************************************************
 *
 *  Dependency: Item.java
 *
 *  The data type for the Bomb item.
 *
 ******************************************************************************/

package uet.oop.bomberman.entities.breakable.item;

/**
 * The {@code BombItem} class is the data type for the BombItem.
 * <p>
 * This BombItem will increase the number of bombs the player could lay by 1.
 * <p>
 *
 * @author Phu Quoc Trung
 * @author Tran Thuy Duong
 */


import uet.oop.bomberman.entities.breakable.Item;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.graphics.Sprite;

public class BombItem extends Item {
    public BombItem(int xUnit, int yUnit) {
        super(xUnit, yUnit, Sprite.powerup_bombs.getFxImage());
    }

    public boolean powerUp(Bomber bomberman) {
        if (isBroken()) {
            return false;
        }
        bomberman.setMaxBombCount(bomberman.getMaxBombCount()+1);
        breakEntity();
        return true;
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
