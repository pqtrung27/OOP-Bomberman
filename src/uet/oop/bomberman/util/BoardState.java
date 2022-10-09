package uet.oop.bomberman.util;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.breakable.*;
import uet.oop.bomberman.entities.breakable.item.BombItem;
import uet.oop.bomberman.entities.breakable.item.FlameItem;
import uet.oop.bomberman.entities.breakable.Portal;
import uet.oop.bomberman.entities.breakable.item.SpeedItem;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemy.Ballon;
import uet.oop.bomberman.entities.character.enemy.Enemy;
import uet.oop.bomberman.entities.character.enemy.Oneal;
import uet.oop.bomberman.entities.unbreakable.Grass;
import uet.oop.bomberman.entities.unbreakable.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BoardState implements Serializable {

    public double boardOffset = 0;

    // Kích thước map của màn chơi
    public int nRow;
    public int nCol;
    private List<LayerEntity> stillObjects = new ArrayList<>();
    private List<MovingEntity> movingObjects = new ArrayList<>();
    public List<BreakableEntity> bombs = new ArrayList<>();
    public List<Flame> flames = new ArrayList<>();
    private Bomber bomber;

    public boolean endGame = false;

    public BoardState() {

    }

    public void render(GraphicsContext gc) {
        stillObjects.forEach(g -> g.render(gc));
        bombs.forEach(g -> g.render(gc));
        movingObjects.forEach(g -> g.render(gc));
        flames.forEach(f -> f.render(gc));
    }

    public void update() {
        stillObjects.forEach(Entity::update);
        movingObjects.forEach(Entity::update);
        layBomb();

        //no lambda to avoid ConcurrentModificationException.
        for (int i = 0; i < bombs.size(); ++i) {
            bombs.get(i).update();
        }
        //no lambda to avoid ConcurrentModificationException.
        for (int i = 0; i < flames.size(); ++i) {
            flames.get(i).update();
        }

        bomberCollide();
        enemyCollide();

        if (bomber.deadOver()) endGame = true;
    }

    public LayerEntity get(int xTop, int yTop) {
        int xBot = xTop + Sprite.SCALED_SIZE - 1;
        int yBot = yTop + Sprite.SCALED_SIZE - 1;
        for (LayerEntity g : stillObjects) {
            if (g.isGrass()) {
                continue;
            }
            int gXmax = g.x() + Sprite.SCALED_SIZE - 1;
            int gYmax = g.y() + Sprite.SCALED_SIZE - 1;
            if (g.y() <= yBot && g.y() >= yTop
                    || gYmax >= yTop && gYmax <= yBot) {
                if (g.x() <= xBot && g.x() >= xTop
                        || gXmax >= xTop && gXmax <= xBot) {
                    return g;
                }
            }
        }
        return null;
    }

    private boolean collide(Entity entity1, Entity entity2) {
        int xTop1 = entity1.x();
        int yTop1 = entity1.y();
        int xBot1 = xTop1 + Sprite.SCALED_SIZE - 1;
        int yBot1 = yTop1 + Sprite.SCALED_SIZE - 1;
        int xTop2 = entity2.x();
        int yTop2 = entity2.y();
        int xBot2 = xTop2 + Sprite.SCALED_SIZE - 1;
        int yBot2 = yTop2 + Sprite.SCALED_SIZE - 1;
        if (yTop2 <= yBot1 && yTop2 >= yTop1
                || yBot2 >= yTop1 && yBot2 <= yBot1) {
            if (xTop2 <= xBot1 && xTop2 >= xTop1
                    || xBot2 >= xTop1 && xBot2 <= xBot1) {
                return true;
            }
        }
        return false;
    }

    public void bomberCollide() {
        flames.forEach(flame -> {
            if (collide(bomber, flame)) bomber.kill();
        });
        movingObjects.forEach(movEn -> {
            if (!(movEn instanceof Bomber))
                if (collide(bomber, movEn)) bomber.kill();
        });
    }

    public void enemyCollide() {
        for (int i = 0; i < movingObjects.size(); ++i) {
            MovingEntity entity = movingObjects.get(i);
            if (!(entity instanceof Bomber))
                for (int j = 0; j < flames.size(); ++j) {
                    Flame flame = flames.get(j);
                    if (collide(entity, flame)) entity.kill();
                }
            if ((entity).deadOver()) movingObjects.remove(i);
        }
    }

    protected void layBomb() {
        if (!Controller.layBomb || bombs.size() == Bomb.maxBombNum) {
            Controller.layBomb = false;
            return;
        }
        bombs.add(new Bomb((bomber.x() + bomber.getSpeed()) / Sprite.SCALED_SIZE, (bomber.y() + bomber.getSpeed()) / Sprite.SCALED_SIZE));
        Controller.layBomb = false;
    }

    /**
     * Tải tải màn chơi từ tập cấu hình.
     *
     * @param level tên level cần tải
     * @throws FileNotFoundException khi không tìm thấy tệp cấu hình cần tải
     */
    public void LoadLevel(int level) throws FileNotFoundException {
        String path = "res/levels/Level" + level + ".txt";
        // System.out.println(path);
        Scanner scanner = new Scanner(new FileInputStream(path));
        scanner.nextInt();
        nRow = scanner.nextInt();
        nCol = scanner.nextInt();
        scanner.nextLine();
        movingObjects.clear();
        for (int i = 0; i < nRow; ++i) {
            String data = scanner.nextLine();
            for (int j = 0; j < nCol; ++j) {
                LayerEntity layer = new LayerEntity(j, i);
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
                        movingObjects.add(bomber);
                        break;
                    case '1':
                        movingObjects.add(new Ballon(j, i));
                        break;
                    case '2':
                        movingObjects.add(new Oneal(j, i));
                        break;
                    default:
                        break;
                }
                if (layer.isItem() || layer.isPortal()) {
                    layer.add(new Brick(j, i));
                }
                stillObjects.add(layer);
            }
        }
    }

}
