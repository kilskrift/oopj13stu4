import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: kgm
 * Date: 2013-08-06
 * Time: 16:24
 * To change this template use File | Settings | File Templates.
 */
public class Fences implements Entity {

    /** The icon of this entity. */
    private final ImageIcon image;
    /** The position of this entity. */
    private final Pasture pasture;

    /**
     * Creates a new instance of this class, with the given pasture as
     * its pasture.
     * @param pasture the pasture this entity should belong to.
     */
    public Fences(Pasture pasture) {

        image = new ImageIcon("fence.gif");
        this.pasture = pasture;
    }

    //Entity methods
    /**
     * Performs the relevant actions of this entity, depending on what
     * kind of entity it is.
     */
    public void tick() {
    }

    /**
     * Returns the icon of this entity, to be displayed by the pasture
     * gui.
     * @see PastureGUI
     */
    public ImageIcon getImage() { return image; }

    /**
     * Tests if this entity can be on the same position in the pasture
     * as the given one.
     */
    public boolean isCompatible(Entity otherEntity) { return false; }

}

