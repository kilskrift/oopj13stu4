import javax.swing.*;

public class Wolf extends Animal {

    static final int wolfMoveInterval = 5;


    /**
     * Creates a new instance of this class, with the given pasture as
     * its pasture.
     * @param pasture the pasture this entity should belong to.
     */
    public Wolf(Pasture pasture) {
        super( pasture, wolfMoveInterval );

        this.image = new ImageIcon("wolf.gif");
    }

}
