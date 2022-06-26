package com.anas.cmusrpc.shell;


import com.anas.cmusrpc.DebugManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class Responce {
    private final StringBuilder stringBuilder;
    private final boolean isError;

    public Responce(Process process) {
        stringBuilder = new StringBuilder();
        isError = process.exitValue() != 0;
        setupString(process);
    }

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

    public boolean isError() {
        return isError;
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }
}
