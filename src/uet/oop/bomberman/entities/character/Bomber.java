package uet.oop.bomberman.entities.character;

import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.breakable.Bomb;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.util.Controller;

public class Bomber extends MovingEntity {

    private final int xPadding = Sprite.SCALED_SIZE * 4;
    private Sprite _sprite = Sprite.player_right;
    private int _direction;
    private int _x;
    private int _y;

    public Bomber(int xUnit, int yUnit) {
        super(xUnit, yUnit, Sprite.player_right.getFxImage());
        _x = xUnit * Sprite.SCALED_SIZE;
        _y = yUnit * Sprite.SCALED_SIZE;
        this.isKilled = false;
        this.isDead = false;
        this.isMoving = false;
    }

    @Override
    public void update() {
        animate();
        chooseSprite();
        this.x = _x;
        this.y = _y;
        this.img = _sprite.getFxImage();
        if (!isKilled && !isDead) {
            powerUp();
            calculateMove();
        }
    }

    protected void powerUp() {
        LayerEntity entity = board.get(this.x, this.y);
        if (entity != null && entity.isItem()) {
            entity.powerUp(this);
        }
    }

    protected void calculateMove() {
        int addX = 0;
        int addY = 0;
        if (Controller.direction[directionUp]) {
            addY--;
            _direction = directionUp;
        } else if (Controller.direction[directionDown]) {
            addY++;
            _direction = directionDown;
        } else if (Controller.direction[directionLeft]) {
            addX--;
            _direction = directionLeft;
        } else if (Controller.direction[directionRight]) {
            addX++;
            _direction = directionRight;
        }

        if (addX != 0 || addY != 0) {
            move(addX * speed, addY * speed);
        } else isMoving = false;

        if (_direction == MovingEntity.directionRight && _x + Sprite.SCALED_SIZE > BombermanGame.initialSceneWidth + board.boardOffset - xPadding) {
            board.boardOffset = Math.min(board.nCol * Sprite.SCALED_SIZE - BombermanGame.initialSceneWidth
                    , _x + Sprite.SCALED_SIZE - BombermanGame.initialSceneWidth + xPadding);
        }
        if (_direction == MovingEntity.directionLeft && x <= xPadding + board.boardOffset) {
            board.boardOffset = Math.max(0, _x - xPadding);
        }
    }

    public void move(int addX, int addY) {
        isMoving = true;
        LayerEntity entity = board.get(_x + addX, _y);
        if (entity == null || entity.canBePassed()) {
            _x += addX;
        } else {
            addX = 0;
            if (entity.getY() >= _y + Sprite.SCALED_SIZE - 31) {
                addY = -2;
            } else if (entity.getY() + Sprite.SCALED_SIZE - 31 <= _y) {
                addY = 2;
            }
            move(addX, addY);
            return;
        }
        entity = board.get(_x, _y + addY);
        if (entity == null || entity.canBePassed()) {
            _y += addY;
        } else {
            addY = 0;
            if (entity.getX() >= _x + Sprite.SCALED_SIZE - 31) {
                addX = -2;
            } else if (entity.getX() + Sprite.SCALED_SIZE - 31 <= _x) {
                addX = 2;
            }
            move(addX, addY);
        }
    }

    private void chooseSprite() {
        if (isKilled) {
            _sprite = Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2, Sprite.player_dead3, animate, 35);
        } else {
            switch (_direction) {
                case MovingEntity.directionUp:
                    _sprite = Sprite.player_up;
                    if (isMoving) {
                        _sprite = Sprite.movingSprite(Sprite.player_up_1, Sprite.player_up_2, animate, 20);
                    }
                    break;
                case MovingEntity.directionDown:
                    _sprite = Sprite.player_down;
                    if (isMoving) {
                        _sprite = Sprite.movingSprite(Sprite.player_down_1, Sprite.player_down_2, animate, 20);
                    }
                    break;
                case MovingEntity.directionLeft:
                    _sprite = Sprite.player_left;
                    if (isMoving) {
                        _sprite = Sprite.movingSprite(Sprite.player_left_1, Sprite.player_left_2, animate, 20);
                    }
                    break;
                case MovingEntity.directionRight:
                    _sprite = Sprite.player_right;
                    if (isMoving) {
                        _sprite = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2, animate, 20);
                    }
                    break;
                default:
                    break;
            }
        }
    }
}