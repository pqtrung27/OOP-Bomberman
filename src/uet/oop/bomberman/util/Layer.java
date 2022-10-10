package uet.oop.bomberman.util;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.BreakableEntity;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.breakable.Brick;
import uet.oop.bomberman.entities.breakable.Portal;
import uet.oop.bomberman.entities.breakable.item.Item;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.unbreakable.Grass;
import uet.oop.bomberman.entities.unbreakable.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Stack;

/**
 * Lớp cài đặt các đối tượng "Layer" dùng để lưu map màn chơi.
 *
 * @author TTD
 */
public class Layer {
    private int x;
    private int y;
    public Stack<Entity> stack = new Stack<>();

    public Layer(int xUnit, int yUnit) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * Thêm 1 entity vào ô layer.
     */
    public void add(Entity entity) {
        stack.add(entity);
    }

    /**
     * Phương thức được gọi nếu ô layer nằm trong phạm vi nổ của Bomb.
     * Phá hủy đối tượng trên cùng của ô layer nếu có thể.
     * @return true nếu có thể phá hủy, false nếu không thể phá hủy
     */
    public boolean destroy() {
        if (isBreakable()) {
            BreakableEntity top = (BreakableEntity) stack.peek();
            top.breakEntity();
            return true;
        }
        return false;
    }

    /**
     * Phương thức kiểm tra đối tượng trên cùng của stack.
     */
    public boolean isWall() {
        return stack.peek() instanceof Wall;
    }

    public boolean isGrass() {
        return stack.peek() instanceof Grass;
    }

    public boolean isPortal() {
        return stack.peek() instanceof Portal;
    }

    public boolean isBreakable() {
        return stack.peek() instanceof BreakableEntity;
    }

    public boolean isItem() {
        return stack.peek() instanceof Item;
    }

    public boolean isBrick() {
        return stack.peek() instanceof Brick;
    }

    public boolean canBePassed() {
        return !isBrick() && !isWall();
    }

    /**
     * Chỉ có thể được sử dụng khi đối tượng trên cùng là một vật phẩm.
     */
    public void powerUp(Bomber bomber) {
        if (!isItem()) {
            return;
        }
        Item top = (Item) stack.peek();
        top.powerUp(bomber);
    }

    /**
     * Render đối tượng trên cùng của ô layer.
     * @param gc GraphicsContext
     */
    public void render(GraphicsContext gc) {
        stack.peek().render(gc);
    }

    /**
     * Cập nhật đối tượng trên cùng của ô layer.
     * Nếu đối tượng trên cùng đã bị phá hủy, loại bỏ đối tượng trên cùng.
     */
    public void update() {
        if (isBreakable()) {
            BreakableEntity top = (BreakableEntity) stack.peek();
            if (top.isBroken()) {
                stack.pop();
            }
        }
        stack.peek().update();
    }
}
