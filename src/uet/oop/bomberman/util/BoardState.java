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
import uet.oop.bomberman.entities.character.enemy.EnemyAI;
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
    public double boardOffsetX = 0;
    public double boardOffsetY = 0;
    // Kích thước map của màn chơi
    public int nRow;
    public int nCol;
    private List<Layer> stillObjects = new ArrayList<>();
    public List<MovingEntity> movingObjects = new ArrayList<>();
    public List<Bomb> bombs = new ArrayList<>();
    public List<Flame> flames = new ArrayList<>();
    private Bomber bomber;
    public boolean endGame = false;
    public boolean nextLevel = false;

    public BoardState(int level) {
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
        movingObjects.forEach(g -> g.render(gc));
        bomber.render(gc);

        flames.forEach(f -> f.render(gc));
    }

    public void update() {
        stillObjects.forEach(Layer::update);
        movingObjects.forEach(Entity::update);
        bomber.update();
        layBomb();

        //no lambda to avoid ConcurrentModificationException.
        for (int i = bombs.size() - 1; i >= 0; --i) {
            if (!collide(bomber, bombs.get(i))) {
                bombs.get(i).setJustLay(false);
            }
            bombs.get(i).update();
        }
        //no lambda to avoid ConcurrentModificationException.
        for (int i = flames.size() - 1; i >= 0; --i) {
            flames.get(i).update();
        }

        bomberCollide();
        enemyCollide();

        if (bomber.isDead()) {
            endGame = true;
            bombs.forEach(bomb -> bomb.setBroken(true));
        }
        if (bomber.isInPortal() && movingObjects.isEmpty()) {
            nextLevel = true;
        }
    }

    public Layer getLayer(int xTop, int yTop) {
        int xBot = xTop + Sprite.SCALED_SIZE - 1;
        int yBot = yTop + Sprite.SCALED_SIZE - 1;
        int xUnitTop = xTop / Sprite.SCALED_SIZE;
        int yUnitTop = yTop / Sprite.SCALED_SIZE;
        int xUnitBot = xBot / Sprite.SCALED_SIZE;
        int yUnitBot = yBot / Sprite.SCALED_SIZE;
        Layer layer = stillObjects.get(yUnitTop * nCol + xUnitTop);
        if (layer != null && !(layer.getTop().isGrass())) return layer;
        layer = stillObjects.get(yUnitBot * nCol + xUnitBot);
        if (layer != null && !(layer.getTop().isGrass())) return layer;
        layer = stillObjects.get(yUnitTop * nCol + xUnitBot);
        if (layer != null && !(layer.getTop().isGrass())) return layer;
        layer = stillObjects.get(yUnitBot * nCol + xUnitTop);
        if (layer != null && !(layer.getTop().isGrass())) return layer;
        return null;
    }

    public Entity getEntityCollideWith(Entity entity, int addX, int addY) {
        Wall temp = new Wall(0, 0);
        temp.setX(entity.getX() + addX);
        temp.setY(entity.getY() + addY);
        temp.setBotX(entity.getBotX() + addX);
        temp.setBotY(entity.getBotY() + addY);
        for (Bomb bomb : bombs) {
            if (collide(bomb, temp) && !bomb.isJustLay())
                return bomb;
        }
        Layer layer = getLayer(temp.getX(), temp.getY());
        if (layer == null) return null;
        return layer.getTop();
    }

    public boolean collide(Entity entity1, Entity entity2) {
        int xTop1 = entity1.getX();
        int yTop1 = entity1.getY();
        int xBot1 = entity1.getBotX();
        int yBot1 = entity1.getBotY();
        int xTop2 = entity2.getX();
        int yTop2 = entity2.getY();
        int xBot2 = entity2.getBotX();
        int yBot2 = entity2.getBotY();
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
        bombs.forEach(bomb -> {
            if (bomb.isExploding() && collide(bomber, bomb)) bomber.kill();
        });
        flames.forEach(flame -> {
            if (collide(bomber, flame)) bomber.kill();
        });
        movingObjects.forEach(movEn -> {
            if (collide(bomber, movEn)) bomber.kill();
        });
    }

    public void enemyCollide() {
        for (int i = 0; i < movingObjects.size(); ++i) {
            MovingEntity entity = movingObjects.get(i);
            for (int j = 0; j < flames.size(); ++j) {
                Flame flame = flames.get(j);
                if (collide(entity, flame)) entity.kill();
            }
            if ((entity).isDead()) movingObjects.remove(i);
        }
    }

    public int EnemyCalDirection(Enemy enemy) {
        return EnemyAI.find(enemy, bomber, enemy.get_direction());
    }


    protected void layBomb() {
        if (!Controller.layBomb || bombs.size() == Bomb.maxBombNum) {
            Controller.layBomb = false;
            return;
        }
        int bombX = bomber.getX() / Sprite.SCALED_SIZE;
        int bombY = bomber.getY() / Sprite.SCALED_SIZE;

        if (Controller.direction[MovingEntity.directionUp] ||
                Controller.direction[MovingEntity.directionDown]) {
            if (bomber.getY() % Sprite.SCALED_SIZE > Sprite.SCALED_SIZE / 2) bombY++;
        }

        bombs.add(new Bomb(bombX, bombY));
        Controller.layBomb = false;
    }

    /**
     * Tải tải màn chơi từ tập cấu hình.
     *
     * @param level tên level cần tải
     * @throws FileNotFoundException khi không tìm thấy tệp cấu hình cần tải
     */
    public void loadLevel(int level) throws FileNotFoundException {
        String path = "res/levels/Level" + level + ".txt";
        // System.out.println(path);
        Scanner scanner = new Scanner(new FileInputStream(path));
        scanner.nextInt();
        nRow = scanner.nextInt();
        nCol = scanner.nextInt();
        scanner.nextLine();
        movingObjects.clear();
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
                        movingObjects.add(new Ballon(j, i));
                        break;
                    case '2':
                        movingObjects.add(new Oneal(j, i));
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
    }

}
