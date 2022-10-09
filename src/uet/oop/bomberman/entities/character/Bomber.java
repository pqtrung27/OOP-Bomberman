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
    private boolean isMoving;

    private int _x;
    private int _y;

    public Bomber(int x, int y) {
        super(x, y, Sprite.player_right.getFxImage());
        _x = x * Sprite.SCALED_SIZE;
        _y = y * Sprite.SCALED_SIZE;
    }

    @Override
    public void update() {
        powerUp();

        animate();
        calculateMove();
        chooseSprite();
        this.x = _x;
        this.y = _y;
        this.img = _sprite.getFxImage();
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
            if (entity.y() >= _y + Sprite.SCALED_SIZE - 31) {
                addY = -2;
            } else if (entity.y() + Sprite.SCALED_SIZE - 31 <= _y) {
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
            if (entity.x() >= _x + Sprite.SCALED_SIZE - 31) {
                addX = -2;
            } else if (entity.x() + Sprite.SCALED_SIZE - 31 <= _x) {
                addX = 2;
            }
            move(addX, addY);
        }
    }

    private void chooseSprite() {
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