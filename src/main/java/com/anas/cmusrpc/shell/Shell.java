package com.anas.cmusrpc.shell;

import com.anas.cmusrpc.DebugManager;

import java.io.IOException;
import java.util.logging.Level;

/**
 * It executes a command and returns a Responce object
 */
public class Shell {
    private final ProcessBuilder processBuilder;

    private static Shell instance;

    private Shell() {
        processBuilder = new ProcessBuilder();
    }

    /**
     * If the instance is null, create a new instance. Otherwise, return the existing instance
     *
     * @return The instance of the Shell class.
     */
    public static Shell getInstance() {
        if (instance == null) {
            instance = new Shell();
        }
        return instance;
    }

    /**
     * It executes a command and returns a Responce object
     *
     * @param command The command to be executed.
     * @return A Responce object, returns null if the command interrupted, or the sh binary is not found.
     */
    public Responce execute(String command) {
        try {
            processBuilder.command("/bin/sh", "-c", command);
            Process process = processBuilder.start();
            process.waitFor();
            return new Responce(process);
        } catch (InterruptedException | IOException e) {
            DebugManager.getInstance().debug(e.getMessage(), Level.SEVERE);
            Thread.currentThread().interrupt(); // reset interrupted flag
        }
        return null;
    }
}
