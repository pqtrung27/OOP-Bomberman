package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends MovingEntity{

    private Image _sprite;
    private int _direction;
    private boolean isMoving;

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
        _sprite = img;
    }

    @Override
    public void update() {
        System.out.println(_sprite);
        this.img = _sprite;
    }

    @Override
    public void move(int direction) {
        super.move(direction);
        this._direction = direction;
        chooseSprite();
        isMoving = true;
        //if (_direction == DirectionNone) isMoving = false;
    }

    private void chooseSprite() {
        switch (_direction) {
            case MovingEntity.directionUp:
                _sprite = Sprite.player_up.getFxImage();
                break;
            case MovingEntity.directionDown:
                _sprite = Sprite.player_down.getFxImage();
                break;
            case MovingEntity.directionLeft:
                _sprite = Sprite.player_left.getFxImage();
                break;
            case MovingEntity.directionRight:
                _sprite = Sprite.player_right.getFxImage();
                break;
            default:
                _sprite = Sprite.player_right.getFxImage();
                break;
        }
        update();
    }
}
