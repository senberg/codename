package senberg.island;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import senberg.TileSet;

public class Map {
    final int MAP_SIZE = 1024;
    final Tile[][] tiles = new Tile[MAP_SIZE][MAP_SIZE];
    static TileSet tileSet;
    final int TILE_SIZE = 10;

    static void init() {
        tileSet = new TileSet("terrain.png", 32);
    }

    static void dispose() {
        tileSet.dispose();
    }

    public Map() {
        for (int x = 0; x < MAP_SIZE; x++) {
            for (int y = 0; y < MAP_SIZE; y++) {
                int mittenX = MAP_SIZE / 2;
                int xDiff = x - mittenX;

                int mittenY = MAP_SIZE / 2;
                int yDiff = y - mittenY;

                double distans = Math.sqrt(xDiff * xDiff + yDiff * yDiff);


                if (distans < MAP_SIZE / 3) {
                    Sprite ground = tileSet.createSprite(RandomHelper.getRandomInt(9) % 6, 11);
                    Tile tile = new Tile(TerrainType.grass, ground, x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE);
                    tiles[x][y] = tile;
                } else {
                    Sprite ground;

                    if (RandomHelper.getRandomInt(10) < 8) {
                        ground = tileSet.createSprite(28, 3);
                    } else {
                        ground = tileSet.createSprite(RandomHelper.getRandomInt(3) + 27, 5);
                    }

                    Tile tile = new Tile(TerrainType.water, ground, x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE);
                    tiles[x][y] = tile;
                }
            }
        }

        addTransitions();
    }

    void addTransitions() {
        // Figure out transitions
        for (int x = 1; x < MAP_SIZE-1; x++) {
            for (int y = 1; y < MAP_SIZE-1; y++) {
                Tile current = tiles[x][y];

                if (current.terrainType == TerrainType.grass){
                    TerrainType north = tiles[x][y+1].terrainType;
                    TerrainType northeast = tiles[x+1][y+1].terrainType;
                    TerrainType northwest = tiles[x-1][y+1].terrainType;
                    TerrainType east = tiles[x+1][y].terrainType;
                    TerrainType southeast = tiles[x+1][y-1].terrainType;
                    TerrainType south = tiles[x][y-1].terrainType;
                    TerrainType southwest = tiles[x-1][y-1].terrainType;
                    TerrainType west = tiles[x-1][y].terrainType;

                    if(north == TerrainType.water && west == TerrainType.water ){
                        current.setNorthWestEdge(tileSet.createSprite(28, 0));
                    }
                    else if(north == TerrainType.water){
                        current.setNorthWestEdge(tileSet.createSprite(28, 4));
                    }
                    else if(west == TerrainType.water){
                        current.setNorthWestEdge(tileSet.createSprite(29, 3));
                    }
                    else if(northwest == TerrainType.water){
                        current.setNorthWestEdge(tileSet.createSprite(29, 4));
                    }

                    if(north == TerrainType.water && east == TerrainType.water ){
                        current.setNorthEastEdge(tileSet.createSprite(29, 0));
                    }
                    else if(north == TerrainType.water){
                        current.setNorthEastEdge(tileSet.createSprite(28, 4));
                    }
                    else if(east == TerrainType.water){
                        current.setNorthEastEdge(tileSet.createSprite(27, 3));
                    }
                    else if(northeast == TerrainType.water){
                        current.setNorthEastEdge(tileSet.createSprite(27, 4));
                    }

                    if(south == TerrainType.water && west == TerrainType.water ){
                        current.setSouthWestEdge(tileSet.createSprite(28, 1));
                    }
                    else if(south == TerrainType.water){
                        current.setSouthWestEdge(tileSet.createSprite(28, 2));
                    }
                    else if(west == TerrainType.water){
                        current.setSouthWestEdge(tileSet.createSprite(29, 3));
                    }
                    else if(southwest == TerrainType.water){
                        current.setSouthWestEdge(tileSet.createSprite(29, 2));
                    }

                    if(south == TerrainType.water && east == TerrainType.water ){
                        current.setSouthEastEdge(tileSet.createSprite(29, 1));
                    }
                    else if(south == TerrainType.water){
                        current.setSouthEastEdge(tileSet.createSprite(28, 2));
                    }
                    else if(east == TerrainType.water){
                        current.setSouthEastEdge(tileSet.createSprite(27, 3));
                    }
                    else if(southeast == TerrainType.water){
                        current.setSouthEastEdge(tileSet.createSprite(27, 2));
                    }

                }
            }
        }
    }

    public void draw(Batch batch, OrthographicCamera camera) {
        float minXposition = camera.position.x - camera.viewportWidth/2;
        int minTileX = (int) Math.floor(minXposition) / TILE_SIZE;

        float minYposition = camera.position.y - camera.viewportHeight/2;
        int minTileY = (int) Math.floor(minYposition) / TILE_SIZE;

        float maxXposition = camera.position.x + camera.viewportWidth/2;
        int maxTileX = (int) Math.ceil(maxXposition) / TILE_SIZE;

        float maxYposition = camera.position.y + camera.viewportHeight/2;
        int maxTileY = (int) Math.ceil(maxYposition) / TILE_SIZE;

        for (int x = minTileX; x <= maxTileX; x++) {
            for (int y = minTileY; y <= maxTileY; y++) {
                Tile t = tiles[x][y];
                t.draw(batch);
            }
        }
    }

}
