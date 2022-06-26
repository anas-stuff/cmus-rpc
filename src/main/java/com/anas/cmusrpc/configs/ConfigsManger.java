package com.anas.cmusrpc.configs;

import com.anas.cmusrpc.DebugManager;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class ConfigsManger {
    private Configs configs;
    private String configsPath;
    private static ConfigsManger instance;

    private ConfigsManger() {
        configsPath = System.getProperty("user.home") + "/.config/cmus-rpc/configs.json";
        loadConfigs(true);
    }

    /**
     * It loads the configs file from the configsPath and sets the attributes of the configs object
     *
     * @param createNew If the configs file doesn't exist, create a new one.
     */
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

    /**
     * It takes a JsonNode object and sets the values of the Configs object
     *
     * @param parentNode The root node of the json file.
     */
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

    /**
     * It creates a new file in the configsPath directory, and writes the configs object to it
     */
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

    /**
     * If the instance is null, create a new instance and return it. Otherwise, return the existing instance
     *
     * @return The instance of the ConfigsManger class.
     */
    public static ConfigsManger getInstance() {
        if (instance == null) {
            instance = new ConfigsManger();
        }
        return instance;
    }

    /**
     * This function returns the configs object.
     *
     * @return The configs object.
     */
    public Configs getConfigs() {
        return configs;
    }

    /**
     * > Sets the debug flag
     *
     * @param debug If true, the debug mode is enabled.
     */
    public void setDebug(boolean debug) {
        configs.setDebug(debug);
    }

    /**
     * > Sets the path to the configs directory
     *
     * @param configsPath The path to the directory where the config files are stored.
     */
    public void setConfigsPath(String configsPath) {
        this.configsPath = configsPath;
    }

    /**
     * It reloads the configs
     */
    public void reLoadConfigs() {
        loadConfigs(false);
    }
}
