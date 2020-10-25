package senberg.island;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class RandomMap extends Map {
    private static final int GENERATIONS = 64;

    private boolean[][] generateIslands() {
        boolean[][] current = new boolean[MAP_SIZE][MAP_SIZE];

        for (int x = 0; x < MAP_SIZE; x++) {
            for (int y = 0; y < MAP_SIZE; y++) {
                if (x == 0 || x == MAP_SIZE - 1 || y == 0 || y == MAP_SIZE - 1) {
                    current[x][y] = false;
                } else {
                    current[x][y] = RandomHelper.getRandomBoolean();
                }
            }
        }

        for (int i = 0; i < GENERATIONS; i++) {
            boolean[][] generation = new boolean[MAP_SIZE][MAP_SIZE];

            for (int x = 0; x < MAP_SIZE; x++) {
                for (int y = 0; y < MAP_SIZE; y++) {
                    if (x == 0 || x == MAP_SIZE - 1 || y == 0 || y == MAP_SIZE - 1) {
                        generation[x][y] = false;
                    } else {
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
            }

            current = generation;
        }


        return current;
    }




    public RandomMap() {
        boolean[][] islands = generateIslands();

        for (int x = 0; x < MAP_SIZE; x++) {
            for (int y = 0; y < MAP_SIZE; y++) {
                if (islands[x][y]) {
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
}
