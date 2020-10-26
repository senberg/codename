package senberg.island;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Character {
    float positionX;
    float positionY;
    float speed = 2;
    Sprite avatar;

    public Character(float positionX, float positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        CharacterTileSet tileSet = new CharacterTileSet();
        avatar = tileSet.createSprite(CharacterTileSet.CharacterTileType.Front_Idle_000);
        avatar.translate(positionX, positionY);
    }

    public void handleInput(float delta) {

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            positionY += speed * delta;
            avatar.translateY(speed * delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            positionY -= speed * delta;
            avatar.translateY(-speed * delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            positionX -= speed * delta;
            avatar.translateX(-speed * delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            positionX += speed * delta;
            avatar.translateX(speed * delta);
        }
    }

    public void draw(Batch batch) {
        avatar.draw(batch);
    }
}
