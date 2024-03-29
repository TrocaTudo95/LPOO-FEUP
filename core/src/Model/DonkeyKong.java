package Model;

public class DonkeyKong extends Model.Entity {
    private final int ANIMATION_RESET = 40;
    private final int ANIMATION_RATE = 20;
    private final int THROW = 1;
    private final int HAND = 0;
    private int curr_animation = -1;

    protected int tick;
    private static DonkeyKong instance = null;
    private boolean free_barrel = false;

    /**
     * Constructor of DonkeyKong
     * @param x X pixel coordinate to create DonkeyKong
     * @param y Y pixel coordinate to create DonkeyKong
     */
    private DonkeyKong(int x, int y){
        super(x,y);
        this.tick = 0;
        this.current_type=type.DK_THROW_LEFT;
    }

    @Override
    public boolean collidesWith(Model.Pair<Integer, Integer> pos, Model.Pair<Integer, Integer> rep_size) {
        return  (pos.getFirst() <= (this.getX()+this.rep_size.getFirst())) && (pos.getSecond() >=  this.getY()) &&
                ((pos.getSecond()+rep_size.getSecond()) <= (this.getY()+this.rep_size.getSecond()) );
    }

    @Override
    public void upgrade() {}


    @Override
    public void setType(type t) {
        if (    t == type.DK_FRONT || t == type.DK_LEFT_BARREL || t == type.DK_LEFT_HAND || t == type.DK_RIGHT_BARREL ||
                t == type.DK_RIGHT_HAND || t == type.DK_THROW_LEFT || t == type.DK_THROW_RIGHT )
            this.current_type = t;
    }

    /**
     *  Since DK does not move, this is used to know which sprite to represent it
     * @param map Current map of the game
     * @param state State of DK, first if it is a falling barrel or rolling to throw, second if it is the first barrel
     * @return null if DK is about to throw a barrel, this object otherwise
     */
    @Override
    public Model.Entity moveEntity(Model.Map map, Pair<Integer,Integer> state) {
        if (this.curr_animation == -1){
            this.free_barrel = ((Math.random()*10) > 7) || state.getSecond() == THROW;
            this.curr_animation = state.getFirst();
            this.tick = 0;
        }
        this.tickTock();
        if ( this.curr_animation == HAND)
            this.updateSpriteKong();
        else if (this.curr_animation == THROW)
            this.updateSpriteThrow();

        if ( this.tick == ANIMATION_RATE && this.curr_animation == THROW )
            return null;
        else
            return this;
    }


    /**
     *  Updates DK status to animate it when he is not throwing a barrel
     */
    private void updateSpriteKong() {
        if(this.tick >= ANIMATION_RATE)
            this.current_type=type.DK_RIGHT_HAND;
        else if(this.tick < ANIMATION_RATE)
            this.current_type=type.DK_LEFT_HAND;
    }

    /**
     *  Updates DK status to animate it when he is throwing a barrel
     */
    private void updateSpriteThrow() {
        if(this.tick >= ANIMATION_RATE)
            this.current_type = (this.free_barrel) ? type.DK_FRONT : type.DK_RIGHT_BARREL;
        else if(this.tick < ANIMATION_RATE)
            this.current_type=type.DK_THROW_LEFT;
    }

    /**
     *  Used to know when to animate DK
     */
    private void tickTock() {
        this.tick++;
        if ( ANIMATION_RESET == this.tick ){
            this.tick = 0;
            this.curr_animation = -1;
        }
    }

    /**
     * Gets an instance of DonkeyKong
     * @return The single instance of DonkeyKong that will exists throughout the program
     */
    public static DonkeyKong getInstance(){
        if (instance == null) {
            return (instance = new DonkeyKong(0,0));
        }
        else
            return instance;
    }

    @Override
    public boolean toRemove(Model.Map map) {
        return false;
    }
}
