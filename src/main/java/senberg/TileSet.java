package senberg;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class TileSet {
    Texture texture;
    int tileSize;

    public TileSet(String filename, int tileSize){
        this.tileSize = tileSize;
        texture = new Texture(filename);
    }

    public Sprite createSprite(int tileX, int tileY){
        int spriteXstart = tileX * tileSize;
        int spriteYstart = tileY * tileSize;

        return new Sprite(texture, spriteXstart, spriteYstart, tileSize, tileSize);
    }

    public void dispose(){
        texture.dispose();
    }
}
