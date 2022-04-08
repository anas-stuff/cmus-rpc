package com.anas.jcmusintegrationwithdiscord.teracke;

import com.anas.jcmusintegrationwithdiscord.configs.ConfigsManger;
import com.anas.jcmusintegrationwithdiscord.discord.DiscordController;
import com.anas.jcmusintegrationwithdiscord.shell.Responce;
import com.anas.jcmusintegrationwithdiscord.shell.Shell;
import com.anas.jcmusintegrationwithdiscord.track.Track;

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
                Track newTrack = Track.build(r.getResponce());
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
                CMUSNotRunning();
            }
            pauseLoop();
        }
    }

    private void resetIntervalTime() {
        ConfigsManger.getInstance().getConfigs().setInterval(ConfigsManger.getInstance().getConfigs().getInterval() / 2);
        intervalTimeIncrement = false;
        if (ConfigsManger.getInstance().isDebug())
            System.out.println("Interval decremented to " + ConfigsManger.getInstance().getConfigs().getInterval());
    }

    private void updateActivity(Track track, Track newTrack) {
        track.update(newTrack);
        if (discordController != null) {
            if (sleepTime > 0) {
                sleepTime = 0;
                if (ConfigsManger.getInstance().isDebug())
                    System.out.println("Restart");
            }
            try {
            discordController.updateActivity(track);
            } catch (NullPointerException e) {
                if (ConfigsManger.getInstance().isDebug())
                    e.printStackTrace();
            }
        }
    }

    private void sleep() {
        sleepTime += ConfigsManger.getInstance().getConfigs().getInterval();
        if (sleepTime >= ConfigsManger.getInstance().getConfigs().getSleepTime()) {
            discordController.updateActivity(null);
            if (ConfigsManger.getInstance().isDebug())
                System.out.println("Sleeping...");
        }
    }

    private void CMUSNotRunning() {
        if (ConfigsManger.getInstance().isDebug())
            System.out.println("CMUS not running");
        if (!intervalTimeIncrement) {
            ConfigsManger.getInstance().getConfigs().setInterval(ConfigsManger.getInstance().getConfigs().getInterval() * 2);
            intervalTimeIncrement = true;
            discordController.updateActivity(null);
            if (ConfigsManger.getInstance().isDebug())
                System.out.println("Interval time incremented to " + ConfigsManger.getInstance().getConfigs().getInterval());
        }
    }

    private void pauseLoop() {
        // Sleep
        try {
            Thread.sleep(ConfigsManger.getInstance().getConfigs().getInterval());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setDiscordController(DiscordController discordController) {
        this.discordController = discordController;
    }
}
