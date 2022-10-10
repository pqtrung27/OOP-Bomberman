package uet.oop.bomberman.entities.breakable;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.BreakableEntity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.util.Layer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Lớp cài đặt đối tượng Bomb.
 *
 * @author TTD
 */
public class Bomb extends BreakableEntity {
    public static int range = 1;
    public static int maxBombNum = 1;
    private Sprite sprite;
    private boolean waiting;
    private boolean justLay;

    /**
     * Khởi tạo đối tượng sử dụng phương thức khởi tạo của lớp cha BreakableEntity.
     * Gán trạng thái đang đợi cho đối tượng.
     * Cài đặt Timer, hủy trạng thái đang đợi sau 2s.
     */
    public Bomb(int xUnit, int yUnit) {
        super(xUnit, yUnit, Sprite.bomb.getFxImage());
        sprite = Sprite.bomb;
        waiting = true;
        justLay = true;

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
     * Phương thức tăng số lượng bomb có thể đặt.
     * Có thể được sử dụng sau khi Bomber nhận power-up BombItem.
     * Số Bomb lớn nhất có thể đặt = 3.
     */
    public static void increaseBombNum() {
        if (Bomb.maxBombNum < 3) {
            ++maxBombNum;
        }
    }

    public static void reset() {
        maxBombNum = 1;
        range = 1;
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
                Layer entity = board.getLayer(curX * Sprite.SCALED_SIZE, curY * Sprite.SCALED_SIZE);
                if (entity != null) {
                    if (entity.stack.peek().isWall()) {
                        break;
                    }
                    if (entity.stack.peek().isBreakable()) {
                        board.flames.add(new Flame(curX, curY, i, true));
                        entity.destroyTop();
                        //System.out.println(curX + " " + curY + " " + (curY * LoadLevel.nCol + curX));
                        break;
                    }
                }
                board.flames.add(new Flame(curX, curY, i, k == range));
            }
        }
    }

    public boolean isJustLay() {
        return justLay;
    }

    public void setJustLay(boolean justLay) {
        this.justLay = justLay;
    }

    @Override
    public void update() {
        if (isBroken) {
            board.bombs.remove(this);
            return;
        }
        animate();
        if (isExploding) {
            sprite = Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2, animate, 35);
        } else if (waiting) {
            sprite = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, animate, 30);
        }
        this.img = sprite.getFxImage();
    }

    /**
     * Ghi đè phương thức render(), render thêm đối tượng Flame khi Bomb đang nổ.
     *
     * @param gc GraphicsContext
     */
    @Override
    public void render(GraphicsContext gc) {
        super.render(gc);
    }
}