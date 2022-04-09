package com.anas.jcmusintegrationwithdiscord.configs;

public class Configs {
    private int interval;
    private String caverImage,
            playIcon,
            pauseIcon;
    private String partOneFormat,
            partTowFormat;
    private int sleepTime;
    private boolean debug,
                    link;

    protected Configs() {
        initialize();
    }

    private void initialize() {
        interval = 1000; // 1 second
        sleepTime = 300000; // 5 minutes
        caverImage = "cmus";
        playIcon = "play_icon_2";
        pauseIcon = "pause_icon_2";
        partOneFormat = "%artist% - %title%";
        partTowFormat = "%album%";
        debug = false;
        link = false;
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

    public String getPartTowFormat() {
        return partTowFormat;
    }

    public void setPartTowFormat(String partTowFormat) {
        this.partTowFormat = partTowFormat;
    }

    public int getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public boolean isLink() {
        return link;
    }

    public void setLink(boolean link) {
        this.link = link;
    }
}
