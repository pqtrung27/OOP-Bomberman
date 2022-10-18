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
                Sprite.minvo_score
        };
        Random ran = new Random((new Date()).getTime() + (long) Math.ceil(_x) + (long) Math.ceil(_y));
        super.speed = 1.6 + (double) ((int) (ran.nextDouble() * 10)) / 10;
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
