import javax.swing.*;

/**
 * This is the superclass of all entities in the pasture simulation
 * system. This provides default implementations of methods used by
 * alla entities.
 */
public abstract class Entity {

    // set to false if the entity has died during a tick
    private boolean alive;

    /** The icon of this entity. */
    protected ImageIcon image;

    /** The position of this entity. */
    protected final Pasture pasture;

    public Entity(Pasture pasture) {
        this.pasture = pasture;

        this.image = new ImageIcon("unknown.gif");

        this.alive = true;
    }

    /** tick() uses introspection to call methods in entities implementing various interfaces:
     *  Mobile executes doMove().
     */
    public void tick()  {

        // implements Mover?
        if( this instanceof Mover) {
            ((Mover)this).doMove();
        }

        // implements Breeder?
        if( this instanceof Breeder) {
            ((Breeder)this).doBreed();
        }

        // implements Feeder?
        if( this instanceof Feeder ) {
            // do for all entities in this location
            for( Entity cohabitant : pasture.getEntitiesAt(pasture.getPosition(this)) ) {
                ((Feeder)this).doFeed(cohabitant);
            }
        }

    }

    // returns true iff the this.alive is true
    public boolean isAlive() {
        return this.alive;
    }

    // set this.alive to false
    public void kill() {
        this.alive = false;
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
