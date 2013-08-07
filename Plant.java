import javax.swing.*;

public class Plant extends Entity implements Multiply {

    static final int multiplyInterval = 20;
    private int multiplyDelay;

    public Plant(Pasture pasture) {
        super(pasture);

        this.image = new ImageIcon("plant.gif");

        this.multiplyDelay = multiplyInterval;


    }

    /** This is called from Entity::tick() for all Movable entities.
     *  Implement movement strategy here.
     */
    @Override
    public void doMultiply() {

        if( multiplyDelay-- <= 0 ) {
            // free space adjacent?
            if( pasture.getFreeNeighbours(this).size() > 0 ) {

                pasture.addEntity(new Plant(pasture),
                        pasture.getFreeNeighbours(this).get(
                                (int)(Math.random() * pasture.getFreeNeighbours(this).size())
                        )
                );

                this.multiplyDelay = this.multiplyInterval;

            }
        }

    }

}
