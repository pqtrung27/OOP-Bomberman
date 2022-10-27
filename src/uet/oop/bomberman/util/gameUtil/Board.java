/******************************************************************************
 *
 *  Dependency: Serializable.java
 *
 *  The data type for the game board in each level.
 *
 ******************************************************************************/

package uet.oop.bomberman.util.gameUtil;

/**
 * The {@code Board} class is the data type for the game board in each level.
 *
 * <p>
 * The Board data type store all the data of the thing going on in the game at a moment
 * like all the entities' information, and handling the interactions between two or more entities
 * <p>
 *
 * @author Phu Quoc Trung
 * @author Tran Thuy Duong
 */

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.MediaPlayer;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.MovingEntity;
import uet.oop.bomberman.entities.breakable.Bomb;
import uet.oop.bomberman.entities.breakable.Brick;
import uet.oop.bomberman.entities.breakable.Flame;
import uet.oop.bomberman.entities.breakable.item.BombItem;
import uet.oop.bomberman.entities.breakable.item.FlameItem;
import uet.oop.bomberman.entities.breakable.Portal;
import uet.oop.bomberman.entities.breakable.item.SpeedItem;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.Enemy;
import uet.oop.bomberman.entities.character.enemy.Ballom;
import uet.oop.bomberman.entities.character.enemy.Doll;
import uet.oop.bomberman.entities.character.enemy.Kondoria;
import uet.oop.bomberman.entities.character.enemy.Oneal;
import uet.oop.bomberman.entities.character.enemy.Minvo;
import uet.oop.bomberman.entities.unbreakable.Grass;
import uet.oop.bomberman.entities.unbreakable.Wall;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;
import uet.oop.bomberman.util.entityUtil.EnemyAI;
import uet.oop.bomberman.util.entityUtil.Layer;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Board implements Serializable {
    public double boardOffsetX = 0;
    public double boardOffsetY = 0;
    // Kích thước map của màn chơi
    public int nRow;
    public int nCol;
    private List<Layer> stillObjects = new ArrayList<>();
    public List<Enemy> enemies = new ArrayList<>();
    public List<Bomb> bombs = new ArrayList<>();
    public List<Flame> flames = new ArrayList<>();
    public Bomber bomber;
    public boolean endGame = false;
    public boolean nextLevel = false;
    private int exitsCount = 2;
    private char itemType;

    public Board() {

    }

    public Board(int level) {
        // create map
        try {
            loadLevel(level);
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found!!!");
        }
        endGame = false;
        nextLevel = false;
        bombs.clear();
        flames.clear();
    }

    public void render(GraphicsContext gc) {
        stillObjects.forEach(g -> g.render(gc));
        bombs.forEach(g -> g.render(gc));
        flames.forEach(f -> f.render(gc));

        Collections.sort(enemies, new Comparator<MovingEntity>() {
            @Override
            public int compare(MovingEntity o1, MovingEntity o2) {
                return Double.compare(o1.getX(), o2.getX());
            }
        });
        boolean bomberRender = false;
        for (MovingEntity g : enemies) {
            g.render(gc);
            if (bomber != null && g.getY() >= bomber.getY()) {
                bomberRender = true;
                bomber.render(gc);
            }
        }
        if (bomber != null && !bomberRender) {
            bomber.render(gc);
        }
    }

    public void update() {
        stillObjects.forEach(Layer::update);
        enemies.forEach(enemy -> {
            enemy.update();
            layBomb(enemy);
        });
        if (bomber != null) bomber.update();
        layBomb(bomber);

        //no lambda to avoid ConcurrentModificationException.
        for (int i = bombs.size() - 1; i >= 0; --i) {
            bombs.get(i).setJustLay(false);
            for (int j = enemies.size() - 1; j >= 0; --j) {
                if (collide(bombs.get(i), enemies.get(j))) {
                    bombs.get(i).setJustLay(true);
                    break;
                }
            }
            if (collide(bomber, bombs.get(i))) {
                bombs.get(i).setJustLay(true);
            }
            bombs.get(i).update();
        }

        //no lambda to avoid ConcurrentModificationException.
        for (int i = flames.size() - 1; i >= 0; --i) {
            flames.get(i).update();
        }

        bomberCollide();
        enemyCollide();
        if (bomber != null) {
            if (bomber.isDead() || exitsCount == 0) {
                endGame = true;
                bombs.forEach(bomb -> bomb.setBroken(true));
            }
            if (bomber.isInPortal() && enemies.isEmpty()) {
                nextLevel = true;
            }
        }
    }

    public Layer getLayerCollideWith(Entity entity) {

        int xUnitTop = (int) entity.getTopX() / Sprite.SCALED_SIZE;
        int yUnitTop = (int) entity.getTopY() / Sprite.SCALED_SIZE;
        int xUnitBot = (int) entity.getBotX() / Sprite.SCALED_SIZE;
        int yUnitBot = (int) entity.getBotY() / Sprite.SCALED_SIZE;

        ArrayList<Layer> res = new ArrayList<>();
        Layer layer = stillObjects.get(yUnitTop * nCol + xUnitTop);
        if (layer != null && !(layer.getTop().isGrass())
                && collide(entity, layer.getTop())) {
            res.add(layer);
        }

        layer = stillObjects.get(yUnitBot * nCol + xUnitBot);
        if (layer != null && !(layer.getTop().isGrass())
                && collide(entity, layer.getTop())) {
            res.add(layer);
        }

        layer = stillObjects.get(yUnitTop * nCol + xUnitBot);
        if (layer != null && !(layer.getTop().isGrass())
                && collide(entity, layer.getTop())) {
            res.add(layer);
        }

        layer = stillObjects.get(yUnitBot * nCol + xUnitTop);
        if (layer != null && !(layer.getTop().isGrass())
                && collide(entity, layer.getTop())) {
            res.add(layer);
        }


        if (res.size() == 0) return null;
        res.sort(new Comparator<Layer>() {
            @Override
            public int compare(Layer o1, Layer o2) {

                Entity e1 = o1.getTop();
                Entity e2 = o2.getTop();

                if (!e1.canBePassed() && e2.canBePassed()) {
                    return -1;
                } else if (e1.canBePassed() && !e2.canBePassed()) {
                    return 1;
                } else if (!e1.canBePassed() && !e2.canBePassed()) {
                    if (e1.isWall()) {
                        return -1;
                    } else if (e2.isWall()) {
                        return 1;
                    } else return 0;
                }
                return 0;
            }
        });
        return res.get(0);
    }

    public Entity getEntityCollideWith(Entity entity, double addX, double addY) {
        Grass temp = new Grass(0, 0);
        temp.setTopX(entity.getTopX() + addX);
        temp.setTopY(entity.getTopY() + addY);
        temp.setBotX(entity.getBotX() + addX);
        temp.setBotY(entity.getBotY() + addY);


        for (Bomb bomb : bombs) {
            if (!bomb.isJustLay() && collide(bomb, temp)) {
                return bomb;
            }
        }

        Layer layer = getLayerCollideWith(temp);
        if (layer != null && collide(temp, layer.getTop()))
            return layer.getTop();

        return null;
    }

    public boolean collide(Entity entity1, Entity entity2) {
        double xTop1 = entity1.getTopX();
        double yTop1 = entity1.getTopY();
        double xBot1 = entity1.getBotX();
        double yBot1 = entity1.getBotY();
        double xTop2 = entity2.getTopX();
        double yTop2 = entity2.getTopY();
        double xBot2 = entity2.getBotX();
        double yBot2 = entity2.getBotY();
        if (yTop2 >= yTop1 && yTop2 <= yBot1
                || yBot2 >= yTop1 && yBot2 <= yBot1
                || yTop1 >= yTop2 && yTop1 <= yBot2
                || yBot1 >= yTop2 && yBot1 <= yBot2) {
            if (xTop2 >= xTop1 && xTop2 <= xBot1
                    || xBot2 >= xTop1 && xBot2 <= xBot1
                    || xTop1 >= xTop2 && xTop1 <= xBot2
                    || xBot1 >= xTop2 && xBot1 <= xBot2) {
                return true;
            }
        }
        return false;
    }

    public void bomberCollide() {
        if (bomber.isKilled()) return;
        bombs.forEach(bomb -> {
            if (bomb.isExploding() && collide(bomber, bomb)) bomber.kill();
        });
        flames.forEach(flame -> {
            if (collide(bomber, flame)) bomber.kill();
        });
        enemies.forEach(movEn -> {
            if (!movEn.isKilled() && collide(bomber, movEn)) bomber.kill();
        });
        if (bomber.isKilled()) {
            Sound.bomberisKilledSound.play();
            (new Timer()).schedule(new TimerTask() {
                @Override
                public void run() {
                    Sound.bomberisKilledSound.stop();
                }
            }, (int) Sound.bomberisKilledSound.getStopTime().toMillis());
        }
    }

    public void enemyCollide() {
        for (int i = 0; i < enemies.size(); ++i) {
            MovingEntity entity = enemies.get(i);
            for (int j = 0; j < flames.size(); ++j) {
                Flame flame = flames.get(j);
                if (collide(entity, flame)) {
                    entity.kill();
                }
            }
            if ((entity).isDead()) {
                enemies.remove(i);
                if (enemies.isEmpty()) {
                    Sound.allEnemiesIsKilledSound.play();
                    (new Timer()).schedule(new TimerTask() {
                        @Override
                        public void run() {
                            Sound.allEnemiesIsKilledSound.stop();
                        }
                    }, (int) Sound.allEnemiesIsKilledSound.getStopTime().toMillis());
                }
            }
        }

    }

    public int EnemyAIDirection(Enemy enemy) {
        return EnemyAI.find(enemy, bomber, enemy.get_direction());
    }


    protected void layBomb(MovingEntity entity) {
        if (!entity.isBomber() || !Controller.layBomb) return;
        Controller.layBomb = false;
        if (bombs.size() >= bomber.getMaxBombCount()) return;
        MediaPlayer layBombSound = Sound.cloneOf(Sound.LayBombSound);
        layBombSound.play();
        (new Timer()).schedule(new TimerTask() {
                @Override
                public void run() {layBombSound.stop();
                }
            }, (int) layBombSound.getStopTime().toMillis());

        double bombX = entity.getTopX() + entity.getSpeed();
        double bombY = entity.getTopY() + entity.getSpeed();

        Bomb bom = new Bomb((int) bombX / (Sprite.SCALED_SIZE), (int) bombY / Sprite.SCALED_SIZE);
        bom.setJustLay(true);
        bombs.add(bom);
    }

    /**
     * Tải tải màn chơi từ tập cấu hình.
     *
     * @param level tên level cần tải
     * @throws FileNotFoundException khi không tìm thấy tệp cấu hình cần tải
     */
    public void loadLevel(int level) throws FileNotFoundException {
        String path = "res/levels/Level" + level + ".txt";
        try {
            if (level >= 1) {
                new CreateLevel(path);
            }
            Scanner scanner = new Scanner(Files.newInputStream(Paths.get(path)));
            scanner.nextInt();
            nRow = scanner.nextInt();
            nCol = scanner.nextInt();
            itemType = scanner.next().charAt(0);
            scanner.nextLine();
            scanner.nextLine();
            enemies.clear();
            bombs.forEach(bomb -> bomb.isBroken = true);
            bombs.clear();
            flames.clear();
            for (int i = 0; i < nRow; ++i) {
                String data = scanner.nextLine();
                for (int j = 0; j < nCol; ++j) {
                    Layer layer = new Layer(j, i);
                    if (data.charAt(j) != '#') {
                        layer.add(new Grass(j, i));
                    } else {
                        layer.add(new Wall(j, i));
                    }

                    switch (data.charAt(j)) {
                        case '*':
                            layer.add(new Brick(j, i));
                            break;
                        case 'x':
                            layer.add(new Portal(j, i));
                            break;
                        case 's':
                            layer.add(new SpeedItem(j, i));
                            break;
                        case 'b':
                            layer.add(new BombItem(j, i));
                            break;
                        case 'f':
                            layer.add(new FlameItem(j, i));
                            break;
                        case 'p':
                            bomber = new Bomber(j, i);
                            break;
                        case '1':
                            enemies.add(new Ballom(j, i));
                            break;
                        case '2':
                            enemies.add(new Oneal(j, i));
                            break;
                        case '3':
                            enemies.add(new Doll(j, i));
                            break;
                        case '4':
                            enemies.add(new Minvo(j, i));
                            break;
                        case '5':
                            enemies.add(new Kondoria(j, i));
                            break;
                        default:
                            break;
                    }
                    if (layer.stack.peek().isItem() || layer.stack.peek().isPortal()) {
                        layer.add(new Brick(j, i));
                    }
                    stillObjects.add(layer);
                }
            }
        } catch (Exception e) {
            new CreateLevel(path);
            loadLevel(level);
        }
    }

    public void decreaseExits() {
        --exitsCount;
    }

    public void spawnEnemies(int xUnit, int yUnit) {
        switch (itemType) {
            case 's':
                for (int i = 0; i < 8; ++i) {
                    enemies.add(new Doll(xUnit, yUnit));
                }
                break;
            case 'b':
                for (int i = 0; i < 8; ++i) {
                    enemies.add(new Ballom(xUnit, yUnit));
                }
                break;
            case 'f':
                for (int i = 0; i < 8; ++i) {
                    enemies.add(new Oneal(xUnit, yUnit));
                }
                break;
        }
    }
}


/******************************************************************************
 *  Copyright 2022, Phu Quoc Trung and Tran Thuy Duong.
 *
 *  This file is part of OOP-Bomberman, which accompanies the course
 *
 *      INT2204 of UET-VNU
 *
 *  OOP-Bomberman is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  OOP-Bomberman is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  See http://www.gnu.org/licenses.
 ******************************************************************************/