package senberg;

import com.badlogic.gdx.Screen;

public interface DecoratedScreen extends Screen {
    @Override
    public default void resize(int width, int height) {
    }

    @Override
    public default void pause() {
    }

    @Override
    public default void resume() {
    }

    @Override
    public default void dispose() {
    }

    @Override
    public default void show() {
    }

    @Override
    public default void render(float delta) {
    }

    @Override
    public default void hide() {
    }
}
