package uet.oop.bomberman.entities.character;

public interface CanLayBomb {
    int getBombCount();
    void setBombCount(int bombCount);
    int getBombRange();
    void setBombRange(int bombRange);
    boolean isBomber();
}
