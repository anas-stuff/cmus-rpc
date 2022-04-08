package com.anas.jcmusintegrationwithdiscord.configs;

import com.anas.jcmusintegrationwithdiscord.Displayable;

import java.util.HashMap;
import java.util.Map;

public class Configs {
    private int interval;
    private String caverImage, playIcon, pauseIcon;
    private Map<Displayable, Boolean> display;
    private int sleepTime;
    private boolean debug;

    private static Configs instance;

    private Configs() {
        interval = 1000; // 1 second
        sleepTime = 300000; // 5 minutes
        display = new HashMap<>();
        caverImage = "cmus";
        playIcon = "play_icon_1";
        pauseIcon = "pause_icon_2";
        debug = true;

        initDisplay();
    }

    private void initDisplay() {

    }

    public static Configs getInstance() {
        if (instance == null) {
            instance = new Configs();
        }
        return instance;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public Map<Displayable, Boolean> getDisplay() {
        return display;
    }

    public void setDisplay(Map<Displayable, Boolean> display) {
        this.display = display;
    }

    public String getCaverImage() {
        return caverImage;
    }

    public void setCaverImage(String caverImage) {
        this.caverImage = caverImage;
    }

    public String getPlayIcon() {
        return playIcon;
    }

    public void setPlayIcon(String playIcon) {
        this.playIcon = playIcon;
    }

    public String getPauseIcon() {
        return pauseIcon;
    }

    public void setPauseIcon(String pauseIcon) {
        this.pauseIcon = pauseIcon;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public int getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
    }
}
