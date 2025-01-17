package ua.gram.munhauzen.interaction.hare.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import ua.gram.munhauzen.MunhauzenGame;

public class Butterflies extends Image {

    public Butterflies(Texture texture) {
        super(texture);
    }

    public void pause() {
        clearActions();
    }

    public void start() {
        addAction(
                Actions.forever(Actions.rotateBy(-90, 4f))
        );
    }

    @Override
    public void layout() {
        super.layout();

        float size = MunhauzenGame.WORLD_WIDTH * 177.8f / 100;
        float x = (MunhauzenGame.WORLD_WIDTH - size) / 2;
        float y = size * -63.52f / 100;

        setBounds(x, y, size, size);

        setOrigin(getWidth() * .5f, getHeight() * .5f);
    }
}
