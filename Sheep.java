import javax.swing.*;

public class Sheep extends Animal {

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

}
