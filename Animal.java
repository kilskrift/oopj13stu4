import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: kgm
 * Date: 2013-08-07
 * Time: 11:44
 * To change this template use File | Settings | File Templates.
 */
public class Animal extends Entity implements Mobile {
    protected boolean alive = true;
    protected int moveDelay = 10;

    public Animal(Pasture pasture) {
        super(pasture);
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
}
