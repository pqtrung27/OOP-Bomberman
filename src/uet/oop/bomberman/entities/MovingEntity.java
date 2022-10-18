package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Timer;
import java.util.TimerTask;

public abstract class MovingEntity extends Entity {
    protected int animate = 0;
    protected final int animate_MAX_VALUE = 7500;

    protected double speed = 3.0;
    public static final int directionNone = 0;
    public static final int directionUp = 1;
    public static final int directionRight = 2;
    public static final int directionDown = 3;
    public static final int directionLeft = 4;
    protected boolean isMoving;
    protected boolean isDead;
    protected boolean isKilled;

    protected int bombRange = 1;
    private int bombCount = 0;

    public MovingEntity(int xUnit, int yUnit) {
        super(xUnit, yUnit);
    }

    public MovingEntity(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void kill() {
        if (isKilled || isDead) return;
        isKilled = true;

        (new Timer()).schedule(new TimerTask() {
            @Override
            public void run() {
                isDead = true;
            }
        }, 750L);
    }

    public int getBombCount() {
        return bombCount;
    }

    public void setBombCount(int bombCount) {
        this.bombCount = bombCount;
    }

    public int getBombRange() {
        return bombRange;
    }

    public void setBombRange(int bombRange) {
        this.bombRange = bombRange;
    }

    public boolean isDead() {
        return isDead;
    }

    protected void animate() {
        if (animate < animate_MAX_VALUE) {
            animate++;
        } else animate = 0;
    }
}
