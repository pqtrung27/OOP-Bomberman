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


    public void setTopX(int x) {
        this.topX = x;
    }

    @Override
    public int getTopX() {
        return this.topX;
    }

    public void setTopY(int y) {
        this.topY = y;
    }

    @Override
    public int getTopY() {
        return this.topY;
    }

    public void setBotX(int botX) {
        this.botX = botX;
    }

    @Override
    public int getBotX() {
        return this.botX;
    }

    public void setBotY(int botY) {
        this.botY = botY;
    }

    @Override
    public int getBotY() {
        return this.botY;
    }

    @Override
    public void update() {

    }
}
