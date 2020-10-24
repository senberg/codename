package senberg;

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HMODULE;
import com.sun.jna.platform.win32.WinDef.LRESULT;
import com.sun.jna.platform.win32.WinUser.HHOOK;
import com.sun.jna.platform.win32.WinUser.LowLevelKeyboardProc;

import static com.sun.jna.platform.win32.WinUser.WH_KEYBOARD_LL;
import static org.lwjgl.system.windows.User32.VK_LWIN;

public class WindowsKeyHook {
    private static final LRESULT UNPROCESSED = new LRESULT(0);
    private static final LRESULT PROCESSED = new LRESULT(1);
    private static HHOOK hHook;

    public static void start() {
        LowLevelKeyboardProc keyboardHook = (nCode, wParam, info) -> info.vkCode != VK_LWIN ? UNPROCESSED : PROCESSED;
        HMODULE hModule = Kernel32.INSTANCE.GetModuleHandle(null);
        hHook = User32.INSTANCE.SetWindowsHookEx(WH_KEYBOARD_LL, keyboardHook, hModule, 0);
    }

    public static void stop() {
        User32.INSTANCE.UnhookWindowsHookEx(hHook);
    }

}