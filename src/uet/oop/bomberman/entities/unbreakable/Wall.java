/******************************************************************************
 *
 *  Dependency: UnbreakableEntity.java
 *
 *  The data type for the Wall entity.
 *
 ******************************************************************************/

package uet.oop.bomberman.entities.unbreakable;

/**
 * The {@code Wall} class is the data type for the Wall entity.
 *
 * <p>
 * Wall can not be passed, nor broken.
 * <p>
 *
 * @author Phu Quoc Trung
 * @author Tran Thuy Duong
 */

import uet.oop.bomberman.entities.UnbreakableEntity;
import uet.oop.bomberman.graphics.Sprite;

public class Wall extends UnbreakableEntity {



    public Wall(int xUnit, int yUnit) {
        super(xUnit, yUnit, Sprite.wall.getFxImage());
        this.spriteOffsetTop = 0;
        this.spriteOffsetBot = 0;
        this.spriteOffsetLeft = 0;
        this.spriteOffsetRight = 0;
    }

    @Override
    public void update() {

    }
}
