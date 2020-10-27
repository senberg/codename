package senberg.island;

public class ItemTileSet extends TileSet {

    public ItemTileSet() {
        super("items.png", 32);
    }

    public enum PlayerTileType implements TileType {
        Treasure_Chest000(6, 5),
        Treasure_Chest001(7, 5),
        Treasure_Chest002(0, 6),
        Treasure_Chest003(1, 6),
        Treasure_Chest004(2, 6),
        Gold001(7, 1),
        Gold002(0, 2),
        Gold003(1, 2),
        Gold004(2, 2),
        ;

        int xPosition;
        int yPosition;

        PlayerTileType(int xPosition, int yPosition) {
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
