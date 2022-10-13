package uet.oop.bomberman.entities.unbreakable;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Wall extends Entity {

    private int botX;
    private int botY;
    public Wall(int xUnit, int yUnit) {
        super(xUnit, yUnit, Sprite.wall.getFxImage());
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
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
