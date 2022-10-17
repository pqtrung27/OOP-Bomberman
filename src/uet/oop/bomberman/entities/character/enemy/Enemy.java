package uet.oop.bomberman.entities.character.enemy;

import uet.oop.bomberman.display.BombermanGame;
import uet.oop.bomberman.entities.MovingEntity;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Timer;
import java.util.TimerTask;

public abstract class Enemy extends MovingEntity {
    private Sprite _sprite = Sprite.player_right;
    protected Sprite[] spriteList = new Sprite[8];

    protected final int leftSprite = 0;
    protected final int rightSprite = 3;
    protected final int deadSprite = 6;
    protected final int scoreSprite = 7;
    private int _direction;

    private boolean isBlocked = false;
    private int _x;
    private int _y;
    protected int point; // Số điểm nhận được sau khi giết Enemy, giá trị cụ thể được gán trong phương thức khởi tạo Enemy
    protected boolean isShowingPoint = false;

    public Enemy(int x, int y) {
        super(x, y);
        _x = x * Sprite.SCALED_SIZE;
        _y = y * Sprite.SCALED_SIZE;
        this.isDead = false;
        this.isBlocked = true;
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

    @Override
    public void kill() {
        if (isKilled || isDead) return;
        isKilled = true;
        (new Timer()).schedule(new TimerTask() {
            @Override
            public void run() {
                isShowingPoint = true;
                (new Timer()).schedule(new TimerTask() {
                    @Override
                    public void run() {
                        isDead = true;
                    }
                }, 500);
            }
        }, 750L);
        BombermanGame.addScore(point);
        System.out.println("You killed an enemy! Score +" + point + ". Current score: " + BombermanGame.score);
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
            if (_x % Sprite.SCALED_SIZE <= speed && _y % Sprite.SCALED_SIZE <= speed) {
                int temp = board.EnemyCalDirection(this);
                if (temp != 0)
                    _direction = temp;
                // else System.out.println(temp);
            }
        }

        if (isBlocked) {
            int cnt = 0;
            while(_direction == 0 || !canMove(_direction)) {
                _direction++;
                if (_direction == 5) {
                    _direction = 1;
                }
                cnt++;
                if (cnt == 5) break;
            }
            if (cnt != 5) isBlocked = false;
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
        if (isDead) return;
        if (isShowingPoint) {
            _sprite = spriteList[scoreSprite];
            return;
        }
        if (isKilled) {
            _sprite = spriteList[deadSprite];
        } else {
            switch (_direction) {
                case MovingEntity.directionUp:
                case MovingEntity.directionLeft:
                    _sprite = Sprite.movingSprite(spriteList[leftSprite], spriteList[leftSprite + 1], spriteList[leftSprite + 2], animate, 20);
                    break;
                default:
                    _sprite = Sprite.movingSprite(spriteList[rightSprite], spriteList[rightSprite + 1], spriteList[rightSprite + 2], animate, 20);
                    break;
            }
        }
    }
}