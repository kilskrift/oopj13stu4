import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * This is the superclass of all entities in the pasture simulation
 * system. This provides default implementations of methods used by
 * alla entities.
 */
public abstract class Entity {
    /** The icon of this entity. */
    protected ImageIcon image;

    /** The position of this entity. */
    protected final Pasture pasture;

    public Entity(Pasture pasture) {
        this.pasture = pasture;

        this.image = new ImageIcon("unknown.gif");

    }


    public void tick() {
    }

    /**
     * Returns the icon of this entity, to be displayed by the pasture
     * gui.
     * @see PastureGUI
     */

    public ImageIcon getImage() { return this.image; }

    /**
     * Tests if this entity can be on the same position in the pasture
     * as the given one.
     */
    public boolean isCompatible(Entity otherEntity) { return false; }

}
