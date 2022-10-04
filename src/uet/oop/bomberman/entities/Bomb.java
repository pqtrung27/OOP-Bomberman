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
    private static int range = 1;
    private Sprite sprite;
    private boolean waiting;
    private List<Flame> flames = new ArrayList<>();

    /**
     * Khởi tạo đối tượng sử dụng phương thức khởi tạo của lớp cha BreakableEntity.
     * Gán trạng thái đang đợi cho đối tượng.
     * Cài đặt Timer, hủy trạng thái đang đợi sau 2s.
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
    }

    /**
     * Phương thức tăng phạm vi nổ của bomb.
     * Có thể được sử dụng sau khi Bomber nhận power-up FlameItem.
     * Phạm vi nổ lớn nhất của bomb = 3.
     */
    public static void increaseRange() {
        if (Bomb.range < 3) {
            ++Bomb.range;
        }
    }

    /**
     * Bomb nổ, phá hủy những đối tượng Brick xung quanh nó.
     */
    private void explode() {
        breakEntity();
        int xUnit = this.x / Sprite.SCALED_SIZE;
        int yUnit = this.y / Sprite.SCALED_SIZE;

        int[] addX = {-1, 1, 0, 0};
        int[] addY = {0, 0, -1, 1};
        for (int i = 0; i < addX.length; ++i) {
            for (int k = 1; k <= range; ++k) {
                int curX = xUnit + addX[i] * k;
                int curY = yUnit + addY[i] * k;
                flames.add(new Flame(curX, curY, i, k == range));
            }
        }
    }

    @Override
    public void update() {
        animate();
        if (waiting) {
            sprite = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, animate, 30);
        } else if (isExploding) {
            sprite = Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2, animate, 35);
            flames.forEach(Flame::update);
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
