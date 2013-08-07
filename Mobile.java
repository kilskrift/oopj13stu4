/** Implemented by all moving entities */

public interface Mobile {

    /** Returns the current number of ticks this entity should get before moving. */
    int getMoveDelay();

    /** Is true iff entity is currently alive and moving. */
    boolean isAlive();

    /** This is called from Entity::tick() for all Movable entities.
     *  Implement movement strategy here.
     */
    public void makeMove();

}
