package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.breakable.Bomb;
import uet.oop.bomberman.entities.breakable.Brick;
import uet.oop.bomberman.entities.breakable.Portal;
import uet.oop.bomberman.entities.breakable.item.Item;
import uet.oop.bomberman.entities.unbreakable.Grass;
import uet.oop.bomberman.entities.unbreakable.Wall;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.util.BoardState;

import java.util.Comparator;

public abstract class Entity {
    public static BoardState board;

    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;
    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;

    protected Image img;

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity(int xUnit, int yUnit) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
    }

    public Entity(int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x - board.boardOffsetX, y - board.boardOffsetY);
    }

    public boolean isWall() {
        return this instanceof Wall;
    }

    public boolean isGrass() {
        return this instanceof Grass;
    }

    public boolean isPortal() {
        return this instanceof Portal;
    }

    public boolean isBreakable() {
        return this instanceof BreakableEntity;
    }

    public boolean isItem() {
        return this instanceof Item;
    }

    public boolean isBrick() {
        return this instanceof Brick;
    }

    public boolean isBomb() {
        return this instanceof Bomb;
    }

    public boolean canBePassed() {
        return !isBrick() && !isWall() && !isBomb();
    }

    public abstract void update();
}
