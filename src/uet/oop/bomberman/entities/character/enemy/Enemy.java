package uet.oop.bomberman.entities.character.enemy;

import uet.oop.bomberman.entities.LayerEntity;
import uet.oop.bomberman.entities.MovingEntity;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public abstract class Enemy extends MovingEntity {
    private Sprite _sprite = Sprite.player_right;
    protected Sprite[] spriteList = new Sprite[7];

    protected final int leftSprite = 0;
    protected final int rightSprite = 3;
    protected final int deadSprite = 6;
    private int _direction;

    private int _x;
    private int _y;
    private int randomSeed = 0;

    public Enemy(int x, int y) {
        super(x, y);
        _x = x * Sprite.SCALED_SIZE;
        _y = y * Sprite.SCALED_SIZE;
        this.speed = 1;
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

    protected void calculateMove() {
        int addX = 0;
        int addY = 0;

        if (randomSeed == 0) {
            Random generator = new Random();
            // System.out.println(generator.nextInt());
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
        LayerEntity entity = board.get(_x + addX, _y + addY);
        if (entity == null) {
            _x += addX;
            _y += addY;
        } else randomSeed = 0;
    }

    private void chooseSprite() {
        if (isKilled) {
            _sprite = spriteList[deadSprite];
        } else {
            switch (_direction) {
                case MovingEntity.directionUp:
                case MovingEntity.directionLeft:
                    _sprite = spriteList[leftSprite];
                    if (isMoving) {
                        _sprite = Sprite.movingSprite(spriteList[leftSprite], spriteList[leftSprite + 1], spriteList[leftSprite + 2], animate, 20);
                    }
                    break;
                case MovingEntity.directionDown:
                case MovingEntity.directionRight:
                    _sprite = spriteList[rightSprite];
                    if (isMoving) {
                        _sprite = Sprite.movingSprite(spriteList[rightSprite], spriteList[rightSprite + 1], spriteList[rightSprite + 2], animate, 20);
                    }
                    break;
                default:
                    break;
            }
        }
    }
}