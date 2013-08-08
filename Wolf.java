import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Wolf extends Animal implements Feeder {

    static final int wolfMoveInterval = 5;
    static final int wolfViewDistance = 3;

    // contains direction we moved in last turn; usage: add to current position to get target position
    int lastY, lastX;

    /**
     * Creates a new instance of this class, with the given pasture as
     * its pasture.
     * @param pasture the pasture this entity should belong to.
     */
    public Wolf(Pasture pasture) {
        super( pasture, wolfMoveInterval, wolfViewDistance );

        //no direction at first
        lastX = 1;
        lastY = 1;

        this.image = new ImageIcon("wolf.gif");
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

    // Feeder
    public void doFeed( Entity otherEntity ) {
        if( otherEntity instanceof Sheep ) { // TODO: Depencency injection, pass in list of acceptible Entities
            // kill other entity
            System.out.println( this + " eats " + otherEntity + " at " + pasture.getPosition( this ) );
            otherEntity.kill();
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



    @Override
    // Mover
    public void doMove() {

        if( this.moveDelay-- <= 0 ) {

            // get all entities within viewDistance of the animal
            List<Entity> seen = pasture.getEntitiesWithinDistance( pasture.getPosition(this), this.viewDistance );
            //System.out.println( seen );

            // score all points surrounding our position, inclusive
            Map<Point, Double> scoredNeighbours = new HashMap<Point, Double>();
            Point here = pasture.getPosition( this );

            for( Point neighbour : pasture.getAllNeighbours( here ) ) {
                Double score = 0.0;

                for( Entity e : seen ) {

                    Double distance = neighbour.distance( pasture.getPosition(e) );

                    if( e instanceof Sheep ) { // only eat sheep
                        System.out.println( e + " spotted " + distance + " away from " + neighbour );
                        score += 100 / (1 + distance);
                    }

                }
                scoredNeighbours.put( neighbour, score );
            }
             //System.out.println( scoredNeighbours );

            // get optimal direction
            // from http://stackoverflow.com/questions/5911174/finding-key-associated-with-max-value-in-a-java-map
            Map.Entry<Point, Double> maxEntry = null;
            for (Map.Entry<Point, Double> entry : scoredNeighbours.entrySet() )
            {
                if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
                {
                    maxEntry = entry;
                }
            }
            Point preferredNeighbour = maxEntry.getKey();

            // if we can't go in the preferred direction, continue in direction from last turn.
            if( pasture.getFreeNeighbours(this).contains(preferredNeighbour) == false ) {
                preferredNeighbour =
                        new Point(  (int)pasture.getPosition(this).getX() + lastX,
                                    (int)pasture.getPosition(this).getY() + lastY );
            }

            // if we still can't go there, resort to random direction
            if( pasture.getFreeNeighbours(this).contains(preferredNeighbour) == false ) {
                preferredNeighbour =
                        getRandomMember(pasture.getFreeNeighbours(this));
            }

            // update direction
            lastX = (int)preferredNeighbour.getX() - (int)pasture.getPosition(this).getX();
            lastY = (int)preferredNeighbour.getY() - (int)pasture.getPosition(this).getY();

            // perform move
            pasture.moveEntity( this, preferredNeighbour );
            this.moveDelay = this.moveInterval;
        }
    }



}
