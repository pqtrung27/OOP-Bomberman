/******************************************************************************
 *
 *  Dependency: Item.java
 *
 *  The data type for the Speed item.
 *
 ******************************************************************************/

package uet.oop.bomberman.entities.breakable.item;

/**
 * The {@code SpeedItem} class is the data type for the SpeedItem.
 * <p>
 * This SpeedItem will double the player speed.
 * <p>
 *
 * @author Phu Quoc Trung
 * @author Tran Thuy Duong
 */

import uet.oop.bomberman.entities.breakable.Item;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.graphics.Sprite;

public class SpeedItem extends Item {
    /**
     * Khởi tạo đối tượng sử dụng phương thức khởi tạo của lớp cha Item.
     */
    public SpeedItem(int xUnit, int yUnit) {
        super(xUnit, yUnit, Sprite.powerup_speed.getFxImage());
    }

    @Override
    public boolean powerUp(Bomber bomberman) {
        if (isBroken()) {
            return false;
        }
        bomberman.setSpeed(bomberman.getSpeed() + 1);
        breakEntity();
        return true;
    }

    @Override
    public void update() {

    }
}
