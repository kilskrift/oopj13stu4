import javax.swing.*;

public class Plant extends Entity {

    private int breedDelay = 20;


    public Plant(Pasture pasture) {
        super(pasture);

        this.image = new ImageIcon("plant.gif");

    }

    /** This is called from Entity::tick() for all Movable entities.
     *  Implement movement strategy here.
     */
    public void doBreed() {

        if( breedDelay-- <= 0 ) {
            // free space adjacent?
            if( pasture.getFreeNeighbours(this).size() > 0 ) {

                pasture.addEntity(new Plant(pasture),
                        pasture.getFreeNeighbours(this).get(
                                (int)(Math.random() * pasture.getFreeNeighbours(this).size())
                        )
                );

                breedDelay = 20;

            }
        }

    }

}
