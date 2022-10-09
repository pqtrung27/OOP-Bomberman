package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.character.Bomber;

import javax.swing.border.Border;
import java.util.Stack;

/**
 * Lớp cài đặt các đối tượng "Layer" dùng để lưu map màn chơi.
 *
 * @author TTD
 */
public class LayerEntity extends Entity {
    Stack<Entity> stack = new Stack<>();

    public LayerEntity(int x, int y) {
        super(x, y);
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
     * Ghi đè phương thức render() của lớp cha Entity.
     * Render đối tượng trên cùng của ô layer.
     * @param gc GraphicsContext
     */
    @Override
    public void render(GraphicsContext gc) {
        stack.peek().render(gc);
    }

    /**
     * Ghi đè phương thức update() của lớp cha Entity().
     * Cập nhật đối tượng trên cùng của ô layer.
     * Nếu đối tượng trên cùng đã bị phá hủy, loại bỏ đối tượng trên cùng.
     */
    @Override
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
