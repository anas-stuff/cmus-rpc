package com.anas.cmusrpc;


import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * It's a singleton class that provides a debug method that logs messages to the console
 */
public class DebugManager {
    private boolean enabled;
    private final Logger logger;

    private static DebugManager instance;

    private DebugManager() {
       enabled = false;
       logger = Logger.getLogger("Logger");
    }

    /**
     * If the instance is null, create a new instance and return it. Otherwise, return the existing instance
     *
     * @return The instance of the DebugManager class.
     */
    public static DebugManager getInstance() {
        if (instance == null) {
            instance = new DebugManager();
        }
        return instance;
    }

    /**
     * If the logger is enabled, log the message at the specified level, if level is not specified, it defaults to INFO
     *
     * @param message The message to be logged.
     */
    public void debug(final String message,
                      final Level... level) {
        if (enabled) {
            if (level.length == 0) {
                logger.info(message);
            } else {
                logger.log(level[0], message);
            }
        }
    }

    /**
     * Enable and disable the debugger
     *
     * @param debug true/false - enables/disables the debug mode
     */
    public void setDebug(final boolean debug) {
        enabled = debug;
    }
}
