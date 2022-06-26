package com.anas.cmusrpc.teracke;

import com.anas.cmusrpc.DebugManager;
import com.anas.cmusrpc.configs.ConfigsManger;
import com.anas.cmusrpc.discord.DiscordController;
import com.anas.cmusrpc.shell.Responce;
import com.anas.cmusrpc.shell.Shell;
import com.anas.cmusrpc.track.Track;

public class CMUSTracker implements Runnable {
    private static CMUSTracker instance;
    private DiscordController discordController;
    private int sleepTime;
    private boolean intervalTimeIncrement;

    private CMUSTracker() {
        discordController = null;
        sleepTime = 0;
        intervalTimeIncrement = false;
    }

    /**
     * If the instance is null, create a new instance of CMUSTracker. Otherwise, return the existing instance
     *
     * @return The instance of the CMUSTracker class.
     */
    public static CMUSTracker getInstance() {
        if (instance == null) {
            instance = new CMUSTracker();
        }
        return instance;
    }

    @Override
    public void run() {
        final var track = new Track();
        while (true) {
            final var r = Shell.getInstance().execute("cmus-remote -Q");
            // Checking if the command executed successfully.
            if (!r.isError()) {
                // Creating a new track object from the output of the cmus-remote command.
                final var newTrack = Track.build(r.toString());
                if (track.getTrackInfo() == null ||
                        !track.getTrackInfo().equals(newTrack.getTrackInfo())) {
                    if (intervalTimeIncrement) {
                        resetIntervalTime();
                    }
                    updateActivity(track, newTrack);
                } else {
                    // If the track is the same, sleep for a while.
                    sleep();
                }
            } else {
                // If cmus is not running, the program will stop if the linking is true. Otherwise, it will increment the
                // interval time.
                cmusNotRunning();
            }
            // Sleeping the thread for a while.
            pauseLoop();
        }
    }

    /**
     * If the interval time is greater than the minimum interval time, then the interval time is halved
     */
    private void resetIntervalTime() {
        ConfigsManger.getInstance().getConfigs().setInterval(ConfigsManger.getInstance().getConfigs().getInterval() / 2);
        intervalTimeIncrement = false;
        DebugManager.getInstance().debug("Interval decremented to " +
                ConfigsManger.getInstance().getConfigs().getInterval());
    }

    /**
     * It updates the activity on Discord
     *
     * @param track The current track that is playing
     * @param newTrack The track that is currently playing
     */
    private void updateActivity(final Track track, final Track newTrack) {
        track.update(newTrack);
        if (discordController != null) {
            if (sleepTime > 0) {
                sleepTime = 0;
                DebugManager.getInstance().debug("Unpause");
            }
            try {
                discordController.updateActivity(track);
            } catch (NullPointerException e) {
                DebugManager.getInstance().debug(e.getMessage());
            }
        }
    }

    /**
     * If the sleepTime is greater than or equal to the sleepTime in the configs, then remove the activity from Discord
     */
    private void sleep() {
        sleepTime += ConfigsManger.getInstance().getConfigs().getInterval();
        if (sleepTime >= ConfigsManger.getInstance().getConfigs().getSleepTime()) {
            discordController.updateActivity(null);
            DebugManager.getInstance().debug("Sleeping....");
        }
    }

    /**
     * If CMUS is not running, stop the program if linking is enabled, otherwise double the interval time and update the
     * Discord activity
     */
    private void cmusNotRunning() {
        DebugManager.getInstance().debug("CMUS not running");
        // Stop the program if the linking true
        if (ConfigsManger.getInstance().getConfigs().isLink()) {
            System.exit(0);
        }
        if (!intervalTimeIncrement) {
            ConfigsManger.getInstance().getConfigs().setInterval(ConfigsManger.getInstance().getConfigs().getInterval() * 2);
            intervalTimeIncrement = true;
            discordController.updateActivity(null);
            DebugManager.getInstance().debug("Interval time incremented to " +
                    ConfigsManger.getInstance().getConfigs().getInterval());
        }
    }

    /**
     * Sleep for a certain amount of time
     */
    private void pauseLoop() {
        // Sleep
        try {
            Thread.sleep(ConfigsManger.getInstance().getConfigs().getInterval());
        } catch (InterruptedException e) {
            DebugManager.getInstance().debug(e.getMessage());
            Thread.currentThread().interrupt(); // Restore interrupted status
        }
    }

    /**
     * "This function sets the discordController variable to the discordController variable passed in as a parameter."
     *
     * Now that we have a way to set the discordController variable, we need to set it
     *
     * @param discordController The DiscordController object that is used to send messages to the Discord channel.
     */
    public void setDiscordController(final DiscordController discordController) {
        this.discordController = discordController;
    }
}
