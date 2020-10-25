package senberg;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWNativeWin32;
import org.lwjgl.system.windows.User32;

public class Codename extends Game {
    private static final Color BACKGROUND_COLOR = new Color(0, 0, 0.2f, 1);

    public static void main(String[] arg) {
        Graphics.DisplayMode displayMode = Lwjgl3ApplicationConfiguration.getDisplayMode();
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Codename - the best game ever.");
        config.setWindowIcon("icon.png");
        config.setWindowPosition(0, 0);
        config.setWindowedMode(displayMode.width, displayMode.height);
        config.setInitialBackgroundColor(BACKGROUND_COLOR);
        config.setIdleFPS(displayMode.refreshRate);
        config.setResizable(false);
        config.setDecorated(false);
        config.useOpenGL3(true, 3, 2);
        config.enableGLDebugOutput(true, System.out);
        config.setInitialVisible(false);
        new Lwjgl3Application(new Codename(), config);
    }

    @Override
    public void create() {
        WindowsKeyHook.start();
        preventFlickering();
        setScreen(new MainMenu(this));
    }

    private void preventFlickering() {
        long hGLFWwindow = GLFW.glfwGetCurrentContext();
        long hWnd = GLFWNativeWin32.glfwGetWin32Window(hGLFWwindow);
        long style = User32.GetWindowLongPtr(hWnd, User32.GWL_STYLE);
        style &= ~User32.WS_POPUP;
        User32.SetWindowLongPtr(hWnd, User32.GWL_STYLE, style);
        GLFW.glfwShowWindow(hGLFWwindow);
    }

    @Override
    public void dispose() {
        WindowsKeyHook.stop();
    }
}
