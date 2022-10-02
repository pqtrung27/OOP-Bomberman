package uet.oop.bomberman.entities;

import uet.oop.bomberman.graphics.Sprite;

/**
 * Lớp cài đặt đối tượng "Brick".
 *
 * @author TTD
 */
public class Brick extends BreakableEntity {
    /**
     * Khởi tạo đối tượng sử dụng phương thức khởi tạo của lớp cha BreakableEntity.
     */
    public Brick(int xUnit, int yUnit) {
        super(xUnit, yUnit, Sprite.brick.getFxImage());
    }

    @Override
    public void update() {

    }
}
