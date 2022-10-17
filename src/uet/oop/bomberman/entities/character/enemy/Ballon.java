package uet.oop.bomberman.entities.character.enemy;

import uet.oop.bomberman.graphics.Sprite;

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
                Sprite.balloom_dead
        };
        super.img = spriteList[0].getFxImage();
        super.speed = 1;
        super.spriteOffsetTop = 2 * 3;
        super.spriteOffsetBot = 2 * 3;
    }
}
