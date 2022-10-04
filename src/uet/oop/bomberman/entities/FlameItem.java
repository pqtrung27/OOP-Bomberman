package uet.oop.bomberman.entities;

import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.graphics.Sprite;

public class FlameItem extends Item {
    public FlameItem(int xUnit, int yUnit) {
        super(xUnit, yUnit, Sprite.powerup_flames.getFxImage());
    }

    public boolean powerUp(Bomber bomberman) {
        if (isBroken()) {
            return false;
        }
        Bomb.increaseRange();
        breakEntity();
        return true;
    }
}
