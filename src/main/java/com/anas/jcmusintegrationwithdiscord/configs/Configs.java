package com.anas.jcmusintegrationwithdiscord.configs;

public class Configs {
    private int interval;
    private String caverImage, playIcon, pauseIcon;
    private String partOneFormat, partTowFormat;
    private int sleepTime;
    private boolean debug;

    private static Configs instance;

    private Configs() {
        interval = 1000; // 1 second
        sleepTime = 300000; // 5 minutes
        caverImage = "cmus";
        playIcon = "play_icon_1";
        pauseIcon = "pause_icon_2";
        partOneFormat = "%artist% - %title%";
        partTowFormat = "%album%";
        debug = true;
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

    public String getPartOneFormat() {
        return partOneFormat;
    }

    public void setPartOneFormat(String partOneFormat) {
        this.partOneFormat = partOneFormat;
    }

    public String getPartTwoFormat() {
        return partTowFormat;
    }

    public void setPartTowFormat(String partTowFormat) {
        this.partTowFormat = partTowFormat;
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
