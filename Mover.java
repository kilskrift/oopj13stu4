/** Implemented by all moving entities */

public interface Mover {

    /** This is called from Entity::tick() for all Movable entities.
     *  Implement movement strategy here.
     */
    public void doMove();

}
