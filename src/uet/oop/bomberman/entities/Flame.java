package uet.oop.bomberman.entities;

import uet.oop.bomberman.graphics.Sprite;

public class Flame extends Entity {
    public static final int horizontalLeft = 0;
    public static final int horizontalRight = 1;
    public static final int verticalUp = 2;
    public static final int verticalDown = 3;
    public Flame(int xUnit, int yUnit, int direction) {
        super(xUnit, yUnit, Sprite.bomb.getFxImage());
        Sprite sprite = Sprite.explosion_horizontal;
        switch (direction) {
            case horizontalLeft:
                sprite = Sprite.explosion_horizontal_left_last;
                break;
            case horizontalRight:
                sprite = Sprite.explosion_horizontal_right_last;
                break;
            case verticalUp:
                sprite = Sprite.explosion_vertical_top_last;
                break;
            case verticalDown:
                sprite = Sprite.explosion_vertical_down_last;
                break;
        }
        this.img = sprite.getFxImage();
    }

    @Override
    public void update() {

    }
}
