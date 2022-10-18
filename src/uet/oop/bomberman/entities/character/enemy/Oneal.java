package uet.oop.bomberman.entities.character.enemy;

import uet.oop.bomberman.graphics.Sprite;

import java.util.Date;
import java.util.Random;

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
        Random ran = new Random((new Date()).getTime() + (long) Math.ceil(_x) + (long) Math.ceil(_y));
        super.speed = 0.6 + (double) ((int) (ran.nextDouble() * 10)) / 10;
        if (speed == 1.5) speed = 1.4;
        super.img = spriteList[0].getFxImage();
    }

    @Override
    protected void calculateMove() {
        aiMovement();
    }
}
