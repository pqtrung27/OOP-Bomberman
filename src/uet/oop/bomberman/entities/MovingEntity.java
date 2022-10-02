package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public abstract class MovingEntity extends Entity {

    protected int speed = 1;
    public static final int directionUp = 1;
    public static final int directionDown = 2;
    public static final int directionLeft = 3;
    public static final int directionRight = 0;
    public MovingEntity(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    private void addX(int add) {
        this.x += add*Sprite.SCALED_SIZE;
    }
    private void addY(int add) {
        this.y += add*Sprite.SCALED_SIZE;
    }

    public boolean canMove(int x, int y) {
        return true;
    }
    public void move(int direction) {
        switch (direction) {
            case directionUp:
                if (canMove(x, y-speed)) addY(-speed);
                break;
            case directionDown:
                if (canMove(x, y+speed)) addY(speed);
                break;
            case directionLeft:
                if (canMove(x-speed, y)) addX(-speed);
                break;
            case directionRight:
                if (canMove(x+speed, y)) addX(speed);
                break;
            default:
                break;
        }
    }
}
