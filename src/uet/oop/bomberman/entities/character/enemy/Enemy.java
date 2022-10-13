package uet.oop.bomberman.entities.character.enemy;

import uet.oop.bomberman.entities.MovingEntity;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Date;
import java.util.Random;

public abstract class Enemy extends MovingEntity {
    private Sprite _sprite = Sprite.player_right;
    protected Sprite[] spriteList = new Sprite[7];

    protected final int leftSprite = 0;
    protected final int rightSprite = 3;
    protected final int deadSprite = 6;
    private int _direction;

    private boolean isBlocked = false;
    private int _x;
    private int _y;

    public Enemy(int x, int y) {
        super(x, y);
        _x = x * Sprite.SCALED_SIZE;
        _y = y * Sprite.SCALED_SIZE;
        this.isDead = false;
    }

    @Override
    public void update() {
        animate();
        chooseSprite();
        this.img = _sprite.getFxImage();
        if (!isKilled && !isDead) {
            calculateMove();
            this.x = _x;
            this.y = _y;
        }
    }

    public void set_direction(int _direction) {
        this._direction = _direction;
    }

    public int get_direction() {
        return this._direction;
    }

    protected void calculateMove() {
        int addX = 0;
        int addY = 0;

        if (this._direction == 0 || !canMove(this._direction)) isBlocked = true;

        if (this.isOneal()) {
            if (_x % Sprite.SCALED_SIZE == 0 && _y % Sprite.SCALED_SIZE == 0) {
                int temp = board.EnemyCalDirection(this);
                if (temp != 0)
                    _direction = temp;
            }
        }

        if (isBlocked) {
            for (int i = 1; i <= 4; ++i) {
                if (canMove(i)) {
                    _direction = i;
                    break;
                }
            }
            isBlocked = false;
        }

        if (_direction == directionUp) addY--;
        if (_direction == directionDown) addY++;
        if (_direction == directionLeft) addX--;
        if (_direction == directionRight) addX++;


        if (addX != 0 || addY != 0) {
            move(addX * speed, addY * speed);
        }
    }

    private boolean canMove(int direction) {
        int addX = 0;
        int addY = 0;
        if (direction == directionUp) addY--;
        if (direction == directionDown) addY++;
        if (direction == directionLeft) addX--;
        if (direction == directionRight) addX++;
        if (board.getEntityCollideWith(this, addX * speed, addY * speed) == null) {
            return true;
        }
        return false;
    }

    public void move(int addX, int addY) {
        _x += addX;
        _y += addY;
    }

    private void chooseSprite() {
        if (isKilled) {
            _sprite = spriteList[deadSprite];
        } else {
            switch (_direction) {
                case MovingEntity.directionUp:
                case MovingEntity.directionLeft:
                    _sprite = Sprite.movingSprite(spriteList[leftSprite], spriteList[leftSprite + 1], spriteList[leftSprite + 2], animate, 20);
                    break;
                case MovingEntity.directionDown:
                case MovingEntity.directionRight:
                    _sprite = Sprite.movingSprite(spriteList[rightSprite], spriteList[rightSprite + 1], spriteList[rightSprite + 2], animate, 20);
                    break;
                default:
                    break;
            }
        }
    }
}