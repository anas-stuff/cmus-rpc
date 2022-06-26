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

    public static CMUSTracker getInstance() {
        if (instance == null) {
            instance = new CMUSTracker();
        }
        return instance;
    }

    @Override
    public void run() {
        Track track = new Track();
        while (true) {
            Responce r = Shell.getInstance().execute("cmus-remote -Q");
            if (!r.isError()) {
                Track newTrack = Track.build(r.toString());
                if (track.getTrackInfo() == null ||
                        !track.getTrackInfo().equals(newTrack.getTrackInfo())) {
                    if (intervalTimeIncrement) {
                        resetIntervalTime();
                    }
                    updateActivity(track, newTrack);
                } else {
                    sleep();
                }
            } else {
                cmusNotRunning();
            }
            pauseLoop();
        }
    }

    private void resetIntervalTime() {
        ConfigsManger.getInstance().getConfigs().setInterval(ConfigsManger.getInstance().getConfigs().getInterval() / 2);
        intervalTimeIncrement = false;
        DebugManager.getInstance().debug("Interval decremented to " +
                ConfigsManger.getInstance().getConfigs().getInterval());
    }

    private void updateActivity(Track track, Track newTrack) {
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

    private void sleep() {
        sleepTime += ConfigsManger.getInstance().getConfigs().getInterval();
        if (sleepTime >= ConfigsManger.getInstance().getConfigs().getSleepTime()) {
            discordController.updateActivity(null);
            DebugManager.getInstance().debug("Sleeping....");
        }
    }

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

    private void pauseLoop() {
        // Sleep
        try {
            Thread.sleep(ConfigsManger.getInstance().getConfigs().getInterval());
        } catch (InterruptedException e) {
            DebugManager.getInstance().debug(e.getMessage());
            Thread.currentThread().interrupt(); // Restore interrupted status
        }
    }

    public void setDiscordController(DiscordController discordController) {
        this.discordController = discordController;
    }
}
