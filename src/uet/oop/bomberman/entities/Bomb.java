package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Lớp cài đặt đối tượng Bomb.
 *
 * @author TTD
 */
public class Bomb extends BreakableEntity {
    private Sprite sprite;
    private boolean waiting;
    private List<Flame> flames = new ArrayList<>();

    /**
     * Khởi tạo đối tượng sử dụng phương thức khởi tạo của lớp cha BreakableEntity.
     * Gán trạng thái đang đợi cho đối tượng.
     * Cài đặt Timer, hủy trạng thái đang đợi sau 2s.
     * Cài đặt Timer, gán trạng thái đã bị phá hủy cho đối tượng sau khi hủy trạng thái đang đợi 750ms.
     */
    public Bomb(int xUnit, int yUnit) {
        super(xUnit, yUnit, Sprite.bomb.getFxImage());
        sprite = Sprite.bomb;
        waiting = true;

        Timer timer = new Timer();
        // Hủy trạng thái đang đợi
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                waiting = false;
                explode();
            }
        }, 2000L);
        // Gán trạng thái đã bị phá hủy
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                breakEntity();
            }
        }, 2750L);
    }

    /**
     * Bomb nổ, phá hủy những đối tượng Brick xung quanh nó.
     */
    private void explode() {
        flames.add(new Flame((this.x - 1) / Sprite.SCALED_SIZE, this.y / Sprite.SCALED_SIZE, Flame.horizontalLeft));
    }

    @Override
    public void update() {
        animate();
        if (waiting) {
            sprite = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, animate, 30);
        } else if (!isBroken) {
            sprite = Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2, animate, 50);
        }
        this.img = sprite.getFxImage();
    }

    /**
     * Ghi đè phương thức render(), render thêm đối tượng Flame khi Bomb đang nổ.
     * @param gc GraphicsContext
     */
    @Override
    public void render(GraphicsContext gc) {
        flames.forEach(f -> f.render(gc));
        super.render(gc);
    }
}
