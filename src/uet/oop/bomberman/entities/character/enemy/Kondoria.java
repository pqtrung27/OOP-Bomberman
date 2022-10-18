package uet.oop.bomberman.entities.character.enemy;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.breakable.Brick;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.util.StdRandom;

import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Kondoria extends Enemy {

    private boolean justLayBomb = false;

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
                //Sprite.kondoria_score
        };
        super.speed = 0.1 + (double) ((int) (StdRandom.uniformDouble() * 10)) / 10;
        if (speed == 0.5) speed = 0.6;
        super.img = spriteList[0].getFxImage();
    }

    @Override
    protected boolean canPass(Entity entity) {
        return entity instanceof Brick;
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

    @Override
    protected void calculateMove() {
        aiMovement();
    }
}
