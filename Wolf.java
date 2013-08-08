import javax.swing.*;

public class Wolf extends Animal implements Feeder {

    static final int wolfMoveInterval = 5;

    /**
     * Creates a new instance of this class, with the given pasture as
     * its pasture.
     * @param pasture the pasture this entity should belong to.
     */
    public Wolf(Pasture pasture) {
        super( pasture, wolfMoveInterval );

        this.image = new ImageIcon("wolf.gif");
    }

    @Override
    /**
     * Tests if this entity can be on the same position in the pasture
     * as the given one.
     */
    public boolean isCompatible(Entity otherEntity) {
        if( otherEntity instanceof Animal )      {
            return true;
        }
        if( otherEntity instanceof Plant )      {
            return true;
        }

        // default not compatible
        return false;
    }

    // Feeder
    public void doFeed( Entity otherEntity ) {
        if( otherEntity instanceof Sheep ) { // TODO: Depencency injection, pass in list of acceptible Entities
            // kill other entity
            System.out.println( this + " eats " + otherEntity + " at " + pasture.getPosition( this ) );
            otherEntity.kill();
       }
    }


}
