package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.Controller;

import java.util.List;

public class Bomber extends MovingEntity {

    private Sprite _sprite;
    private int _direction;
    private boolean isMoving;

    private int _x;
    private int _y;

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
        _sprite = Sprite.player_right;
        _x = this.x;
        _y = this.y;
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
        if (Controller.direction[directionUp]) {
            addY--;
            _direction = directionUp;
        }
        if (Controller.direction[directionDown]) {
            addY++;
            _direction = directionDown;
        }
        if (Controller.direction[directionLeft]) {
            addX--;
            _direction = directionLeft;
        }
        if (Controller.direction[directionRight]) {
            addX++;
            _direction = directionRight;
        }

        if (addX != 0 || addY != 0) {
            move(addX * speed, addY * speed);
        } else isMoving = false;
    }

    public void move(int addX, int addY) {
        isMoving = true;
        if (canMove(x + addX, y + addY)) {
            _x += addX;
            _y += addY;
        }
    }

    @Override
    public boolean canMove(int x, int y) {
        if (_direction == directionLeft) {
            return canPass(x, y) && canPass(x, y + Sprite.SCALED_SIZE - 1);
        }
        if (_direction == directionUp) {
            return canPass(x, y) && canPass(x + Sprite.SCALED_SIZE - 1, y);
        }
        if (_direction == directionRight) {
            return canPass(x + Sprite.SCALED_SIZE - 1, y)
                    && canPass(x + Sprite.SCALED_SIZE - 1, y + Sprite.SCALED_SIZE - 1);
        }
        if (_direction == directionDown) {
            return canPass(x + Sprite.SCALED_SIZE - 1, y + Sprite.SCALED_SIZE - 1)
                    && canPass(x,  y +Sprite.SCALED_SIZE - 1);
        }
        return true;
    }

    private boolean canPass(int x, int y) {
        int xUnit = x / Sprite.SCALED_SIZE;
        int yUnit = y / Sprite.SCALED_SIZE;
        int id = yUnit * LoadLevel.nCol + xUnit;
        Entity obstacle = BombermanGame.stillObjects.get(id);
        if (obstacle instanceof Wall) {
            return false;
        }
        if (obstacle instanceof BreakableEntity && !((BreakableEntity) obstacle).isBroken()) {
            return false;
        }
        return true;
    }

    private void chooseSprite() {
        switch (_direction) {
            case MovingEntity.directionUp:
                _sprite = Sprite.player_up;
                if (isMoving) {
                    _sprite = Sprite.movingSprite(Sprite.player_up_1,Sprite.player_up_2, animate, 15);
                }
                break;
            case MovingEntity.directionDown:
                _sprite = Sprite.player_down;
                if (isMoving) {
                    _sprite = Sprite.movingSprite(Sprite.player_down_1,Sprite.player_down_2, animate, 15);
                }
                break;
            case MovingEntity.directionLeft:
                _sprite = Sprite.player_left;
                if (isMoving) {
                    _sprite = Sprite.movingSprite(Sprite.player_left_1,Sprite.player_left_2, animate, 15);
                }
                break;
            case MovingEntity.directionRight:
                _sprite = Sprite.player_right;
                if (isMoving) {
                    _sprite = Sprite.movingSprite(Sprite.player_right_1,Sprite.player_right_2, animate, 15);
                }
                break;
            default:
                break;
        }
    }
}