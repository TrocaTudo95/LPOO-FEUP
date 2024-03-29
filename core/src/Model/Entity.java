package Model;


import com.mygdx.game.MyGdxGame;

import java.util.ArrayList;

public abstract class Entity {
    private final static Pair<Integer,Integer> mario_init_pos = new Pair<Integer, Integer>(4 ,8);
    private final static Pair<Integer,Integer> DK_pos =         new Pair<Integer, Integer>(3 ,222);
    private final static Pair<Integer,Integer> fire_pos =       new Pair<Integer,Integer> (4,8);

    public enum type {  MARIO_LEFT,MARIO_RIGHT,MARIO_CLIMB_LEFT, MARIO_CLIMB_RIGHT, MARIO_CLIMB_OVER, MARIO_RUN_LEFT,MARIO_RUN_RIGHT, MARIO_DYING_UP,MARIO_DYING_DOWN, MARIO_DYING_LEFT, MARIO_DYING_RIGHT, MARIO_DIED,
                        DK_THROW_LEFT, DK_THROW_RIGHT, DK_FRONT, DK_LEFT_BARREL, DK_RIGHT_BARREL, DK_LEFT_HAND, DK_RIGHT_HAND,
                        BARREL_FALL_BACK, BARREL_FALL_FRONT, BARREL_ROLLING1, BARREL_ROLLING2, BARREL_ROLLING3, BARREL_ROLLING4,
                        FIRE_BARREL_FALL_BACK, FIRE_BARREL_FALL_FRONT, FIRE_BARREL_ROLLING,
                        FIRE_LEFT, FIRE_LEFT_IGNITE, FIRE_RIGHT, FIRE_RIGHT_IGNITE,
                        BARREL_FIRE_MIN1, BARREL_FIRE_MIN2, BARREL_FIRE_MAX1, BARREL_FIRE_MAX2, PRINCESS_1, PRINCESS_2}

    private final float DEFAULT_MAX_Y_VELOCITY = 4f;
    protected float DEFAULT_MAX_X_VELOCITY = 3f;

    protected Pair<Integer,Integer> rep_size = new Pair<Integer, Integer>(0,0);
    protected Pair<Integer,Integer> position;
    protected Pair<Float,Float> velocity = new Pair<Float,Float>(DEFAULT_MAX_X_VELOCITY,-2f);

    private float gravity = 1f;
    private float max_y_velocity = 3;
    private float max_x_velocity = 3.0f;

    protected type current_type;
    protected float scale;

    /**
     * Constructor for an Entity
     * @param x X pixel coordinate to create Entity
     * @param y Y pixel coordinate to create Entity
     */
    public Entity(int x , int y){
        this.position = new Pair<Integer,Integer>(x,y);
    }

    /**
     * Gets current X pixel coordinate of Entity
     * @return X pixel coordinate
     */
    public int getX(){
        return this.position.getFirst();
    }

    /**
     * Gets current Y pixel coordinate of Entity
     * @return Y pixel coordinate
     */
    public int getY(){
        return this.position.getSecond();
    }

    /**
     * Gets the current pixel position of Entity
     * @return Pixel position of Entity
     */
    public Pair<Integer,Integer> getPos(){
        return (Pair<Integer,Integer>)this.position.clone();
    }

    /**
     * Gets the size of the sprite representing the Entity
     * @return Size of sprite representing Entity
     */
    public Pair<Integer,Integer> getRepSize(){
        return this.rep_size;
    }

    /**
     * Gets velocity in the X axis
     * @return Current X velocity in pixels
     */
    public int getXSpeed(){
        return (int)Math.round((double)this.velocity.getFirst().floatValue());
    }

    /**
     * Gets velocity in the Y axis
     * @return Current Y velocity in pixels
     */
    public int getYSpeed(){
        return (int)Math.round((double)this.velocity.getSecond().floatValue());
    }

    /**
     * Gets the current type of Entity
     * @return Current Entity type
     */
    public type getType(){
        return this.current_type;
    }

