import java.awt.*;

/**
 * A new Animal() takes two arguments, first is the Pasture it belongs to, second is the time between moves
 */
public abstract class Animal extends Entity implements Mover {

    protected int moveInterval; // set in constructor, passed by individual animals
    protected int moveDelay;

    protected int viewDistance; // set in constructor, passed by individual animals

    public Animal(Pasture pasture, int moveInterval, int viewDistance ) {
        super(pasture);

        this.moveInterval = moveInterval;
        this.moveDelay = this.moveInterval;

        this.viewDistance = viewDistance;
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

    // Mover
    // default implementation is of a mindlessly rambling animal
    public void doMove() {

        if( this.moveDelay-- <= 0 ) {
            Point neighbour =
                    getRandomMember(pasture.getFreeNeighbours(this));

            if(neighbour != null)
                pasture.moveEntity(this, neighbour);

            this.moveDelay = this.moveInterval;
        }
    }
}
