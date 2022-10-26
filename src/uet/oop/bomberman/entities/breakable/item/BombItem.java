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
