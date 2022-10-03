package uet.oop.bomberman.entities;

import uet.oop.bomberman.Controller;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Enemy extends MovingEntity {

    private final int BALLON = 1;
    private final int ONEAL = 2;
    private Sprite _sprite = Sprite.player_right;
    private int _direction;
    private boolean isMoving;

    private int _x;
    private int _y;

    private int _type;

    private int randomSeed = 0;

    public Enemy(int x, int y, Sprite sprite, int type) {
        super(x, y, sprite.getFxImage());
        _x = x * Sprite.SCALED_SIZE;
        _y = y * Sprite.SCALED_SIZE;
        _type = type;
    }

    @Override
    public void update() {
        animate();
        calculateMove();
        chooseSprite();
        this.x = _x;
        this.y = _y;
        this.img = _sprite.getFxImage();
    }

    protected void calculateMove() {
        int addX = 0;
        int addY = 0;

        if (randomSeed == 0) {
            Random generator = new Random();
            System.out.println(generator.nextInt());
            _direction = generator.nextInt(4) + 1;
            randomSeed = 100;
        } else randomSeed--;

        if (_direction == directionUp) addY--;
        if (_direction == directionDown) addY++;
        if (_direction == directionLeft) addX--;
        if (_direction == directionRight) addX++;


        if (addX != 0 || addY != 0) {
            move(addX * speed, addY * speed);
        } else isMoving = false;
    }

    public void move(int addX, int addY) {
        isMoving = true;
        if (canMove(_x + addX, _y + addY)) {
            _x += addX;
            _y += addY;
        }
    }

    private void chooseSprite() {
        switch (_direction) {
            case MovingEntity.directionUp:
                _sprite = Sprite.player_up;
                if (isMoving) {
                    _sprite = Sprite.movingSprite(Sprite.player_up_1, Sprite.player_up_2, animate, 10);
                }
                break;
            case MovingEntity.directionDown:
                _sprite = Sprite.player_down;
                if (isMoving) {
                    _sprite = Sprite.movingSprite(Sprite.player_down_1, Sprite.player_down_2, animate, 10);
                }
                break;
            case MovingEntity.directionLeft:
                _sprite = Sprite.player_left;
                if (isMoving) {
                    _sprite = Sprite.movingSprite(Sprite.player_left_1, Sprite.player_left_2, animate, 10);
                }
                break;
            case MovingEntity.directionRight:
                _sprite = Sprite.player_right;
                if (isMoving) {
                    _sprite = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2, animate, 10);
                }
                break;
            default:
                break;
        }
    }
}