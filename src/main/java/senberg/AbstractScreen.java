package senberg;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class AbstractScreen implements Screen {
    protected Game game;

    public AbstractScreen(Game game) {
        this.game = game;
    }

    @Override
    public void render (float delta) {
    }

    @Override
    public void resize (int width, int height) {
    }

    @Override
    public void show () {
    }

    @Override
    public void hide () {
    }

    @Override
    public void pause () {
    }

    @Override
    public void resume () {
    }

    @Override
    public void dispose () {
    }

    public ShaderProgram createShader(String vertexShaderFilename, String fragmentShaderFilename) {
        ShaderProgram.pedantic = false;
        String vertexShader = Gdx.files.internal(vertexShaderFilename).readString();
        String fragmentShader = Gdx.files.internal(fragmentShaderFilename).readString();
        ShaderProgram program = new ShaderProgram(vertexShader, fragmentShader);

        if (!program.isCompiled()) {
            throw new IllegalStateException("Error compiling shader: " + program.getLog());
        }

        return program;
    }
}
