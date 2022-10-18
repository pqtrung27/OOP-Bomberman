package uet.oop.bomberman.entities.character.enemy;

import uet.oop.bomberman.graphics.Sprite;

import java.util.Date;
import java.util.Random;

public class Doll extends Enemy {

    private final double initSpeed;
    private double accel;

    public Doll(int x, int y) {
        super(x, y);
        point = 400;
        super.spriteList = new Sprite[]{
                Sprite.doll_left1,
                Sprite.doll_left2,
                Sprite.doll_left3,
                Sprite.doll_right1,
                Sprite.doll_right2,
                Sprite.doll_right3,
                Sprite.doll_dead,
                Sprite.doll_score
        };
        Random ran = new Random((new Date()).getTime() + (long) Math.ceil(_x) + (long) Math.ceil(_y));
        super.speed = 1 + (double) ((int) (ran.nextDouble() * 10)) / 10;
        super.img = spriteList[0].getFxImage();
        this._direction = 0;
        initSpeed = speed;
        accel = initSpeed;
    }

    private int reverseDirection(int dir) {
        switch (dir) {
            case directionUp:
                return directionDown;
            case directionDown:
                return directionUp;
            case directionLeft:
                return directionRight;
            case directionRight:
                return directionLeft;
            default:
                return 0;
        }
    }

    @Override
    protected void calculateMove() {
        if (this._direction == 0){
            calMoveForNone();
        } else {
            if (!canMove(this._direction)) {
                accel = initSpeed;
                _direction = reverseDirection(_direction);
            }
            this.speed = initSpeed + accel;
            if (accel > 0) accel -= 0.1;
            else accel = 0;
            super.calculateMove();
        }
    }
}
