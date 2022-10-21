package uet.oop.bomberman.entities.character.enemy;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Enemy;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.util.StdRandom;

public class Oneal extends Enemy {
    public Oneal(int x, int y) {
        super(x, y);
        point = 200;
        super.spriteList = new Sprite[]{
                Sprite.oneal_left1,
                Sprite.oneal_left2,
                Sprite.oneal_left3,
                Sprite.oneal_right1,
                Sprite.oneal_right2,
                Sprite.oneal_right3,
                Sprite.oneal_dead,
                Sprite.oneal_score
        };
        super.speed = 1 + (double) ((int) ((StdRandom.uniformDouble()/3) * 10)) / 10;
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
