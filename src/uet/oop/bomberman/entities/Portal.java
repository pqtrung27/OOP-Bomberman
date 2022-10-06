package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.graphics.Sprite;

/**
 * Lớp cài đặt đối tượng "Portal".
 *
 * @author TTD
 */
public class Portal extends Item {
    /**
     * Khởi tạo đối tượng sử dụng phương thức khởi tạo của lớp cha BreakableEntity.
     * Khởi tạo đối tượng Brick đặt trên Portal.
     */
    public Portal(int xUnit, int yUnit) {
        super(xUnit, yUnit, Sprite.portal.getFxImage());
    }

    @Override
    public boolean powerUp(Bomber bomberman) {
        if (isBroken()) {
            return false;
        }
        breakEntity();
        return true;
    }

    /**
     * Ghi đè phương thức breakEntity() của lớp cha BreakableEntity.
     * Portal hiện ra sau khi đối tượng Brick ở trên bị phá hủy.
     * Không thể phá hủy Portal.
     */
    @Override
    public void breakEntity() {
        if (!cover.isBroken()) {
            cover.breakEntity();
        }
    }

    /**
     * Ghi đè phương thức render() của lớp cha Item.
     * Portal chỉ hiện ra sau khi đối tượng Brick ở trên bị phá hủy.
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