    /**
     * Sets the Entity position
     * @param pos Position to set Entity
     */
    public void setPos(Pair<Integer,Integer> pos){
        this.position = pos;
    }

    /**
     * Sets the entity sprite representation size
     * @param width Width of the sprite
     * @param height Height of the sprite
     * @param scale Scale of the sprite
     */
    public void setRepSize(int width, int height, float scale){
        this.setScale(scale);
        this.rep_size.setFirst(width);
        this.rep_size.setSecond(height);
    }

    /**
     * Sets the Y velocity
     * @param vel Y velocity in pixels
     */
    public void setYVelocity( float vel ){
            this.velocity.setSecond(vel);
    }

    /**
     * Sets the X velocity
     * @param vel X velocity in pixels
     */
    public void setXVelocity( int vel ){
        this.velocity.setFirst((float)vel);
    }

    /**
     * Updates Y velocity based on gravity
     */
    public void updateYVelocity(){
        if (this.velocity.getSecond() - this.gravity > -this.max_y_velocity)
            this.velocity.setSecond(this.velocity.getSecond() - this.gravity );
        else
            this.velocity.setSecond(-this.max_y_velocity);

    }

    /**
     * Sets the scale of this Entity
     * @param scale Scale to be set
     */
    public void setScale (float scale){
        this.max_x_velocity = this.DEFAULT_MAX_X_VELOCITY*scale/MyGdxGame.DEFAULT_SCALE;
        this.velocity.setFirst(this.max_x_velocity);
        this.max_y_velocity = this.DEFAULT_MAX_Y_VELOCITY*scale/MyGdxGame.DEFAULT_SCALE;
        this.scale = scale;
    }

    /**
     * Sets the type of this Entity
     * @param t Type of Entity
     */
    public abstract void setType(type t);


    /**
     *  Tries to move the Entity in the given direction
     * @param map Current map of the game
     * @param move Contains the movement in X coordinate and Y coordinate
     * @return Either this object if state has not changed, or a different object if state has changed
     */
    public abstract Entity moveEntity(Map map, Pair<Integer,Integer> move);

    /**
     *  Whether this entity should be removed or not
     * @param map Current game map
     * @return True is it should be removed, false otherwise
     */
    public abstract boolean toRemove(Map map);

    /**
     * Checks if this entity collides with another entity on given position
     * @param pos Position of other entity
     * @param rep_size Representation size of other entity
     * @return Whether it collides or not
     */
    public abstract boolean collidesWith(Pair<Integer, Integer> pos, Pair<Integer, Integer> rep_size);

    /**
     * Upgrades this Entity, if available
     */
    public abstract void upgrade();

    /**
     * Creates the initial characters of the game
     * @param map Current game map
     * @return Array with initial characters of game
     */
    public static ArrayList<Entity> createInitialCharacters(Map map){
        ArrayList<Entity> chrs = new ArrayList<Entity>();
        Pair<Integer,Integer> mario_pos = map.mapPosToPixels(mario_init_pos);
        Pair<Integer,Integer> dk_pos = map.mapPosToPixels(DK_pos);
        Entity dk = DonkeyKong.getInstance();
        dk.setPos( dk_pos );
        chrs.add( Mario.createMario(mario_pos.getFirst(), mario_pos.getSecond()) );
        chrs.add( dk );
        return chrs;
    }

    /**
     * Creates a new barrel with the given state
     * @param map Current game map
     * @param state Current state of object, First if it is a fire, second if it is free falling
     * @return The newly created barrel
     */
    public static Entity newBarrel(Map map, Pair<Boolean,Boolean> state){
        return Barrel.createBarrel(map, state );
    }

    /**
     * Creates a new Fire
     * @param map Current game map
     * @return The newly created Fire
     */
    public static Entity newFire(Map map){
        return new Fire(map.mapPosToPixels(fire_pos), new SimpleMovement() );
    }
}
