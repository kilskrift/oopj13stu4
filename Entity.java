import javax.swing.*;

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

    /** tick() uses introspection to call methods in entities implementing various interfaces:
     *  Mobile executes makeMove().
     */
    public void tick()  {

        // implements Mobile?
        if( this instanceof Mobile ) {
            ((Mobile)this).makeMove();
        }

        // implements Multiply?
        if( this instanceof Multiply ) {
            ((Multiply)this).doMultiply();
        }

        // implements Herbivore
        if( this instanceof Herbivore ) {
            // do for all entities in this location

            for( Entity cohabitant : pasture.getEntitiesAt( pasture.getPosition( this ) ) ) {
                ((Herbivore)this).doFeed(cohabitant);
            }
        }
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
