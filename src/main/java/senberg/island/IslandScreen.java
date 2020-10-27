package senberg.island;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import senberg.DecoratedScreen;
import senberg.GameScreen;
import senberg.MainMenu;

public class IslandScreen extends GameScreen implements DecoratedScreen {
    SpriteBatch spriteBatch;
    OrthographicCamera camera;
    float cameraZoomSpeed = 1;
    Map islandMap;
    Player player;
    float total = 0.0f;

    public IslandScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        spriteBatch = new SpriteBatch(8191);
        float aspectRatio = (float) Gdx.graphics.getHeight() / Gdx.graphics.getWidth();
        int viewPortWidth = 20;
        int viewPortHeight = (int) (viewPortWidth * aspectRatio);
        camera = new OrthographicCamera(viewPortWidth, viewPortHeight);
        islandMap = new RandomMap();
        Tile tile = islandMap.getRandomWalkableTile();
        player = new Player(tile.xIndex+0.5f, tile.yIndex+0.5f);


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
                camera.update();
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        total += delta;
        player.handleInput(delta, islandMap);
        camera.position.x = player.positionX;
        camera.position.y = player.positionY;
        camera.update();

        Vector3 cursorCoordinates = new Vector3();
        cursorCoordinates.x = Gdx.input.getX();
        cursorCoordinates.y = Gdx.input.getY();
        camera.unproject(cursorCoordinates);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();

        islandMap.draw(spriteBatch, camera);
        player.draw(spriteBatch, total);

        spriteBatch.end();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
        spriteBatch.dispose();
        islandMap.dispose();
        player.dispose();
    }
}
