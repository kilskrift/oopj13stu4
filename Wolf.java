import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: kgm
 * Date: 2013-08-07
 * Time: 11:35
 * To change this template use File | Settings | File Templates.
 */

public class Wolf extends Entity implements Mobile {

    private boolean alive = true;
    private int moveDelay = 10;

    /**
     * Creates a new instance of this class, with the given pasture as
     * its pasture.
     * @param pasture the pasture this entity should belong to.
     */
    public Wolf(Pasture pasture) {
        super( pasture );

        this.image = new ImageIcon("wolf.gif");
    }

    /**
     * Creates a new instance of this class, with the given pasture as
     * its pasture, and position as its position.
     * @param pasture the pasture this entity should belong to.
     */
    public Wolf(Pasture pasture, boolean alive) {
        this( pasture );

        this.alive     = alive;
        this.moveDelay  = 5;
    }

    // Movable methods

    public boolean isAlive() {
        return alive;
    }

    public int getMoveDelay() {
        return moveDelay;
    }

    public void makeMove() {

        if( isAlive() ) {
            moveDelay--;
        }

        if( getMoveDelay() == 0) {
            Point neighbour =
                    getRandomMember(pasture.getFreeNeighbours(this));

            if(neighbour != null)
                pasture.moveEntity(this, neighbour);

            moveDelay = 5;
        }
    }
    /**
     * A general method for grabbing a random element from a
     * list. Does it belong in this class?
     */
    private static <X> X getRandomMember(java.util.List<X> c) {
        if (c.size() == 0)
            return null;

        int n = (int)(Math.random() * c.size());

        return c.get(n);
    }

}
