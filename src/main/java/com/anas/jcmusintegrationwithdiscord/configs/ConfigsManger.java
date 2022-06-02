package com.anas.jcmusintegrationwithdiscord.configs;

import com.anas.jcmusintegrationwithdiscord.DebugManager;
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
        loadConfigs(true);
    }

    private void loadConfigs(boolean createNew) {
        File file = new File(configsPath);
        DebugManager.getInstance().debug("Loading configs from " + configsPath);
        configs = new Configs();
        if (!file.exists()) {
            DebugManager.getInstance().debug("Configs file not found");
            if (createNew) {
                DebugManager.getInstance().debug("Creating new configs file....");
                saveConfigs();
            }
        } else {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                JsonNode parentNode = objectMapper.readTree(file);
                DebugManager.getInstance().debug(parentNode.asText());
                setAttributes(parentNode);
            } catch (IOException e) {
                DebugManager.getInstance().debug("Error while loading configs file");
            }
        }
    }

    private void setAttributes(JsonNode parentNode) {
        configs.setInterval(parentNode.get("interval").asInt());
        configs.setCaverImage(parentNode.get("caverImage").asText());
        configs.setPlayIcon(parentNode.get("playIcon").asText());
        configs.setPauseIcon(parentNode.get("pauseIcon").asText());
        configs.setPartOneFormat(parentNode.get("partOneFormat").asText());
        configs.setPartTowFormat(parentNode.get("partTowFormat").asText());
        configs.setSleepTime(parentNode.get("sleepTime").asInt());
        configs.setDebug(parentNode.get("debug").asBoolean());
    }

    private void saveConfigs() {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(configsPath);
        if (!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, configs);
            DebugManager.getInstance().debug("Created new configs file in " + file.getAbsolutePath());
        } catch (IOException e) {
            DebugManager.getInstance().debug("Error while creating new configs file, " + e.getMessage());
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
        return configs.isDebug();
    }

    public void setConfigsPath(String configsPath) {
        this.configsPath = configsPath;
    }

    public void reLoadConfigs() {
        loadConfigs(false);
    }
}
