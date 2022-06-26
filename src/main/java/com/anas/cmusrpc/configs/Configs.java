package com.anas.cmusrpc.configs;

import com.anas.cmusrpc.DebugManager;

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

    /**
     * It initializes the variables
     */
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

    /**
     * This function returns the interval.
     *
     * @return The interval of the timer.
     */
    public int getInterval() {
        return interval;
    }

    /**
     * This function sets the interval to the value of the parameter.
     *
     * @param interval The interval in milliseconds between each call to the onTick() method.
     */
    public void setInterval(int interval) {
        this.interval = interval;
    }

    /**
     * This function returns the image of the caver
     *
     * @return The caverImage is being returned.
     */
    public String getCaverImage() {
        return caverImage;
    }

    /**
     * This function sets the image of the caver
     *
     * @param caverImage The image of the caver.
     */
    public void setCaverImage(String caverImage) {
        this.caverImage = caverImage;
    }

    /**
     * This function returns the play icon name
     *
     * @return The play icon name.
     */
    public String getPlayIcon() {
        return playIcon;
    }

    /**
     * This function sets the play icon name.
     *
     * @param playIcon The icon name to use for the play icon.
     */
    public void setPlayIcon(String playIcon) {
        this.playIcon = playIcon;
    }

    /**
     * This function returns the pause icon name.
     *
     * @return The pause icon name.
     */
    public String getPauseIcon() {
        return pauseIcon;
    }

    /**
     * This function sets the pause icon name.
     *
     * @param pauseIcon The icon name to use for the pause icon.
     */
    public void setPauseIcon(String pauseIcon) {
        this.pauseIcon = pauseIcon;
    }

    /**
     * This function returns the format of the first part of rich presence.
     *
     * @return The format of the first part of rich presence.
     */
    public String getPartOneFormat() {
        return partOneFormat;
    }

    /**
     * This function sets the format of the first part of the rich presence.
     *
     * @param partOneFormat The format of the first part of the rich presence.
     */
    public void setPartOneFormat(String partOneFormat) {
        this.partOneFormat = partOneFormat;
    }

    /**
     * This function returns the format of the second part of rich presence.
     *
     * @return The format of the second part of rich presence.
     */
    public String getPartTowFormat() {
        return partTowFormat;
    }

    /**
     * This function sets the format of the second part of the rich presence.
     *
     * @param partTowFormat The format of the second part of the rich presence.
     */
    public void setPartTowFormat(String partTowFormat) {
        this.partTowFormat = partTowFormat;
    }

    /**
     * This function returns the sleep time.
     *
     * @return The sleep time.
     */
    public int getSleepTime() {
        return sleepTime;
    }

    /**
     * This function sets the sleep time to the value of the parameter.
     *
     * @param sleepTime The time in milliseconds to sleep between each check of the CMUS status.
     */
    public void setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
    }

    /**
     * This function returns true if the debug mode is enabled.
     *
     * @return The value of the debug mode.
     */
    public boolean isDebug() {
        return debug;
    }

    /**
     * Active and deactivate the debug mode.
     *
     * @param debug If true, the debug mode is enabled.
     */
    public void setDebug(boolean debug) {
        this.debug = debug;
        DebugManager.getInstance().setDebug(debug);
    }

    /**
     * This function returns true if the link mode is enabled.
     *
     * @return The value of the link mode.
     */
    public boolean isLink() {
        return link;
    }

    /**
     * This function sets the link mode to the value of the parameter.
     *
     * @param link If true, the link will be enabled.
     */
    public void setLink(boolean link) {
        this.link = link;
    }
}
