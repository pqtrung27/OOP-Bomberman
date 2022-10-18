package uet.oop.bomberman.entities.character.enemy;

import uet.oop.bomberman.graphics.Sprite;

import java.util.Date;
import java.util.Random;

public class Ballon extends Enemy {
    public Ballon(int x, int y) {
        super(x, y);
        point = 100;
        super.spriteList = new Sprite[]{
                Sprite.balloom_left1,
                Sprite.balloom_left2,
                Sprite.balloom_left3,
                Sprite.balloom_right1,
                Sprite.balloom_right2,
                Sprite.balloom_right3,
                Sprite.balloom_dead,
                Sprite.balloom_score
        };
        Random ran = new Random((new Date()).getTime() + (long) Math.ceil(_x) + (long) Math.ceil(_y));
        super.speed = 0.5 + (double) ((int) (ran.nextDouble() * 10)) / 10;
        super.img = spriteList[0].getFxImage();
        super.spriteOffsetTop = 0;
        super.spriteOffsetBot = 0;
    }

    @Override
    protected void calculateMove() {
        if (this._direction == 0){
            calMoveForNone();
        } else {
            if (Math.floor(_x) % Sprite.SCALED_SIZE == 0 && Math.floor(_y) % Sprite.SCALED_SIZE == 0) {
                super.randomMovement();
            }
            super.calculateMove();
        }
    }

}
