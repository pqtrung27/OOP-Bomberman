/******************************************************************************
 *
 *  Dependency: Item.java
 *
 *  The data type for the Flame item.
 *
 ******************************************************************************/

package uet.oop.bomberman.entities.breakable.item;

/**
 * The {@code FlameItem} class is the data type for the Flame Item.
 * <p>
 * This FlameItem will double the range of the player bombs.
 * <p>
 *
 * @author Phu Quoc Trung
 * @author Tran Thuy Duong
 */

import uet.oop.bomberman.entities.breakable.Item;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.graphics.Sprite;

public class FlameItem extends Item {
    public FlameItem(int xUnit, int yUnit) {
        super(xUnit, yUnit, Sprite.powerup_flames.getFxImage());
    }

    public boolean powerUp(Bomber bomberman) {
        if (isBroken()) {
            return false;
        }
        bomberman.setBombRange(bomberman.getBombRange()+1);
        breakEntity();
        return true;
    }
}
