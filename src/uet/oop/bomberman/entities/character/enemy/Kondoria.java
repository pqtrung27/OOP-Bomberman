package uet.oop.bomberman.entities.character.enemy;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.CanLayBomb;
import uet.oop.bomberman.entities.character.Enemy;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.util.StdRandom;

import java.util.Timer;
import java.util.TimerTask;

public class Kondoria extends Enemy implements CanLayBomb {

    private boolean justLayBomb = false;
    private int bombRange = 1;
    private int bombCount = 0;

    public Kondoria(int x, int y) {
        super(x, y);
        point = 1000;
        super.spriteList = new Sprite[]{
                Sprite.kondoria_left1,
                Sprite.kondoria_left2,
                Sprite.kondoria_left3,
                Sprite.kondoria_right1,
                Sprite.kondoria_right2,
                Sprite.kondoria_right3,
                Sprite.kondoria_dead,
                Sprite.kondoria_score
        };
        super.speed = 0.3 + (double) ((int) (StdRandom.uniformDouble() * 10)) / 10;
        if (speed == 0.5) speed = 0.6;
        super.img = spriteList[0].getFxImage();
    }

    @Override
    protected boolean canPass(Entity entity) {
        if (!entity.canBePassed()) {
            return entity.isBrick();
        }
        return true;
    }

    public boolean didJustLayBomb() {
        return justLayBomb;
    }

    public void setJustLayBomb() {
        justLayBomb = true;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                justLayBomb = false;
            }
        }, 50000L);
    }


    public int getBombCount() {
        return bombCount;
    }

    public void setBombCount(int bombCount) {
        this.bombCount = bombCount;
    }

    public int getBombRange() {
        return bombRange;
    }

    public void setBombRange(int bombRange) {
        this.bombRange = bombRange;
    }

    @Override
    protected void calculateMove() {
        aiMovement();
    }
}
