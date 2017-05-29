package View.Entity;


import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.HashMap;

public class MarioView extends EntityView {
    private Texture texture;
    private int last_texture;
    private static final float DEFAULT_SCALE = 3;


    public MarioView(AssetManager assets, float screen_scale){
        super(DEFAULT_SCALE,screen_scale);
        this.last_texture=0;
        image_names = new HashMap<Integer, String>();
        image_names.put(0,"mario_left.png");
        image_names.put(1,"mario_right.png");
        image_names.put(2,"mario_climb_left.png");
        image_names.put(3,"mario_climb_right.png");
        image_names.put(4,"mario_run_left.png");
        image_names.put(5,"mario_run_right.png");
        image_names.put(6,"mario_morrer_cima.png");
        this.assets=assets;
        this.canJump = true;
        this.texture = assets.get(image_names.get(0));
        this.representation = new Sprite(texture,texture.getWidth(),texture.getHeight());
        this.representation.scale(this.img_scale);
    }


    public void draw(SpriteBatch spriteBatch) {
        super.draw(spriteBatch);
    }

    public void changeSprite(int option) {
        if(option!=this.last_texture) {
                    this.texture = assets.get(image_names.get(option));
                    this.representation = new Sprite(texture,texture.getWidth(),texture.getHeight());
                    this.representation.scale(this.img_scale);
            this.last_texture=option;
        }

    }

}




