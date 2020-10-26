package senberg.fish;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import senberg.Randomizer;

import java.util.Arrays;

public class Fish implements GameObject {
    private static Texture[] textures;

    static void init() {
        textures = new Texture[4];
        textures[0] = new Texture("goldfish.png");
        textures[1] = new Texture("smallfish.png");
        textures[2] = new Texture("silverfish.png");
        textures[3] = new Texture("uglyfish.png");
    }

    static void dispose() {
        Arrays.stream(textures).forEach(Texture::dispose);
    }

    private final Sprite sprite;
    private float speed;

    public Fish(float x, float y) {
        int fishType = Randomizer.getInt(4);
        sprite = new Sprite(textures[fishType]);
        speed = 1 + Randomizer.nextFloat(100);

        if (fishType == 1 || fishType == 3) {
            speed *= -1;
        }

        if (Randomizer.getBoolean()) {
            sprite.flip(true, false);
            speed *= -1;
        }

        sprite.setScale(0.01f + Randomizer.nextFloat(0.05f));
        sprite.setPosition(x, y);
    }

    @Override
    public void update(float delta) {
        sprite.translateX(delta * speed);

        if (Randomizer.getInt(10000) == 0) {
            sprite.flip(true, false);
            speed = -1 * Math.signum(speed) * (1 + Randomizer.nextFloat(100));
        }
    }

    @Override
    public void draw(Batch batch) {
        sprite.draw(batch);
    }
}
