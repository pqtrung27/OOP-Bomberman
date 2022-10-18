package uet.oop.bomberman.entities.breakable.item;

import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.graphics.Sprite;

/**
 * Lớp cài đặt đối tượng vật phẩm "Speed".
 *
 * @author TTD
 */
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
