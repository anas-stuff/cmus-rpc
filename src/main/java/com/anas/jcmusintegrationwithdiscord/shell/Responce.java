package com.anas.jcmusintegrationwithdiscord.shell;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Responce {
    private StringBuilder responce;
    private boolean isError;

    public Responce(Process process) {
        init(process);
    }

    private void init(Process process) {
        isError = process.exitValue() != 0;
        responce = new StringBuilder();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                responce.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getResponce() {
        return responce.toString();
    }

    public boolean isError() {
        return isError;
    }
}
