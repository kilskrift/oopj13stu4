import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: kgm
 * Date: 2013-08-07
 * Time: 11:35
 * To change this template use File | Settings | File Templates.
 */

public class Wolf extends Animal {

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


}
