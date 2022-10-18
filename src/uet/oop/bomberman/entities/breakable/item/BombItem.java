package uet.oop.bomberman.entities.breakable.item;

import uet.oop.bomberman.entities.breakable.Bomb;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.graphics.Sprite;

public class BombItem extends Item {
    public BombItem(int xUnit, int yUnit) {
        super(xUnit, yUnit, Sprite.powerup_bombs.getFxImage());
    }

    public boolean powerUp(Bomber bomberman) {
        if (isBroken()) {
            return false;
        }
        if (bomberman.getMaxBombCount() < 3) {
            bomberman.setMaxBombCount(bomberman.getMaxBombCount()+1);
        }
        breakEntity();
        return true;
    }
}
