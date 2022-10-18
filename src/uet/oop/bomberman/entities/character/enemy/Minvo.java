package uet.oop.bomberman.entities.character.enemy;

import uet.oop.bomberman.graphics.Sprite;

import java.util.Date;
import java.util.Random;

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
                //Sprite.minvo_score
        };
        Random ran = new Random((new Date()).getTime() + (long) Math.ceil(_x) + (long) Math.ceil(_y));
        super.speed = 1.6 + (double) ((int) (ran.nextDouble() * 10)) / 10;
        if (speed == 2.5) speed = 2.4;
        super.img = spriteList[0].getFxImage();
    }

    @Override
    protected void calculateMove() {
        aiMovement();
    }
}
