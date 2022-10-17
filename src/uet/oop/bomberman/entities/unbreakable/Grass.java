package uet.oop.bomberman.entities.unbreakable;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Grass extends Entity {

    private int topX;
    private int topY;
    private int botX;
    private int botY;

    public Grass(int xUnit, int yUnit) {
        super(xUnit, yUnit, Sprite.grass.getFxImage());
    }


    public void setTopX(double x) {
        this.topX = (int) x;
    }

    @Override
    public int getTopX() {
        return this.topX;
    }

    public void setTopY(double y) {
        this.topY = (int) y;
    }

    @Override
    public int getTopY() {
        return this.topY;
    }

    public void setBotX(double botX) {
        this.botX = (int) botX;
    }

    @Override
    public int getBotX() {
        return this.botX;
    }

    public void setBotY(double botY) {
        this.botY = (int) botY;
    }

    @Override
    public int getBotY() {
        return this.botY;
    }

    @Override
    public void update() {

    }
}
