package senberg.island;

public class CharacterTileSet extends TileSet {

    public CharacterTileSet() {
        super("character.tileset.png", 602);
    }

    public enum CharacterTileType implements TileType {
        Back_Idle_000(0, 0),
        Back_Idle_001(1, 0),
        Back_Idle_002(2, 0),
        Back_Idle_003(3, 0),
        Back_Idle_004(4, 0),
        Back_Idle_005(5, 0),
        Back_Idle_006(6, 0),
        Back_Idle_007(7, 0),
        Back_Idle_008(8, 0),
        Back_Idle_009(9, 0),
        Back_Idle_010(10, 0),
        Back_Idle_011(11, 0),
        Back_Run_000(12, 0),
        Back_Run_001(13, 0),
        Back_Run_002(14, 0),
        Back_Run_003(15, 0),
        Back_Run_004(16, 0),
        Back_Run_005(17, 0),
        Back_Run_006(18, 0),
        Back_Run_007(19, 0),
        Back_Run_008(20, 0),
        Back_Run_009(21, 0),
        Front_Idle_000(22, 0),
        Front_Idle_001(23, 0),
        Front_Idle_002(24, 0),
        Front_Idle_003(25, 0),
        Front_Idle_004(26, 0),
        Front_Idle_005(27, 0),
        Front_Idle_006(28, 0),
        Front_Idle_007(29, 0),
        Front_Idle_008(30, 0),
        Front_Idle_009(31, 0),
        Front_Idle_010(32, 0),
        Front_Idle_011(33, 0),
        Front_Run_000(34, 0),
        Front_Run_001(35, 0),
        Front_Run_002(36, 0),
        Front_Run_003(37, 0),
        Front_Run_004(38, 0),
        Front_Run_005(39, 0),
        Front_Run_006(40, 0),
        Front_Run_007(41, 0),
        Front_Run_008(42, 0),
        Front_Run_009(43, 0),
        Side_Idle_000(44, 0),
        Side_Idle_001(45, 0),
        Side_Idle_002(46, 0),
        Side_Idle_003(47, 0),
        Side_Idle_004(48, 0),
        Side_Idle_005(49, 0),
        Side_Idle_006(50, 0),
        Side_Idle_007(51, 0),
        Side_Idle_008(52, 0),
        Side_Idle_009(53, 0),
        Side_Idle_010(54, 0),
        Side_Idle_011(55, 0),
        Side_Run_000(56, 0),
        Side_Run_001(57, 0),
        Side_Run_002(58, 0),
        Side_Run_003(59, 0),
        Side_Run_004(60, 0),
        Side_Run_005(61, 0),
        Side_Run_006(62, 0),
        Side_Run_007(63, 0),
        Side_Run_008(64, 0),
        Side_Run_009(65, 0),
        ;

        int xPosition;
        int yPosition;

        CharacterTileType(int xPosition, int yPosition) {
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
