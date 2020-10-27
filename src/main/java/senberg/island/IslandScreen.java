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
import senberg.Randomizer;

import java.util.LinkedList;
import java.util.List;

public class IslandScreen extends GameScreen implements DecoratedScreen {
    SpriteBatch spriteBatch;
    SpriteBatch hud;
    OrthographicCamera camera;
    float cameraZoomSpeed = 1;
    Map islandMap;
    Player player;
    float total = 0.0f;
    List<TreasureChest> allChests = new LinkedList<>();
    Gold gold;

    public IslandScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        spriteBatch = new SpriteBatch(8191);
        hud = new SpriteBatch();
        float aspectRatio = (float) Gdx.graphics.getHeight() / Gdx.graphics.getWidth();
        int viewPortWidth = 20;
        int viewPortHeight = (int) (viewPortWidth * aspectRatio);
        camera = new OrthographicCamera(viewPortWidth, viewPortHeight);
        islandMap = new RandomMap();
        Tile tile = islandMap.getRandomWalkableTile();
        player = new Player(tile.xIndex+0.5f, tile.yIndex+0.5f);
        for (int i = 0; i < 500; i++) {
            TreasureChest treasureChest;
            Tile treasureTile = islandMap.getRandomWalkableTile();
            treasureChest = new TreasureChest(treasureTile.xIndex+0.5f, treasureTile.yIndex+0.5f);
            allChests.add(treasureChest);
        }
        gold = new Gold();


        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                switch (keycode) {
                    case Input.Keys.T:
                    {
                        Tile tile = islandMap.getRandomWalkableTile();
                        player.positionX = tile.xIndex+0.5f;
                        player.positionY = tile.yIndex+0.5f;
                        return true;
                    }
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
        handleCollisions();
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
        for(TreasureChest kista : allChests) {
            kista.draw(spriteBatch, total);
        }
        player.draw(spriteBatch, total);

        spriteBatch.end();
        hud.begin();
        gold.draw(hud, total, camera);
        hud.end();
    }

    private void handleCollisions() {

        TreasureChest opened = null;

        for(TreasureChest kista : allChests) {
           float xDifference = kista.positionX - player.positionX;
           float yDifference = kista.positionY - player.positionY;
           boolean xCollision = kista.size / 4 + player.size / 4 > Math.abs( xDifference);
           boolean yCollision = kista.size / 6 + player.size / 6 > Math.abs( yDifference);
           if (xCollision && yCollision){
               gold.count += Randomizer.getInt(990)+10;
               opened = kista;
               break;
           }
        }

        if(opened != null){
            allChests.remove(opened);
        }
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
        spriteBatch.dispose();
        islandMap.dispose();
        player.dispose();
        for(TreasureChest kista : allChests) {
            kista.dispose();
        }
        gold.dispose();
        hud.dispose();
    }
}
