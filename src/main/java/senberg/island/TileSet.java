package senberg.island;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import senberg.Randomizer;

public class TileSet {
    private final int tileSizePixels;
    private final Texture texture;

    public TileSet(String filename, int tileSizePixels) {
        this.tileSizePixels = tileSizePixels;
        texture = new Texture(filename);
    }

    public Sprite createSprite(int tileX, int tileY, float size) {
        int spriteXstart = tileX * tileSizePixels;
        int spriteYstart = tileY * tileSizePixels;
        Sprite sprite = new Sprite(texture, spriteXstart, spriteYstart, tileSizePixels, tileSizePixels);
        sprite.setOrigin(0, 0);
        sprite.setScale(size / tileSizePixels);
        return sprite;
    }

    public Sprite createSprite(TileType tileType, float size) {
        return createSprite(tileType.getXPosition(), tileType.getYPosition(), size);
    }

    public Sprite createSprite(TileType tileType) {
        return createSprite(tileType.getXPosition(), tileType.getYPosition(), 0.5f);
    }

    public void dispose() {
        texture.dispose();
    }

    public interface TileType {
        int getXPosition();

        int getYPosition();

        static TileType getRandom(TileType... alternatives) {
            return alternatives[Randomizer.getInt(alternatives.length)];
        }
    }
}
