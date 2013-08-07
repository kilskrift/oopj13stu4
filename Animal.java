import java.awt.*;

/**
 * A new Animal() takes two arguments, first is the Pasture it belongs to, second is the time between moves
 */
public class Animal extends Entity implements Mobile {

    protected int moveInterval; // set in constructor by individual animals
    private int moveDelay;

    public Animal(Pasture pasture, int moveInterval ) {
        super(pasture);

        this.moveInterval = moveInterval;
        this.moveDelay = this.moveInterval;
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

    public void makeMove() {

        if( this.moveDelay-- <= 0 ) {
            Point neighbour =
                    getRandomMember(pasture.getFreeNeighbours(this));

            if(neighbour != null)
                pasture.moveEntity(this, neighbour);

            this.moveDelay = this.moveInterval;
        }
    }
}
