package senberg.island;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import senberg.Randomizer;

import static senberg.island.TerrainTileSet.TerrainTileType.*;

public class Map {
    public static final int MAP_SIZE = 256;

    final Tile[][] tiles = new Tile[MAP_SIZE][MAP_SIZE];
    final TileSet tileSet;

    public Map() {
        tileSet = new TerrainTileSet();
    }

    void dispose() {
        tileSet.dispose();
    }

    void addTransitions() {
        System.out.println("Adding grass-water transitions...");
        long start = System.currentTimeMillis();

        for (int x = 1; x < MAP_SIZE - 1; x++) {
            for (int y = 1; y < MAP_SIZE - 1; y++) {
                Tile tile = tiles[x][y];

                if (tile.terrainType == TerrainType.grass) {
                    boolean north = tiles[x][y + 1].terrainType == TerrainType.water;
                    boolean northeast = tiles[x + 1][y + 1].terrainType == TerrainType.water;
                    boolean northwest = tiles[x - 1][y + 1].terrainType == TerrainType.water;
                    boolean east = tiles[x + 1][y].terrainType == TerrainType.water;
                    boolean southeast = tiles[x + 1][y - 1].terrainType == TerrainType.water;
                    boolean south = tiles[x][y - 1].terrainType == TerrainType.water;
                    boolean southwest = tiles[x - 1][y - 1].terrainType == TerrainType.water;
                    boolean west = tiles[x - 1][y].terrainType == TerrainType.water;

                    if (north && west) {
                        tile.setNorthWestEdge(tileSet.createSprite(WATER_TRANSITION_NORTH_AND_WEST));
                    } else if (north) {
                        tile.setNorthWestEdge(tileSet.createSprite(WATER_TRANSITION_NORTH));
                    } else if (west) {
                        tile.setNorthWestEdge(tileSet.createSprite(WATER_TRANSITION_WEST));
                    } else if (northwest) {
                        tile.setNorthWestEdge(tileSet.createSprite(WATER_TRANSITION_NORTHWEST));
                    }

                    if (north && east) {
                        tile.setNorthEastEdge(tileSet.createSprite(WATER_TRANSITION_NORTH_AND_EAST));
                    } else if (north) {
                        tile.setNorthEastEdge(tileSet.createSprite(WATER_TRANSITION_NORTH));
                    } else if (east) {
                        tile.setNorthEastEdge(tileSet.createSprite(WATER_TRANSITION_EAST));
                    } else if (northeast) {
                        tile.setNorthEastEdge(tileSet.createSprite(WATER_TRANSITION_NORTHEAST));
                    }

                    if (south && west) {
                        tile.setSouthWestEdge(tileSet.createSprite(WATER_TRANSITION_SOUTH_AND_WEST));
                    } else if (south) {
                        tile.setSouthWestEdge(tileSet.createSprite(WATER_TRANSITION_SOUTH));
                    } else if (west) {
                        tile.setSouthWestEdge(tileSet.createSprite(WATER_TRANSITION_WEST));
                    } else if (southwest) {
                        tile.setSouthWestEdge(tileSet.createSprite(WATER_TRANSITION_SOUTHWEST));
                    }

                    if (south && east) {
                        tile.setSouthEastEdge(tileSet.createSprite(WATER_TRANSITION_SOUTH_AND_EAST));
                    } else if (south) {
                        tile.setSouthEastEdge(tileSet.createSprite(WATER_TRANSITION_SOUTH));
                    } else if (east) {
                        tile.setSouthEastEdge(tileSet.createSprite(WATER_TRANSITION_EAST));
                    } else if (southeast) {
                        tile.setSouthEastEdge(tileSet.createSprite(WATER_TRANSITION_SOUTHEAST));
                    }
                }
            }
        }

        long timeElapsed = System.currentTimeMillis() - start;
        System.out.println("Completed in " + timeElapsed + "ms.");
    }

    public void draw(Batch batch, OrthographicCamera camera) {

        float halfViewportWidth = camera.viewportWidth * camera.zoom / 2;
        int minTileX = (int) Math.floor(camera.position.x - halfViewportWidth);
        minTileX = Math.max(minTileX, 0);
        int maxTileX = (int) Math.ceil(camera.position.x + halfViewportWidth);
        maxTileX = Math.min(maxTileX, MAP_SIZE - 1);

        float halfViewportHeight = camera.viewportHeight * camera.zoom / 2;
        int minTileY = (int) Math.floor(camera.position.y - halfViewportHeight);
        minTileY = Math.max(minTileY, 0);
        int maxTileY = (int) Math.ceil(camera.position.y + halfViewportHeight);
        maxTileY = Math.min(maxTileY, MAP_SIZE - 1);



        for (int x = minTileX; x <= maxTileX; x++) {
            for (int y = minTileY; y <= maxTileY; y++) {
                tiles[x][y].draw(batch);
            }
        }
    }

    public float getTileSize(){
        return 1;
    }

    public boolean isWalkable(float x, float y){
        Tile tile = getTileFromPosition(x, y);
        return tile != null && tile.terrainType == TerrainType.grass;
    }
    public boolean isWalkable(float size, float x, float y){
        return  isWalkable(x, y+size/2) &&
                isWalkable(x, y-size/2) &&
                isWalkable(x+size/3, y+size/3) &&
                isWalkable(x+size/3, y-size/3) &&
                isWalkable(x+size/2, y) &&
                isWalkable(x-size/2, y) &&
                isWalkable(x-size/3, y-size/3) &&
                isWalkable(x-size/3, y+size/3);

    }

    private Tile getTileFromPosition(float x, float y){
        int xIndex = (int)Math.floor(x);
        int yIndex = (int)Math.floor(y);

        if(xIndex > 0 && xIndex < MAP_SIZE && yIndex > 0 && yIndex < MAP_SIZE) {
            return tiles[xIndex][yIndex];
        }
        else{
            return null;
        }
    }

    public Tile getRandomWalkableTile() {
        while(true){
            int x = Randomizer.getInt(MAP_SIZE);
            int y = Randomizer.getInt(MAP_SIZE);
            Tile tile = tiles[x][y];

            if(tile.terrainType == TerrainType.grass){
                return tile;
            }
        }
    }
}
