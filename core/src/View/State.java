package View;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by asus on 02/06/2017.
 */

public abstract class State extends ScreenAdapter {
    protected boolean advanceState;
    protected SpriteBatch batch;
    protected StateManager sm;

    public abstract void render(float delta);
    protected abstract void handleInput();
    public abstract void dispose();
}
