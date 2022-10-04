package uet.oop.bomberman.util;

import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.character.enemy.Ballon;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemy.Oneal;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * Lớp tải màn chơi từ tệp cấu hình.
 * <p>
 * Sample:
 * 1 13 31
 * ###############################
 * #p     s* *  1 * 2 *  * * *   #
 * # # # #*# # #*#*# # # #*#*#*# #
 * #  x*     ***  *  1   * 2 * * #
 * # # # # # #*# # #*#*# # # # #*#
 * #f         x **  *  *   1     #
 * # # # # # # # # # #*# #*# # # #
 * #*  *      *  *      *        #
 * # # # # #*# # # #*#*# # # # # #
 * #*    **  *       *           #
 * # #*# # # # # # #*# # # # # # #
 * #           *   *  *          #
 * ###############################
 * <p>
 * Mô tả cấu trúc tệp cấu hình màn chơi:
 * 1/ Dòng đầu tiên bao gồm 3 số nguyên L, R, C:
 * L - số thứ tự màn chơi
 * R - số hàng của bản đồ
 * C - số cột của bản đồ
 * <p>
 * 2/ R dòng tiếp theo, mỗi dòng có C kí tự. Mỗi kí tự đại diện cho một đối tượng trên bản đồ:
 * Tiles:
 * # - Wall
 * * - Brick
 * x - Portal
 * <p>
 * Character:
 * p - Bomber
 * 1 - Balloon
 * 2 - Oneal
 * <p>
 * Items:
 * b - Bomb Item
 * f - Flame Item
 * s - Speed Item
 * <p>
 * Kí tự khác các kí tự trên - Grass
 *
 * @author TTD
 */
public class LoadLevel {
    // Kích thước map của màn chơi
    public static int nRow;
    public static int nCol;

    /**
     * Tải tải màn chơi từ tập cấu hình.
     * @param level tên level cần tải
     * @param stillObjects danh sách đối tượng cố định
     * @param movingObjects danh sách đối tượng di chuyển
     * @throws FileNotFoundException khi không tìm thấy tệp cấu hình cần tải
     */
    public LoadLevel(int level, List<Entity> stillObjects, List<Entity> movingObjects) throws FileNotFoundException {
        String path = "res/levels/Level" + level + ".txt";
        // System.out.println(path);

        Scanner scanner = new Scanner(new FileInputStream(path));
        scanner.nextInt();
        nRow = scanner.nextInt();
        nCol = scanner.nextInt();
        scanner.nextLine();

        stillObjects.clear();
        movingObjects.clear();
        for (int i = 0; i < nRow; ++i) {
            String data = scanner.nextLine();
            for (int j = 0; j < nCol; ++j) {
                switch (data.charAt(j)) {
                    case '#':
                        stillObjects.add(new Wall(j, i));
                        break;
                    case '*':
                        stillObjects.add(new Brick(j, i));
                        break;
                    case 'x':
                        stillObjects.add(new Portal(j, i));
                        break;
                    case 's':
                        stillObjects.add(new SpeedItem(j, i));
                        break;
                    case 'b':
                        stillObjects.add(new BombItem(j, i));
                        break;
                    case 'f':
                        stillObjects.add(new FlameItem(j, i));
                        break;
                    case 'p':
                        movingObjects.add(new Bomber(j, i));
                        stillObjects.add(new Grass(j, i));
                        break;
                    case '1':
                        movingObjects.add(new Ballon(j, i));
                        stillObjects.add(new Grass(j, i));
                        break;
                    case '2':
                        movingObjects.add(new Oneal(j, i));
                        stillObjects.add(new Grass(j, i));
                        break;
                    default:
                        stillObjects.add(new Grass(j, i));
                        break;
                }
            }
        }
    }
}
