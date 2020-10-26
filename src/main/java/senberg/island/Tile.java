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
    int positionX;
    int positionY;

    public Tile(TerrainType terrainType, Sprite northWestGround, Sprite northEastGround, Sprite southWestGround, Sprite southEastGround, int positionX, int positionY) {
        this.terrainType = terrainType;
        this.northWestGround = northWestGround;
        this.northEastGround = northEastGround;
        this.southWestGround = southWestGround;
        this.southEastGround = southEastGround;
        this.positionX = positionX;
        this.positionY = positionY;
        northWestGround.translate(positionX, positionY + 0.5f);
        northEastGround.translate(positionX + 0.5f, positionY + 0.5f);
        southWestGround.translate(positionX, positionY);
        southEastGround.translate(positionX + 0.5f, positionY);
    }

    public void setNorthWestEdge(Sprite northWestEdge) {
        this.northWestEdge = northWestEdge;
        northWestEdge.translate(positionX, positionY + 0.5f);
    }

    public void setNorthEastEdge(Sprite northEastEdge) {
        this.northEastEdge = northEastEdge;
        northEastEdge.translate(positionX + 0.5f, positionY + 0.5f);
    }

    public void setSouthWestEdge(Sprite southWestEdge) {
        this.southWestEdge = southWestEdge;
        southWestEdge.translate(positionX, positionY);
    }

    public void setSouthEastEdge(Sprite southEastEdge) {
        this.southEastEdge = southEastEdge;
        southEastEdge.translate(positionX + 0.5f, positionY);
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
