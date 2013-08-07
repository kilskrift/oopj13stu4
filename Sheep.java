import javax.swing.*;

public class Sheep extends Animal {

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
        if( otherEntity instanceof Plant ) {
            System.out.println( "Sheep stepped on plant");
            return true;
        }

        // default not compatible
        return false;
    }
}