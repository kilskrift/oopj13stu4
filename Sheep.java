import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.util.List;

// Note that Dummy is a pretty BAD example of object-oriented
// programming. Instead of having separate classes for stationary and
// mobile dummies, they are distinguished using the flag "alive".  You
// probably do not want to base your solution on this class.


public class Sheep extends Entity {

    /** The number of ticks this entity should get before moving. */
    private int moveDelay;

    private boolean alive;

    /**
     * Creates a new instance of this class, with the given pasture as
     * its pasture.
     * @param pasture the pasture this entity should belong to.
     */
    public Sheep(Pasture pasture) {
        super( pasture );

        this.image = new ImageIcon("sheep.gif");
    }

    /**
     * Creates a new instance of this class, with the given pasture as
     * its pasture, and position as its position.
     * @param pasture the pasture this entity should belong to.
     * @param position the position of this entity.
     */
    public Sheep(Pasture pasture, boolean alive) {
        this( pasture );

        this.alive     = alive;
        this.moveDelay      = 10;
    }

    /**
     * Performs the relevant actions of this entity, depending on what
     * kind of entity it is.
     */
    public void tick() {
        if(alive)
            moveDelay--;
        
        if(moveDelay == 0) {
            Point neighbour = 
                getRandomMember(pasture.getFreeNeighbours(this));
            
            if(neighbour != null) 
                pasture.moveEntity(this, neighbour);

            moveDelay = 10;
        }
    }

    /**
     * A general method for grabbing a random element from a
     * list. Does it belong in this class?
     */
    private static <X> X getRandomMember(List<X> c) {
        if (c.size() == 0)
            return null;
        
        int n = (int)(Math.random() * c.size());
        
        return c.get(n);
    }

}
