package com.anas.jcmusintegrationwithdiscord.shell;

import java.io.IOException;

public class Shell {
    private final ProcessBuilder processBuilder;

    private static Shell instance;

    private Shell() {
        processBuilder = new ProcessBuilder();
    }

    public static Shell getInstance() {
        if (instance == null) {
            instance = new Shell();
        }
        return instance;
    }

    public Responce execute(String command) {
        try {
            processBuilder.command("sh", "-c", command);
            Process process = processBuilder.start();
            process.waitFor();
            return new Responce(process);
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
