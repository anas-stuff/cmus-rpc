package com.anas.cmusrpc;


import java.util.logging.Level;
import java.util.logging.Logger;

public class DebugManager {
    private boolean enabled;
    private final Logger logger;

    private static DebugManager instance;

    private DebugManager() {
       enabled = false;
       logger = Logger.getLogger("Logger");
    }

    public static DebugManager getInstance() {
        if (instance == null) {
            instance = new DebugManager();
        }
        return instance;
    }

    public void debug(String message, Level... level) {
        if (enabled) {
            if (level.length == 0) {
                logger.info(message);
            } else {
                logger.log(level[0], message);
            }
        }
    }

    public void setDebug(boolean debug) {
        enabled = debug;
    }
}
