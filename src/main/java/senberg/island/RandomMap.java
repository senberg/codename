package senberg.island;

import com.badlogic.gdx.graphics.g2d.Sprite;
import senberg.Randomizer;

import static senberg.island.TerrainTileSet.TerrainTileType.*;

public class RandomMap extends Map {
    private static final int GENERATIONS = 64;

    private boolean[][] generateIslands() {
        System.out.println("Generating islands...");
        long start = System.currentTimeMillis();
        boolean[][] current = new boolean[MAP_SIZE][MAP_SIZE];
        int indexMin = 1;
        int indexMax = MAP_SIZE - 1;

        for (int x = indexMin; x < indexMax; x++) {
            for (int y = indexMin; y < indexMax; y++) {
                current[x][y] = Randomizer.getBoolean();
            }
        }

        for (int i = 0; i < GENERATIONS; i++) {
            boolean[][] generation = new boolean[MAP_SIZE][MAP_SIZE];

            for (int x = indexMin; x < indexMax; x++) {
                for (int y = indexMin; y < indexMax; y++) {
                    int neighbours = 0;
                    if (current[x - 1][y - 1]) neighbours++;
                    if (current[x - 1][y]) neighbours++;
                    if (current[x - 1][y + 1]) neighbours++;
                    if (current[x][y - 1]) neighbours++;
                    if (current[x][y]) neighbours++;
                    if (current[x][y + 1]) neighbours++;
                    if (current[x + 1][y - 1]) neighbours++;
                    if (current[x + 1][y]) neighbours++;
                    if (current[x + 1][y + 1]) neighbours++;

                    generation[x][y] = neighbours > 4;
                }
            }

            current = generation;
        }

        long timeElapsed = System.currentTimeMillis() - start;
        System.out.println("Completed in " + timeElapsed + "ms.");
        return current;
    }


    public RandomMap() {
        super();
        boolean[][] islands = generateIslands();

        System.out.println("Creating ground sprites...");
        long start = System.currentTimeMillis();

        for (int x = 0; x < MAP_SIZE; x++) {
            for (int y = 0; y < MAP_SIZE; y++) {
                if (islands[x][y]) {
                    tiles[x][y] = new Tile(TerrainType.grass, getGrassSprite(), getGrassSprite(), getGrassSprite(), getGrassSprite(), x, y);
                } else {
                    tiles[x][y] = new Tile(TerrainType.water, getWaterSprite(), getWaterSprite(), getWaterSprite(), getWaterSprite(), x, y);
                }
            }
        }

        long timeElapsed = System.currentTimeMillis() - start;
        System.out.println("Completed in " + timeElapsed + "ms.");
        addTransitions();
    }

    private Sprite getWaterSprite() {
        if (Randomizer.getInt(10) < 8) {
            return tileSet.createSprite(WATER);
        } else {
            TileSet.TileType tileType = TileSet.TileType.getRandom(WATER_VARIATION_1, WATER_VARIATION_2, WATER_VARIATION_3);
            return tileSet.createSprite(tileType);
        }
    }

    private Sprite getGrassSprite() {
        TileSet.TileType tileType = TileSet.TileType.getRandom(GRASS, GRASS_LEAVES_1, GRASS_LEAVES_2, GRASS_LEAVES_3, GRASS_FLOWERS_1, GRASS_FLOWERS_2, GRASS_FLOWERS_3);
        return tileSet.createSprite(tileType);
    }
}
