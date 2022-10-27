/******************************************************************************
 *
 *  Dependency: Entity.java
 *
 *  The abstract data type for the Unbreakable Entity in general.
 *
 ******************************************************************************/

package uet.oop.bomberman.entities;

/**
 * The {@code UnbreakableEntity} class is the data type for all the Unbreakable Entity
 * in this game in general.
 *
 * <p>
 * It has all the method and variable that a normal Unbreakable Entity
 * would have, which is the constructor :).
 * <p>
 *
 * @author Phu Quoc Trung
 * @author Tran Thuy Duong
 */

import javafx.scene.image.Image;

public abstract class UnbreakableEntity extends Entity {
    public UnbreakableEntity(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }
}
