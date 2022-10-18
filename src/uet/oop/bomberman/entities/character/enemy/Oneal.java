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
        super.speed = 1 + (double) ((int) (ran.nextDouble() * 10)) / 10;
        super.img = spriteList[0].getFxImage();
    }

    @Override
    protected void calculateMove() {
        if (this._direction == 0) {
            calMoveForNone();
        } else {
            if (this._direction == 0 || !canMove(this._direction)) isBlocked = true;
            if (_x % Sprite.SCALED_SIZE == 0 && _y % Sprite.SCALED_SIZE == 0) {
                int temp = board.EnemyCalDirection(this);
                if (temp != 0) _direction = temp;
                else if (isBlocked) super.randomMovement();
            }
        }
        super.calculateMove();
    }
}
