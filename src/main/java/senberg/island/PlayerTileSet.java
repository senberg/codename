package senberg.island;

public class PlayerTileSet extends TileSet {

    public PlayerTileSet() {
        super("player.png", 600);
    }

    public enum PlayerTileType implements TileType {
        Back_Idle_000(0, 0),
        Back_Idle_001(1, 0),
        Back_Idle_002(2, 0),
        Back_Idle_003(3, 0),
        Back_Idle_004(4, 0),
        Back_Idle_005(5, 0),
        Back_Idle_006(0, 1),
        Back_Idle_007(1, 1),
        Back_Idle_008(2, 1),
        Back_Idle_009(2, 1),
        Back_Idle_010(4, 1),
        Back_Idle_011(5, 1),
        Back_Run_000(0, 2),
        Back_Run_001(1, 2),
        Back_Run_002(2, 2),
        Back_Run_003(3, 2),
        Back_Run_004(4, 2),
        Back_Run_005(5, 2),
        Back_Run_006(0, 3),
        Back_Run_007(1, 3),
        Back_Run_008(2, 3),
        Back_Run_009(3, 3),
        Front_Idle_000(4, 3),
        Front_Idle_001(5, 3),
        Front_Idle_002(0, 4),
        Front_Idle_003(1, 4),
        Front_Idle_004(2, 4),
        Front_Idle_005(3, 4),
        Front_Idle_006(4, 4),
        Front_Idle_007(5, 4),
        Front_Idle_008(0, 5),
        Front_Idle_009(1, 5),
        Front_Idle_010(2, 5),
        Front_Idle_011(3, 5),
        Front_Run_000(4, 5),
        Front_Run_001(5, 5),
        Front_Run_002(0, 6),
        Front_Run_003(1, 6),
        Front_Run_004(2, 6),
        Front_Run_005(3, 6),
        Front_Run_006(4, 6),
        Front_Run_007(5, 6),
        Front_Run_008(0, 7),
        Front_Run_009(1, 7),
        Side_Idle_000(2, 7),
        Side_Idle_001(3, 7),
        Side_Idle_002(4, 7),
        Side_Idle_003(5, 7),
        Side_Idle_004(0, 8),
        Side_Idle_005(1, 8),
        Side_Idle_006(2, 8),
        Side_Idle_007(3, 8),
        Side_Idle_008(4, 8),
        Side_Idle_009(5, 8),
        Side_Idle_010(0, 9),
        Side_Idle_011(1, 9),
        Side_Run_000(2, 9),
        Side_Run_001(3, 9),
        Side_Run_002(4, 9),
        Side_Run_003(5, 9),
        Side_Run_004(0, 10),
        Side_Run_005(1, 10),
        Side_Run_006(2, 10),
        Side_Run_007(3, 10),
        Side_Run_008(4, 10),
        Side_Run_009(5, 10),
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
