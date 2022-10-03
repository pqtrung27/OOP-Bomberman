package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Lớp trừu tượng cài đặt các đối tượng có thể phá hủy.
 *
 * @author TTD
 */
public abstract class BreakableEntity extends Entity {
    // Biến boolean kiểm tra trạng thái của đối tượng (true = đã bị phá hủy)
    protected boolean isBroken;
    protected Grass base;

    /**
     * Khởi tạo đối tượng sử dụng phương thức khởi tạo của lớp cha Entity.
     * Gán trạng thái ban đầu chưa bị phá hủy cho đối tượng.
     * Khởi tạo đối tượng nền Grass.
     */
    public BreakableEntity(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        isBroken = false;
        base = new Grass(xUnit, yUnit);
    }

    /**
     * Phương thức kiểm tra trạng thái của đối tượng.
     * @return trạng thái hiện tại của đối tượng, true = đã bị phá hủy, false = chưa bị phá hủy
     */
    public boolean isBroken() {
        return isBroken;
    }

    /**
     * Phương thức chuyển trạng thái của đối tượng.
     * Gán trạng thái đã bị phá hủy cho đối tượng.
     */
    public void breakEntity() {
        isBroken = true;
    }

    /**
     * Ghi đè phương thức render() của lớp cha Entity.
     * Chỉ cho phép render khi đối tượng có trạng thái chưa bị phá hủy.
     * Render đối tượng nền Grass khi đối tượng đã bị phá hủy.
     * @param gc GraphicsContext
     */
    @Override
    public void render(GraphicsContext gc) {
        if (!isBroken) {
            super.render(gc);
        } else {
            base.render(gc);
        }
    }

    @Override
    public void update() {

    }
}
