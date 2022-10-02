package uet.oop.bomberman.entities;

import uet.oop.bomberman.graphics.Sprite;

public class Wall extends Entity {
    public Wall(int xUnit, int yUnit) {
        super(xUnit, yUnit, Sprite.wall.getFxImage());
    }

    @Override
    public void update() {

    }
}
