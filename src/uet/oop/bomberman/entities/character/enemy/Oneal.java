package uet.oop.bomberman.entities.character.enemy;

import uet.oop.bomberman.graphics.Sprite;

import java.util.Date;
import java.util.Random;

public class Oneal extends Enemy {
    public Oneal(int x, int y) {
        super(x, y);
        super.spriteList = new Sprite[]{
                Sprite.oneal_left1,
                Sprite.oneal_left2,
                Sprite.oneal_left3,
                Sprite.oneal_right1,
                Sprite.oneal_right2,
                Sprite.oneal_right3,
                Sprite.oneal_dead
        };
        super.img = spriteList[0].getFxImage();
        Random generator = new Random(Math.abs((new Date()).getTime()));
        super.speed = generator.nextInt(2) + 1;
    }
}
