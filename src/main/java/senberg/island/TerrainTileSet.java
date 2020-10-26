package senberg.island;

public class TerrainTileSet extends TileSet {

    public TerrainTileSet() {
        super("terrain.png", 32);
    }

    public enum TerrainTileType implements TileType {
        WATER(28, 3),
        WATER_VARIATION_1(27, 5),
        WATER_VARIATION_2(28, 5),
        WATER_VARIATION_3(29, 5),
        WATER_TRANSITION_NORTH_AND_WEST(28, 0),
        WATER_TRANSITION_NORTH_AND_EAST(29, 0),
        WATER_TRANSITION_SOUTH_AND_WEST(28, 1),
        WATER_TRANSITION_SOUTH_AND_EAST(29, 1),
        WATER_TRANSITION_SOUTHEAST(27, 2),
        WATER_TRANSITION_SOUTH(28, 2),
        WATER_TRANSITION_SOUTHWEST(29, 2),
        WATER_TRANSITION_EAST(27, 3),
        WATER_TRANSITION_WEST(29, 3),
        WATER_TRANSITION_NORTHEAST(27, 4),
        WATER_TRANSITION_NORTH(28, 4),
        WATER_TRANSITION_NORTHWEST(29, 4),
        GRASS_LEAVES_1(0, 11),
        GRASS_LEAVES_2(1, 11),
        GRASS_LEAVES_3(2, 11),
        GRASS_FLOWERS_1(3, 11),
        GRASS_FLOWERS_2(4, 11),
        GRASS_FLOWERS_3(5, 11),
        GRASS(12, 11),
        ;

        int xPosition;
        int yPosition;

        TerrainTileType(int xPosition, int yPosition) {
            this.xPosition = xPosition;
            this.yPosition = yPosition;
        }

        @Override
        public int getXPosition() {
            return xPosition;
        }

        @Override
        public int getYPosition() {
            return yPosition;
        }
    }
}
