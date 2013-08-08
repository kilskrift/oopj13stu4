import javax.swing.*;

public class Sheep extends Animal implements Feeder {

    static final int sheepMoveInterval = 10;
    static final int sheepViewDistance = 3;
    /**
     * Creates a new instance of this class, with the given pasture as
     * its pasture.
     * @param pasture the pasture this entity should belong to.
     */
    public Sheep(Pasture pasture) {
        super( pasture, sheepMoveInterval, sheepViewDistance );

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
        if( otherEntity instanceof Wolf )      {
            return true;
        }
        // default not compatible
        return false;
    }

    public void doFeed( Entity otherEntity ) {
        if( otherEntity instanceof Plant ) { // TODO: Depencency injection, pass in list of acceptible Entities
            // kill other entity
            System.out.println( this + " eats " + otherEntity + " at " + pasture.getPosition( this ) );
            otherEntity.kill();
        }
    }
}