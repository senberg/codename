package senberg.island;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

import static senberg.island.Player.Action.*;
import static senberg.island.PlayerTileSet.PlayerTileType.*;

public class Player {
    static final float size = 1.0f;
    private static final float frameDuration = 0.05f;
    float positionX;
    float positionY;
    float speed = 2f;
    Action action = Action.front_idle;
    PlayerTileSet tileSet;

    public Player(float positionX, float positionY) {
        tileSet = new PlayerTileSet();
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public void dispose() {
        tileSet.dispose();
    }

    public void handleInput(float delta, Map map) {
        boolean moving = false;

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            action = back_run;
            float presumedY = positionY + speed * delta;
            if (map.isWalkable(size, positionX, presumedY)) {
                positionY = presumedY;
                moving = true;
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            action = front_run;
            float presumedY = positionY - speed * delta;
            if (map.isWalkable(size, positionX, presumedY)) {
                positionY = presumedY;
                moving = true;
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            action = side_run_left;
            float presumedX = positionX - speed * delta;
            if (map.isWalkable(size, presumedX, positionY)) {
                positionX = presumedX;
                moving = true;
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            action = side_run_right;
            float presumedX = positionX + speed * delta;
            if (map.isWalkable(size, presumedX, positionY)) {
                positionX = presumedX;
                moving = true;
            }
        }

        if (!moving) {
            switch (action) {
                case back_run:
                    action = back_idle;
                    break;
                case front_run:
                    action = front_idle;
                    break;
                case side_run_left:
                    action = side_idle_left;
                    break;
                case side_run_right:
                    action = side_idle_right;
                    break;
            }
        }
    }

    public void draw(Batch batch, float total) {
        float animationDuration = action.tileTypes.length * frameDuration;
        float currentAnimationTime = total % animationDuration;
        int currentAnimationNumber = (int) Math.floor(currentAnimationTime / frameDuration);
        TileSet.TileType tileType = action.tileTypes[currentAnimationNumber];
        Sprite avatar = tileSet.createSprite(tileType, size);

        if (action.flip) {
            avatar.setFlip(true, false);
        }

        avatar.setPosition(positionX - size / 2, positionY + 0.2f - size / 2);
        avatar.draw(batch);
    }

    enum Action {
        front_idle(Front_Idle_000, Front_Idle_001, Front_Idle_002, Front_Idle_003, Front_Idle_004, Front_Idle_005, Front_Idle_006, Front_Idle_007, Front_Idle_008, Front_Idle_009, Front_Idle_010, Front_Idle_011),
        back_idle(Back_Idle_000, Back_Idle_001, Back_Idle_002, Back_Idle_003, Back_Idle_004, Back_Idle_005, Back_Idle_006, Back_Idle_007, Back_Idle_008, Back_Idle_009, Back_Idle_010, Back_Idle_011),
        side_idle_right(Side_Idle_000, Side_Idle_001, Side_Idle_002, Side_Idle_003, Side_Idle_004, Side_Idle_005, Side_Idle_006, Side_Idle_007, Side_Idle_008, Side_Idle_009, Side_Idle_010, Side_Idle_011),
        side_idle_left(true, Side_Idle_000, Side_Idle_001, Side_Idle_002, Side_Idle_003, Side_Idle_004, Side_Idle_005, Side_Idle_006, Side_Idle_007, Side_Idle_008, Side_Idle_009, Side_Idle_010, Side_Idle_011),
        front_run(Front_Run_000, Front_Run_001, Front_Run_002, Front_Run_003, Front_Run_004, Front_Run_005, Front_Run_006, Front_Run_007, Front_Run_008, Front_Run_009),
        back_run(Back_Run_000, Back_Run_001, Back_Run_002, Back_Run_003, Back_Run_004, Back_Run_005, Back_Run_006, Back_Run_007, Back_Run_008, Back_Run_009),
        side_run_right(Side_Run_000, Side_Run_001, Side_Run_002, Side_Run_003, Side_Run_004, Side_Run_005, Side_Run_006, Side_Run_007, Side_Run_008, Side_Run_009),
        side_run_left(true, Side_Run_000, Side_Run_000, Side_Run_001, Side_Run_002, Side_Run_003, Side_Run_004, Side_Run_005, Side_Run_006, Side_Run_007, Side_Run_008, Side_Run_009);

        TileSet.TileType[] tileTypes;
        boolean flip;

        Action(TileSet.TileType... tileTypes) {
            this.tileTypes = tileTypes;
        }

        Action(boolean flip, TileSet.TileType... tileTypes) {
            this.flip = flip;
            this.tileTypes = tileTypes;
        }
    }
}