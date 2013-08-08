import java.util.*;
import java.awt.Point;


/**
 * A pasture contains sheep, wolves, fences, plants, and possibly
 * other entities. These entities move around in the pasture and try
 * to find food, other entities of the same kind and run away from
 * possible enimies. 
 */
public class Pasture {

    private final int   width = 4;
    private final int   height = 4;

    private final int   sheep = 1;
    private final int   wolves = 1;
    private final int   plants = 1;

    // contains all entities in the simulation world
    private final Set<Entity> world = 
        new HashSet<Entity>();

    // contains a list of which entities are present at which point
    private final Map<Point, List<Entity>> grid = 
        new HashMap<Point, List<Entity>>();

    // contains which point entities are at
    private final Map<Entity, Point> point 
        = new HashMap<Entity, Point>();

    private final PastureGUI gui;

    /** 
     * Creates a new instance of this class and places the entities in
     * it on random positions.
     */
    public Pasture() {

        Engine engine = new Engine(this);
        gui = new PastureGUI(width, height, engine);

        boolean testFlag =true;

        if( !testFlag ) {

            /* The pasture is surrounded by a fence. Replace Dummy for
             * Fence when you have created that class */
            for (int i = 0; i < width; i++) {
                addEntity(new Fence(this), new Point(i,0));
                addEntity(new Fence(this), new Point(i, height - 1));
            }
            for (int i = 1; i < height-1; i++) {
                addEntity(new Fence(this), new Point(0,i));
                addEntity(new Fence(this), new Point(width - 1,i));
            }

            /*
             * Now insert the right number of different entities in the
             * pasture.
             */

            // sheep
            for (int i = 0; i < sheep; i++) {
                Entity sheep = new Sheep(this);
                addEntity(sheep, getFreePosition(sheep));
            }

            // wolves
            for (int i = 0; i < wolves; i++) {
                Entity wolf = new Wolf(this);
                addEntity(wolf, getFreePosition(wolf));

            }

            //plants
            for (int i = 0; i < plants; i++) {
                Entity plant = new Plant(this);
                addEntity(plant, getFreePosition(plant));
            }
        }
        // test playground
        else {
            Entity sheep = new Sheep(this);
            addEntity(sheep, new Point(1,1));

            Entity wolf = new Wolf(this);
            addEntity(wolf, new Point(0,0));

            System.out.println( getEntitiesWithinDistance( getPosition(wolf), 1 ) );  // should be wolf only

            Entity sheep2 = new Sheep(this);
            addEntity(sheep2, new Point(0,1));

            System.out.println( getEntitiesWithinDistance( getPosition(wolf), 1 ) );  // should be wolf, sheep2
            System.out.println( getEntitiesWithinDistance( getPosition(wolf), 2 ) );  // all three
        }


        gui.update();
    }

    public void refresh() {
        gui.update();
    }

    /**
     * Returns a random free position in the pasture if there exists
     * one.
     * 
     * If the first random position turns out to be occupied, the rest
     * of the board is searched to find a free position. 
     */
    private Point getFreePosition(Entity toPlace) 
        throws MissingResourceException {
        Point position = new Point((int)(Math.random() * width),
                                   (int)(Math.random() * height)); 

        int p = position.x+position.y*width;
        int m = height * width;
        int q = 97; //any large prime will do

        for (int i = 0; i<m; i++) {
            int j = (p+i*q) % m;
            int x = j % width;
            int y = j / width;

            position = new Point(x,y);
            boolean free = true;

            Collection <Entity> c = getEntitiesAt(position);
            if (c != null) {
                for (Entity thisThing : c) {
                    if(!toPlace.isCompatible(thisThing)) { 
                        free = false; break; 
                    }
                }
            }
            if (free) return position;
        }
        throw new MissingResourceException(
                  "There is no free space"+" left in the pasture",
                  "Pasture", "");
    }
    
            
    public Point getPosition (Entity e) {
        return point.get(e);
    }

    /**
     * Add a new entity to the pasture.
     */
    public void addEntity(Entity entity, Point pos) {

        world.add(entity);

        List<Entity> l = grid.get(pos);
        if (l == null) {
            l = new  ArrayList<Entity>();
            grid.put(pos, l);
        }
        l.add(entity);

        point.put(entity,pos);

        gui.addEntity(entity, pos);
    }
    
    public void moveEntity(Entity e, Point newPos) {
        
        Point oldPos = point.get(e);
        List<Entity> l = grid.get(oldPos);
        if (!l.remove(e)) 
            throw new IllegalStateException("Inconsistent state in Pasture");
        /* We expect the entity to be at its old position, before we
           move, right? */
                
        l = grid.get(newPos);
        if (l == null) {
            l = new ArrayList<Entity>();
            grid.put(newPos, l);
        }
        l.add(e);

        point.put(e, newPos);

        gui.moveEntity(e, oldPos, newPos);
    }

    /**
     * Remove the specified entity from this pasture.
     */
    public void removeEntity(Entity entity) { 

        Point p = point.get(entity);
        world.remove(entity); 
        grid.get(p).remove(entity);
        point.remove(entity);
        gui.removeEntity(entity, p);

    }

    /**
     * Various methods for getting information about the pasture
     */

    public List<Entity> getEntities() {
        return new ArrayList<Entity>(world);
    }
        
    public Collection<Entity> getEntitiesAt(Point lookAt) {

        Collection<Entity> l = grid.get(lookAt);
        
        if (l==null) {
            return null;
        }
        else {
            return new ArrayList<Entity>(l);
        }
    }


    public List<Point> getFreeNeighbours(Entity entity) {
        List<Point> free = new ArrayList<Point>();

        int entityX = getEntityPosition(entity).x;
        int entityY = getEntityPosition(entity).y;

        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                Point p = new Point(entityX + x,
                          entityY + y);
                if (freeSpace(p, entity))
                    free.add(p);
            }
        }
        return free;
    }

    private boolean freeSpace(Point p, Entity e) {
                              
        List <Entity> l = grid.get(p);
        if ( l == null  ) return true;
        for (Entity old : l ) 
            if (! old.isCompatible(e)) return false;
        return true;
    }

    public Point getEntityPosition(Entity entity) {
        return point.get(entity);
    }


    // all points w/entities
    private Set <Point> getOccupiedPoints() {
        return grid.keySet();
    }

    // all entities of a type within distance from point
    public List<Entity> getEntitiesWithinDistance( Point origin, int maxDistance ) {

        List<Entity> found = new ArrayList<Entity>();

        for( Entity e : world ) {
            System.out.println( origin.distance( this.getPosition(e) ) );
            if( origin.distance( this.getPosition(e) ) <= maxDistance ) {
                found.add( e );
            }
        }

        return found;
    }



    /** The method for the JVM to run. */
    public static void main(String[] args) {

        new Pasture();
    }


}


