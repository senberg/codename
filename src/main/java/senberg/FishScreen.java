package senberg;

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

import java.util.HashSet;
import java.util.Set;

public class FishScreen extends GameScreen implements DecoratedScreen {
    SpriteBatch spriteBatch;
    BitmapFont font;
    OrthographicCamera camera;
    float cameraSpeed = 1000;
    float cameraZoomSpeed = 1;
    float cameraZoomMinimum = 0.25f;
    float cameraZoomMaximum = 4;
    Vector3 cursorCoordinates = new Vector3();
    Set<GameObject> gameObjects = new HashSet<>();
    float time = 0;
    long frames = 0;
    ShaderProgram defaultShader;

    public FishScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        defaultShader = createShader("shaders/default.vertex.glsl", "shaders/default.fragment.glsl");
        spriteBatch = new SpriteBatch(8191, defaultShader);
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.translate(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();

        font = new BitmapFont();
        Fish.init();

        for (int i = 0; i < 1000; i++) {
            float x = (float) Math.random() * 10000;
            float y = (float) Math.random() * 3000;
            gameObjects.add(new Fish(x, y));
        }

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
        time += delta;
        frames++;
        handleInput(delta);
        gameObjects.forEach(o -> o.update(delta));

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();

        cursorCoordinates.x = Gdx.input.getX();
        cursorCoordinates.y = Gdx.input.getY();
        camera.unproject(cursorCoordinates);
        String text = "X = " + (int) cursorCoordinates.x + " Y = " + (int) cursorCoordinates.y + "\n" + frames / time;

        font.draw(spriteBatch, text, cursorCoordinates.x, cursorCoordinates.y - 30);
        gameObjects.forEach(o -> o.draw(spriteBatch));
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
        font.dispose();
        Fish.dispose();
        defaultShader.dispose();
    }
}
