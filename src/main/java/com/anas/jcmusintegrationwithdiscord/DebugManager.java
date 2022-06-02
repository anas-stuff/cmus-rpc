package com.anas.jcmusintegrationwithdiscord;

import com.anas.jcmusintegrationwithdiscord.configs.ConfigsManger;

public class DebugManager {
    private final boolean enabled;

    private static DebugManager instance;

    private DebugManager() {
        enabled = ConfigsManger.getInstance().isDebug();
    }

    public static DebugManager getInstance() {
        if (instance == null) {
            instance = new DebugManager();
        }
        return instance;
    }

    public void debug(String message) {
        if (enabled) {
            System.out.println(message);
        }
    }
}
