package senberg.island;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;

import static senberg.island.ItemTileSet.PlayerTileType.*;

public class Gold {
    private static final float size = 1.0f;
    private static final float frameDuration = 0.25f;
    ItemTileSet tileSet;
    TileSet.TileType[] tileTypes = {Gold001, Gold002, Gold003, Gold004};
    int count = 0;
    BitmapFont font;


    public Gold() {
        tileSet = new ItemTileSet();
        font = new BitmapFont();
        font.setUseIntegerPositions(false);
    }

    public void dispose() {
        tileSet.dispose();
        font.dispose();
    }


    public void draw(Batch batch, float total, OrthographicCamera camera) {
        float animationDuration = tileTypes.length * frameDuration;
        float currentAnimationTime = total % animationDuration;
        int currentAnimationNumber = (int) Math.floor(currentAnimationTime / frameDuration);
        TileSet.TileType tileType = tileTypes[currentAnimationNumber];

        Sprite coin = tileSet.createSprite(tileType, size);
        coin.setScale(4f);
        float coinX = Gdx.graphics.getWidth() * 0f;
        float coinY = Gdx.graphics.getHeight() * 0.90f;
        coin.setPosition(coinX, coinY);
        coin.draw(batch);

        font.getData().setScale(5f);
        float fontX = Gdx.graphics.getWidth() * 0.07f;
        float fontY = Gdx.graphics.getHeight() * 0.983f;
        font.draw(batch, Integer.toString(count), fontX, fontY );

    }
}
