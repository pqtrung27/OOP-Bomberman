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
    protected Brick cover;

    /**
     * Khởi tạo đối tượng sử dụng phương thức khởi tạo của lớp cha BreakableEntity.
     * Khởi tạo đối tượng Brick đặt trên vật phẩm.
     */
    public Item(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        cover = new Brick(xUnit, yUnit);
    }

    /**
     * Ghi đè phương thức render() của lớp cha BreakableEntity.
     * Các vật phẩm ban đầu bị giấu sau một đối tượng Brick, chỉ hiện ra sau khi Brick bị phá hủy.
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

    /**
     * Ghi đè phương thức breakEntity() của lớp cha BreakableEntity.
     * Vật phẩm chỉ có thể bị phá hủy sau khi đối tượng Brick ở trên bị phá hủy.
     */
    @Override
    public void breakEntity() {
        if (!cover.isBroken()) {
            cover.breakEntity();
        } else {
            this.isBroken = true;
        }
    }

    /**
     * Phương thức kiểm tra vật phẩm bị giấu hay không.
     * @return true nếu vật phẩm bị giấu sau đối tượng Brick.
     */
    public boolean isCovered() {
        return !cover.isBroken();
    }

    public abstract boolean powerUp(Bomber bomber);

    @Override
    public void update() {

    }
}
