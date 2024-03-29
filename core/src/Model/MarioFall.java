package Model;


public class MarioFall extends Mario {

    /**
     *  Constructor for MarioFall
     * @param x X coordinate to position Mario
     * @param y Y coordinate to position Mario
     */
    public MarioFall( int x , int y ){
        super(x,y);
    }

    @Override
    public Model.Entity moveEntity(Map map, Pair<Integer,Integer> irrelevant) {
        if (current_type == type.MARIO_DYING_UP)
            return new MarioDie(position.getFirst(), position.getSecond());
        else {
            Mario ret_val = updatePosition(map);

            return ret_val;
        }
    }

    @Override
    protected void tickTock() {}

    /**
     *  Updates Mario position based on collisions and current velocity
     * @param map Current map of the game
     * @return A MarioRun object if the fall has ended or this object with updated positions
     */
    private Mario updatePosition(Map map){
        Pair<Integer,Integer> new_pos = new Pair<Integer, Integer>(this.position.getFirst(), this.position.getSecond() + this.getYSpeed() );
        int new_y;
        Mario ret_val = this;
        if ( (new_y = map.collidesBottom(new_pos,this.rep_size.getFirst())) == -1 ){
            this.setPos(new_pos);
            this.updateYVelocity();
        }
        else{
            ret_val = new MarioRun(new_pos.getFirst() , new_y );
            ret_val.setRepSize(this.rep_size.getFirst(), this.rep_size.getSecond(), this.scale);
        }

        ret_val.setType(this.current_type);
        return ret_val;
    }
}
