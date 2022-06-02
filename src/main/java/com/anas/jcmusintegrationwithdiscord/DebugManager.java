package com.anas.jcmusintegrationwithdiscord;

public class DebugManager {
    private boolean enabled;

    private static DebugManager instance;

    private DebugManager() {
        enabled = false;
    }

    public static DebugManager getInstance() {
        if (instance == null) {
            instance = new DebugManager();
        }
        return instance;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void debug(String message) {
        if (enabled) {
            System.out.println(message);
        }
    }
}
