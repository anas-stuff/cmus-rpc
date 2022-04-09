package com.anas.jcmusintegrationwithdiscord.configs;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class ConfigsManger {
    private Configs configs;
    private String configsPath;
    private static ConfigsManger instance;
    private ConfigsManger() {
        configsPath = System.getProperty("user.home") + "/.config/JCMUSIntegrationWithDiscord/configs.json";
        loadConfigs();
    }

    private void loadConfigs() {
        File file = new File(configsPath);
            configs = new Configs();
        if (!file.exists()) {
            if (configs.isDebug())
                System.out.println("Configs file not found, creating new one");
            saveConfigs();
        } else {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                JsonNode pnode = objectMapper.readTree(file);
                if (this.isDebug())
                    System.out.println(pnode);

                setAttributes(pnode);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setAttributes(JsonNode pnode) {
        configs.setInterval(pnode.get("interval").asInt());
        configs.setCaverImage(pnode.get("caverImage").asText());
        configs.setPlayIcon(pnode.get("playIcon").asText());
        configs.setPauseIcon(pnode.get("pauseIcon").asText());
        configs.setPartOneFormat(pnode.get("partOneFormat").asText());
        configs.setPartTowFormat(pnode.get("partTowFormat").asText());
        configs.setSleepTime(pnode.get("sleepTime").asInt());
        configs.setDebug(pnode.get("debug").asBoolean());
    }

    private void saveConfigs() {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(configsPath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, configs);
            if (this.isDebug())
                System.out.println("Created new configs file in " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ConfigsManger getInstance() {
        if (instance == null) {
            instance = new ConfigsManger();
        }
        return instance;
    }

    public Configs getConfigs() {
        return configs;
    }

    public void setDebug(boolean debug) {
        configs.setDebug(debug);
    }

    public boolean isDebug() {
        return  configs.isDebug();
    }
}
