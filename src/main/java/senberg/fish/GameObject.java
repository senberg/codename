package senberg.fish;

import com.badlogic.gdx.graphics.g2d.Batch;

public interface GameObject {
    void update(float delta);

    void draw(Batch batch);
}
