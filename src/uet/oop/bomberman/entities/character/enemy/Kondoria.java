package uet.oop.bomberman.entities.character.enemy;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.breakable.Brick;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Date;
import java.util.Random;

public class Kondoria extends Enemy {
    public Kondoria(int x, int y) {
        super(x, y);
        point = 200;
        super.spriteList = new Sprite[]{
                Sprite.kondoria_left1,
                Sprite.kondoria_left2,
                Sprite.kondoria_left3,
                Sprite.kondoria_right1,
                Sprite.kondoria_right2,
                Sprite.kondoria_right3,
                Sprite.kondoria_dead,
                //Sprite.kondoria_score
        };
        Random ran = new Random((new Date()).getTime() + (long) Math.ceil(_x) + (long) Math.ceil(_y));
        super.speed = 0.1 + (double) ((int) (ran.nextDouble()/2 * 10)) / 10;
        if(speed == 0.5) speed = 0.4;
        super.img = spriteList[0].getFxImage();
    }

    @Override
    protected boolean canPass(Entity entity) {
        return entity instanceof Brick;
    }

    @Override
    protected void calculateMove() {
        aiMovement();
    }
}
