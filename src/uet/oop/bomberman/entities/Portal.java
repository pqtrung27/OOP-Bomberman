package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.graphics.Sprite;

/**
 * Lớp cài đặt đối tượng "Portal".
 *
 * @author TTD
 */
public class Portal extends Entity {
    private Brick cover;

    /**
     * Khởi tạo đối tượng sử dụng phương thức khởi tạo của lớp cha BreakableEntity.
     * Khởi tạo đối tượng Brick đặt trên Portal.
     */
    public Portal(int xUnit, int yUnit) {
        super(xUnit, yUnit, Sprite.portal.getFxImage());
        cover = new Brick(xUnit, yUnit);
    }

    /**
     * Ghi đè phương thức render() của lớp cha BreakableEntity.
     * Portal ban đầu bị giấu sau một đối tượng Brick, chỉ hiện ra sau khi Brick bị phá hủy.
     * @param gc GraphicsContext
     */
    @Override
    public void render(GraphicsContext gc) {
        if (!cover.isBroken()) {
            cover.render(gc);
        } else {
            super.render(gc);
        }
    }

    @Override
    public void update() {

    }
}
