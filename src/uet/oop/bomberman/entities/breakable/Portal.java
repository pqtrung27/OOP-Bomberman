/******************************************************************************
 *
 *  Dependency: BreakableEntity.java
 *
 *  The data type for the Portal.
 *
 ******************************************************************************/

package uet.oop.bomberman.entities.breakable;

/**
 * The {@code Portal} class is the data type for the Portal.
 *
 * <p>
 * Only when Bomber kill all the enemies that he could use the portal
 * to process to the next level.
 * <p>
 *
 * @author Phu Quoc Trung
 * @author Tran Thuy Duong
 */

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.BreakableEntity;
import uet.oop.bomberman.entities.unbreakable.Grass;
import uet.oop.bomberman.graphics.Sprite;

public class Portal extends BreakableEntity {
    private Grass base;
    public Portal(int xUnit, int yUnit) {
        super(xUnit, yUnit, Sprite.portal.getFxImage());
        base = new Grass(xUnit, yUnit);
    }

    /**
     * Ghi đè phương thức render() của lớp cha Entity.
     * @param gc GraphicsContext
     */
    @Override
    public void render(GraphicsContext gc) {
        base.render(gc);
        super.render(gc);
    }

    @Override
    public void update() {

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