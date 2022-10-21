package uet.oop.bomberman.entities.character.enemy;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Enemy;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.util.StdRandom;
public class Minvo extends Enemy {
    public Minvo(int x, int y) {
        super(x, y);
        point = 800;
        super.spriteList = new Sprite[]{
                Sprite.minvo_left1,
                Sprite.minvo_left2,
                Sprite.minvo_left3,
                Sprite.minvo_right1,
                Sprite.minvo_right2,
                Sprite.minvo_right3,
                Sprite.minvo_dead,
                Sprite.minvo_score
        };
        super.speed = 1.5 + (double) ((int) (StdRandom.uniformDouble()/3 * 10)) / 10;
        if (speed == 1.5) speed = 1.6;
        super.img = spriteList[0].getFxImage();
    }

    @Override
    protected boolean canPass(Entity entity) {
        return entity.canBePassed();
    }

    @Override
    protected void calculateMove() {
        aiMovement();
    }
}
