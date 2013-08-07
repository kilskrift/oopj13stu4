import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Sheep extends Entity implements Mobile {

    private boolean alive = true;
    private int moveDelay = 10;

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
     */
    public Sheep(Pasture pasture, boolean alive) {
        this( pasture );

        this.alive     = alive;
        this.moveDelay  = 10;
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
