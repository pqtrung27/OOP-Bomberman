package uet.oop.bomberman.entities.character.enemy;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Enemy;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.util.StdRandom;

public class Ballom extends Enemy {
    public Ballom(int x, int y) {
        super(x, y);
        point = 100;
        super.spriteList = new Sprite[]{
                Sprite.ballom_left1,
                Sprite.ballom_left2,
                Sprite.ballom_left3,
                Sprite.ballom_right1,
                Sprite.ballom_right2,
                Sprite.ballom_right3,
                Sprite.ballom_dead,
                Sprite.ballom_score
        };
        super.speed = 0.5 + (double) ((int) (StdRandom.uniformDouble() * 10)) / 10;
        super.img = spriteList[0].getFxImage();
        super.spriteOffsetTop = 0;
        super.spriteOffsetBot = 0;
    }

    @Override
    protected void calculateMove() {
        if (this._direction == 0) {
            calMoveForNone();
        } else {
            if ((x + speed) % Sprite.SCALED_SIZE <= speed * 2 && (y + speed) % Sprite.SCALED_SIZE <= speed * 2) {
                super.randomMovement();
            }
            super.move();
        }
    }

    @Override
    protected boolean canPass(Entity entity) {
        return entity.canBePassed();
    }

}
