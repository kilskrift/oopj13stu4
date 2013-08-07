import javax.swing.*;
import java.util.List;

public class Sheep extends Animal implements Herbivore {

    static final int sheepMoveInterval = 10;

    /**
     * Creates a new instance of this class, with the given pasture as
     * its pasture.
     * @param pasture the pasture this entity should belong to.
     */
    public Sheep(Pasture pasture) {
        super( pasture, sheepMoveInterval );

        this.image = new ImageIcon("sheep.gif");
    }

    @Override
    /**
     * Tests if this entity can be on the same position in the pasture
     * as the given one.
     */
    public boolean isCompatible(Entity otherEntity) {
        if( otherEntity instanceof Plant )      {
            return true;
        }

        // default not compatible
        return false;
    }

    public void doFeed( Entity otherEntity ) {
        if( otherEntity instanceof Plant ) { // TODO: Depencency injection, pass in list of acceptible Entities
            // kill other entity
            System.out.println( "Sheep eats plant" + " at " + pasture.getPosition( this ) );
            System.out.println( pasture.getEntitiesAt( pasture.getPosition( this ) ) + " at " + pasture.getPosition( this ) );
            pasture.removeEntity( otherEntity );
            System.out.println( "Sheep ate plant");
            System.out.println( pasture.getEntitiesAt( pasture.getPosition( this ) ) + " at " + pasture.getPosition( this ) );

            // TODO: track time/delay until need to feed again?
        }
    }
}