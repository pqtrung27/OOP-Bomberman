package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.character.Bomber;

/**
 * Lớp trừu tượng cài đặt các đối tượng vật phẩm.
 *
 * @author TTD
 */
public abstract class Item extends BreakableEntity {
    /**
     * Khởi tạo đối tượng sử dụng phương thức khởi tạo của lớp cha BreakableEntity.
     * Khởi tạo đối tượng Brick đặt trên vật phẩm.
     */
    public Item(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    /**
     * Phương thức chuyển trạng thái của đối tượng.
     */
    @Override
    public void breakEntity() {
        this.isBroken = true;
    }

    public abstract boolean powerUp(Bomber bomber);

    @Override
    public void update() {

    }
}
