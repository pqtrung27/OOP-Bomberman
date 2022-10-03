package uet.oop.bomberman.util;

import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.entities.character.Bomber;

public class Controller {
    public static boolean[] direction = new boolean[5];

    public Controller() {
        direction[Bomber.directionNone] = true;
    }

    public void listen(KeyEvent event) {
        switch (event.getCode()) {
            case UP:
                direction[Bomber.directionUp] = true;
                break;
            case DOWN:
                direction[Bomber.directionDown] = true;
                break;
            case LEFT:
                direction[Bomber.directionLeft] = true;
                break;
            case RIGHT:
                direction[Bomber.directionRight] = true;
                break;
            default:
                direction[Bomber.directionNone] = false;
                break;
        }
    }

    public void release(KeyEvent event) {
        switch (event.getCode()) {
            case UP:
                direction[Bomber.directionUp] = false;
                break;
            case DOWN:
                direction[Bomber.directionDown] = false;
                break;
            case LEFT:
                direction[Bomber.directionLeft] = false;
                break;
            case RIGHT:
                direction[Bomber.directionRight] = false;
                break;
            default:
                direction[Bomber.directionNone] = true;
                break;
        }
    }
}
