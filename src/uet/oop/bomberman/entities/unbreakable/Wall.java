package uet.oop.bomberman.entities.unbreakable;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Wall extends Entity {



    public Wall(int xUnit, int yUnit) {
        super(xUnit, yUnit, Sprite.wall.getFxImage());
        this.spriteOffsetTop = 0;
        this.spriteOffsetBot = 0;
        this.spriteOffsetLeft = 0;
        this.spriteOffsetRight = 0;
    }

    @Override
    public void update() {

    }
}
