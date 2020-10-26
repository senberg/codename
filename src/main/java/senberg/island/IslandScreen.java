package senberg.island;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import senberg.DecoratedScreen;
import senberg.GameScreen;
import senberg.MainMenu;

public class IslandScreen extends GameScreen implements DecoratedScreen {
    SpriteBatch spriteBatch;
    OrthographicCamera camera;
    float cameraSpeed = 20;
    float cameraZoomSpeed = 1;
    float cameraZoomMinimum = 0.25f;
    float cameraZoomMaximum = 4;
    Map islandMap;
    Player player;
    float total = 0.0f;

    public IslandScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        Map.init();
        spriteBatch = new SpriteBatch(8191);
        float aspectRatio = (float) Gdx.graphics.getHeight() / Gdx.graphics.getWidth();
        int viewPortWidth = 25;
        int viewPortHeight = (int) (viewPortWidth * aspectRatio);
        camera = new OrthographicCamera(viewPortWidth, viewPortHeight);
        camera.translate(Map.MAP_SIZE / 2.0f, Map.MAP_SIZE / 2.0f);
        camera.update();
        player = new Player(Map.MAP_SIZE / 2.0f, Map.MAP_SIZE / 2.0f);

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

            @Override
            public boolean scrolled(int amount) {
                camera.zoom += amount * cameraZoomSpeed / 20;
                camera.zoom = Math.max(camera.zoom, cameraZoomMinimum);
                camera.zoom = Math.min(camera.zoom, cameraZoomMaximum);
                camera.update();
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        total += delta;
        //handleInput(delta);
        player.handleInput(delta);
        camera.position.x = player.positionX;
        camera.position.y = player.positionY;
        camera.update();

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();

        islandMap.draw(spriteBatch, camera);
        player.draw(spriteBatch, total);

        spriteBatch.end();
    }

    private void handleInput(float delta) {
        boolean updated = false;

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            camera.position.y += delta * cameraSpeed;
            updated = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            camera.position.y -= delta * cameraSpeed;
            updated = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            camera.position.x -= delta * cameraSpeed;
            updated = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            camera.position.x += delta * cameraSpeed;
            updated = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            updated = true;
            camera.zoom -= delta * cameraZoomSpeed;
            camera.zoom = Math.max(camera.zoom, cameraZoomMinimum);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            updated = true;
            camera.zoom += delta * cameraZoomSpeed;
            camera.zoom = Math.min(camera.zoom, cameraZoomMaximum);
        }

        if (updated) {
            camera.update();
        }
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
        spriteBatch.dispose();
        Map.dispose();
    }
}
