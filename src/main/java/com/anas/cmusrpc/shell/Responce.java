package com.anas.cmusrpc.shell;


import com.anas.cmusrpc.DebugManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;

/**
 * It takes a Process object and returns a string with the output of the process
 */
public class Responce {
    private final StringBuilder stringBuilder;
    private final boolean isError;

    /**
     * Create respoce object
     * @param process the shell process
     */
    public Responce(Process process) {
        stringBuilder = new StringBuilder();
        isError = process.exitValue() != 0;
        setupString(process);
    }

    /**
     * It reads the output of the process and appends it to a string builder
     *
     * @param process The process that you want to get the output from.
     */
    private void setupString(Process process) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            DebugManager.getInstance().debug(e.getMessage(), Level.SEVERE);
        }
    }

    /**
     * Returns true if the response is an error.
     *
     * @return The value of the isError variable.
     */
    public boolean isError() {
        return isError;
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }
}
