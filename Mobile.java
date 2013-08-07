/** Implemented by all moving entities */

public interface Mobile {

    /** This is called from Entity::tick() for all Movable entities.
     *  Implement movement strategy here.
     */
    public void makeMove();

}
