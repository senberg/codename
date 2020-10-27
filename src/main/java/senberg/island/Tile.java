package senberg.island;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Tile {
    TerrainType terrainType;
    private final Sprite northWestGround;
    private final Sprite northEastGround;
    private final Sprite southWestGround;
    private final Sprite southEastGround;
    private Sprite northWestEdge;
    private Sprite northEastEdge;
    private Sprite southWestEdge;
    private Sprite southEastEdge;
    int xIndex;
    int yIndex;

    public Tile(TerrainType terrainType, Sprite northWestGround, Sprite northEastGround, Sprite southWestGround, Sprite southEastGround, int xIndex, int yIndex) {
        this.terrainType = terrainType;
        this.northWestGround = northWestGround;
        this.northEastGround = northEastGround;
        this.southWestGround = southWestGround;
        this.southEastGround = southEastGround;
        northWestGround.setPosition(xIndex, yIndex + 0.5f);
        northEastGround.setPosition(xIndex + 0.5f, yIndex + 0.5f);
        southWestGround.setPosition(xIndex, yIndex);
        southEastGround.setPosition(xIndex + 0.5f, yIndex);
        this.xIndex = xIndex;
        this.yIndex = yIndex;
    }

    public void setNorthWestEdge(Sprite northWestEdge) {
        this.northWestEdge = northWestEdge;
        northWestEdge.translate(xIndex, yIndex + 0.5f);
    }

    public void setNorthEastEdge(Sprite northEastEdge) {
        this.northEastEdge = northEastEdge;
        northEastEdge.translate(xIndex + 0.5f, yIndex + 0.5f);
    }

    public void setSouthWestEdge(Sprite southWestEdge) {
        this.southWestEdge = southWestEdge;
        southWestEdge.translate(xIndex, yIndex);
    }

    public void setSouthEastEdge(Sprite southEastEdge) {
        this.southEastEdge = southEastEdge;
        southEastEdge.translate(xIndex + 0.5f, yIndex);
    }

    public void draw(Batch batch) {
        northWestGround.draw(batch);
        northEastGround.draw(batch);
        southWestGround.draw(batch);
        southEastGround.draw(batch);

        if (northWestEdge != null) {
            northWestEdge.draw(batch);
        }
        if (northEastEdge != null) {
            northEastEdge.draw(batch);
        }
        if (southWestEdge != null) {
            southWestEdge.draw(batch);
        }
        if (southEastEdge != null) {
            southEastEdge.draw(batch);
        }
    }
}
