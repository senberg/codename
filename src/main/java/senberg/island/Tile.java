package senberg.island;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Tile {
    TerrainType terrainType;
    private Sprite ground;
    private Sprite northWestEdge;
    private Sprite northEastEdge;
    private Sprite southWestEdge;
    private Sprite southEastEdge;
    int positionX;
    int positionY;
    int tileSize;

    public Tile(TerrainType terrainType, Sprite ground, int positionX, int positionY, int tileSize) {
        this.terrainType = terrainType;
        this.ground = ground;
        this.positionX = positionX;
        this.positionY = positionY;
        this.tileSize = tileSize;
        place(ground);
    }

    private void place(Sprite ground) {
        float scale = tileSize / ground.getHeight();
        ground.setOrigin(0, 0);
        ground.translate(positionX, positionY);
        ground.setScale(scale);
    }

    public void setNorthWestEdge(Sprite northWestEdge) {
        this.northWestEdge = northWestEdge;
        float scale = tileSize / northWestEdge.getHeight() / 2;
        northWestEdge.setOrigin(0, 0);
        northWestEdge.translate(positionX, positionY + tileSize / 2);
        northWestEdge.setScale(scale);
    }

    public void setNorthEastEdge(Sprite northEastEdge) {
        this.northEastEdge = northEastEdge;
        float scale = tileSize / northEastEdge.getHeight() / 2;
        northEastEdge.setOrigin(0, 0);
        northEastEdge.translate(positionX + tileSize / 2, positionY + tileSize / 2);
        northEastEdge.setScale(scale);
    }

    public void setSouthWestEdge(Sprite southWestEdge) {
        this.southWestEdge = southWestEdge;
        float scale = tileSize / southWestEdge.getHeight() / 2;
        southWestEdge.setOrigin(0, 0);
        southWestEdge.translate(positionX, positionY);
        southWestEdge.setScale(scale);
    }

    public void setSouthEastEdge(Sprite southEastEdge) {
        this.southEastEdge = southEastEdge;
        float scale = tileSize / southEastEdge.getHeight() / 2;
        southEastEdge.setOrigin(0, 0);
        southEastEdge.translate(positionX + tileSize / 2, positionY);
        southEastEdge.setScale(scale);
    }

    public void draw(Batch batch) {
        ground.draw(batch);

        if(northWestEdge != null) {
            northWestEdge.draw(batch);
        }
        if(northEastEdge != null) {
            northEastEdge.draw(batch);
        }
        if(southWestEdge != null) {
            southWestEdge.draw(batch);
        }
        if(southEastEdge != null) {
            southEastEdge.draw(batch);
        }
    }
}

