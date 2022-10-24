package uet.oop.bomberman.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class CreateLevel {
    private int row;
    private int col;
    private char[][] map;
    public CreateLevel(String path) {
        try {
            Scanner scanner = new Scanner(Files.newInputStream(Paths.get(path)));
            int level = scanner.nextInt();
            row = scanner.nextInt();
            col = scanner.nextInt();
            scanner.close();


            row = 13;
            createMap();

            FileWriter file = new FileWriter("res/levels/Level" + level + ".txt");
            file.write(level + " " + row + " " + col + "\n");
            for (int i = 0; i < row; ++i) {
                for (int j = 0; j < col; ++j) {
                    file.write(map[i][j]);
                }
                file.write("\n");
            }
            file.flush();
            file.close();
        } catch (IOException e) {
            System.out.println("Can't create level!");
        } catch (NullPointerException e) {
            System.out.println("Can't open level file!");
        }
    }

    private void createMap() {
        map = new char[row][col];
        // border
        for (int j = 0; j < col; ++j) {
            map[0][j] = '#';
            map[row - 1][j] = '#';
        }
        // border
        for (int i = 0; i < row; ++i) {
            map[i][0] = '#';
            map[i][col - 1] = '#';
        }
        // wall
        for (int i = 2; i < row; i += 2) {
            for (int j = 2; j < col; j += 2) {
                map[i][j] = '#';
            }
        }
        // brick, grass
        int brickCount = 0;
        for (int i = 1; i < row - 1; ++i) {
            for (int j = 1; j < col - 1; ++j) {
                if (map[i][j] == '#') continue;
                if (StdRandom.uniformInt(4) == 0) {
                    map[i][j] = '*';
                    ++brickCount;
                } else {
                    map[i][j] = ' ';
                }
            }
        }
        // portal
        for (int i = 1; i < row - 1; ++i) {
            int r = -1;
            for (int j = 1; j < col - 1; ++j) {
                if (map[i][j] == '*') {
                    r = StdRandom.uniformInt(brickCount);
                    if (r == 0) {
                        map[i][j] = 'x';
                    } else --brickCount;
                }
            }
            if (r == 0) break;
        }
        // bomber
        map[1][1] = 'p';
        map[1][2] = ' ';
        map[2][1] = ' ';
    }
}
