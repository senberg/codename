package senberg;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import senberg.fish.FishScreen;
import senberg.island.IslandScreen;

public class MainMenu extends GameScreen implements DecoratedScreen {
    SpriteBatch backgroundBatch;
    SpriteBatch foregroundBatch;
    BitmapFont font;
    OrthographicCamera camera;
    String text = "Welcome to the Fish Demo!\n\n1. Enter fish demo.\n2. Enter the island.";
    ShaderProgram defaultShader;
    ShaderProgram seascapeShader;
    Texture fillerTexture;
    float time = 0;
    float[] v_resolution;

    public MainMenu(Game game) {
        super(game);
    }

    @Override
    public void show() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.translate(Gdx.graphics.getWidth() / 2.0f, Gdx.graphics.getHeight() / 2.0f);
        camera.update();
        defaultShader = createShader("shaders/default.vertex.glsl", "shaders/default.fragment.glsl");
        seascapeShader = createShader("shaders/seascape.vertex.glsl", "shaders/seascape.fragment.glsl");
        backgroundBatch = new SpriteBatch(1, seascapeShader);
        fillerTexture = new Texture("filler.png");
        v_resolution = new float[]{Gdx.graphics.getWidth(), Gdx.graphics.getHeight()};
        foregroundBatch = new SpriteBatch(8191, defaultShader);

        font = new BitmapFont();

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                switch (keycode) {
                    case Input.Keys.ESCAPE:
                        Gdx.app.exit();
                        return true;
                    case Input.Keys.NUM_1:
                        game.setScreen(new FishScreen(game));
                        return true;
                    case Input.Keys.NUM_2:
                        game.setScreen(new IslandScreen(game));
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

        backgroundBatch.begin();
        seascapeShader.setUniform2fv("v_resolution", v_resolution, 0, v_resolution.length);
        seascapeShader.setUniformf("f_time", time);
        backgroundBatch.draw(fillerTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        backgroundBatch.end();

        foregroundBatch.begin();
        font.draw(foregroundBatch, text, Gdx.graphics.getWidth() / 2.0f, Gdx.graphics.getHeight() * 0.75f);
        foregroundBatch.end();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
        foregroundBatch.dispose();
        backgroundBatch.dispose();
        font.dispose();
        fillerTexture.dispose();
        defaultShader.dispose();
        seascapeShader.dispose();
    }
}
