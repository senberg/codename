package senberg;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class GameScreen {
    static{
        ShaderProgram.pedantic = false;
    }

    protected Game game;

    public GameScreen(Game game) {
        this.game = game;
    }

    public ShaderProgram createShader(String vertexShaderFilename, String fragmentShaderFilename) {
        String vertexShader = Gdx.files.internal(vertexShaderFilename).readString();
        String fragmentShader = Gdx.files.internal(fragmentShaderFilename).readString();
        ShaderProgram program = new ShaderProgram(vertexShader, fragmentShader);

        if (!program.isCompiled()) {
            throw new IllegalStateException("Error compiling shader: " + program.getLog());
        }

        return program;
    }
}
