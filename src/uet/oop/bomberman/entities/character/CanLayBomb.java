/******************************************************************************
 *
 *  The interface for all the entity which could lays bombs to implement.
 *
 ******************************************************************************/

package uet.oop.bomberman.entities.character;

/**
 * The {@code CanLayBomb} interface is the interface for all the
 * entity which could lays bombs to implement.
 *
 * <p>
 *
 * @author Phu Quoc Trung
 * @author Tran Thuy Duong
 */

public interface CanLayBomb {
    int getBombCount();
    void setBombCount(int bombCount);
    int getBombRange();
    void setBombRange(int bombRange);
    boolean isBomber();
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