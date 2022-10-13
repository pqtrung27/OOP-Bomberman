package uet.oop.bomberman.display;

import uet.oop.bomberman.entities.Option;

public abstract class Menu extends DisplayScene {
    protected int minPos = 0;
    protected int currentItem = 0;
    protected Option[] menuItems;

    @Override
    public void reset() {
        menuItems[currentItem].setActive(false);
        currentItem = minPos;
        menuItems[currentItem].setActive(true);
    }

    @Override
    public void update() {
        scene.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case UP:
                    if (currentItem > minPos) {
                        menuItems[currentItem].setActive(false);
                        menuItems[--currentItem].setActive(true);
                    }
                    break;
                case DOWN:
                    if (currentItem < menuItems.length - 1) {
                        menuItems[currentItem].setActive(false);
                        menuItems[++currentItem].setActive(true);
                    }
                    break;
                case ENTER:
                    menuItems[currentItem].activate();
                    break;
            }
        });
    }
}
