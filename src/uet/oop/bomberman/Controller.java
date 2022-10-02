package uet.oop.bomberman;

import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.entities.Bomber;

public class Controller {

    private int direction;
    Controller() {
        direction = Bomber.directionRight;
    }

    public void listen(KeyEvent event) {
        switch (event.getCode()) {
            case UP:
                direction = Bomber.directionUp;
                break;
            case DOWN:
                direction = Bomber.directionDown;
                break;
            case LEFT:
                direction = Bomber.directionLeft;
                break;
            case RIGHT:
                direction = Bomber.directionRight;
                break;
            default:
                break;
        }
    }

    public int getDirection() {
        return this.direction;
    }
}
