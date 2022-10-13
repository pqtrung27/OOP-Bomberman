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
        super.speed = 1;
        super.spriteOffsetTop = 2 * 3;
        super.spriteOffsetBot = 2 * 3;
        super.spriteOffsetLeft = 1 * 3;
        super.spriteOffsetRight = 1 * 3;
    }
}
