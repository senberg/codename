package senberg.island;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

import static senberg.island.ItemTileSet.PlayerTileType.*;

public class TreasureChest {
    static final float size = 1.0f;
    private static final float frameDuration = 0.2f;
    float positionX;
    float positionY;
    ItemTileSet tileSet;
    TileSet.TileType[] tileTypes = {Treasure_Chest000, Treasure_Chest001, Treasure_Chest002, Treasure_Chest003, Treasure_Chest004};

    public TreasureChest(float positionX, float positionY) {
        tileSet = new ItemTileSet();
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public void dispose() {
        tileSet.dispose();
    }


    public void draw(Batch batch, float total) {
        float animationDuration = tileTypes.length * frameDuration;
        float currentAnimationTime = total % animationDuration;
        int currentAnimationNumber = (int) Math.floor(currentAnimationTime / frameDuration);
        TileSet.TileType tileType = tileTypes[currentAnimationNumber];
        Sprite avatar = tileSet.createSprite(tileType, size);
        avatar.setPosition(positionX - size / 2, positionY - size / 2);
        avatar.draw(batch);
    }
}
