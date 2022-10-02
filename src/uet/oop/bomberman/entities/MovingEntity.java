package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public abstract class MovingEntity extends Entity {


    protected int animate = 0;
    protected final int animate_MAX_VALUE = 7500;

    protected int speed = 1;
    public static final int directionNone = 0;
    public static final int directionUp = 1;
    public static final int directionDown = 2;
    public static final int directionLeft = 3;
    public static final int directionRight = 4;

    public MovingEntity(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public boolean canMove(int x, int y) {
        return true;
    }

    protected void animate() {
        if (animate < animate_MAX_VALUE) {
            animate++;
        } else animate = 0;
    }
}