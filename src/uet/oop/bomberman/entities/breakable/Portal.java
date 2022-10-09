package uet.oop.bomberman.entities.breakable;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.unbreakable.Grass;
import uet.oop.bomberman.graphics.Sprite;

/**
 * Lớp cài đặt đối tượng "Portal".
 *
 * @author TTD
 */
public class Portal extends Entity {
    private Grass base;
    public Portal(int xUnit, int yUnit) {
        super(xUnit, yUnit, Sprite.portal.getFxImage());
        base = new Grass(xUnit, yUnit);
    }

    /**
     * Ghi đè phương thức render() của lớp cha Entity.
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
