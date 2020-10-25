package senberg.island;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector3;
import senberg.DecoratedScreen;
import senberg.GameScreen;
import senberg.MainMenu;

import java.util.HashSet;
import java.util.Set;

public class IslandScreen extends GameScreen implements DecoratedScreen {
    SpriteBatch spriteBatch;
    OrthographicCamera camera;
    float cameraSpeed = 200;
    float cameraZoomSpeed = 1;
    float cameraZoomMinimum = 0.25f;
    float cameraZoomMaximum = 4;
    Map islandMap;
    public IslandScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        Map.init();
        spriteBatch = new SpriteBatch(8191);
        float aspectRatio = (float)Gdx.graphics.getHeight() / Gdx.graphics.getWidth();
        int viewPortWidth = 256;
        int viewPortHeight = (int)(viewPortWidth * aspectRatio);
        camera = new OrthographicCamera(viewPortWidth, viewPortHeight);
        camera.translate(2800, 2600);
        camera.update();

        islandMap = new RandomMap();

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                switch (keycode) {
                    case Input.Keys.ESCAPE:
                        game.setScreen(new MainMenu(game));
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    @Override
    public void render(float delta) {
        handleInput(delta);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();

        islandMap.draw(spriteBatch, camera);

        spriteBatch.end();
    }

    private void handleInput(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            camera.position.y += delta * cameraSpeed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            camera.position.y -= delta * cameraSpeed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            camera.position.x -= delta * cameraSpeed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            camera.position.x += delta * cameraSpeed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            camera.zoom -= delta * cameraZoomSpeed;
            camera.zoom = Math.max(camera.zoom, cameraZoomMinimum);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            camera.zoom += delta * cameraZoomSpeed;
            camera.zoom = Math.min(camera.zoom, cameraZoomMaximum);
        }

        camera.update();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
        spriteBatch.dispose();
        Map.dispose();
    }
}
