package com.anas.jcmusintegrationwithdiscord.teracke;

import com.anas.jcmusintegrationwithdiscord.configs.Configs;
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
        Configs.getInstance().setInterval(Configs.getInstance().getInterval() / 2);
        intervalTimeIncrement = false;
        if (Configs.getInstance().isDebug())
            System.out.println("Interval decremented to " + Configs.getInstance().getInterval());
    }

    private void updateActivity(Track track, Track newTrack) {
        track.update(newTrack);
        if (discordController != null) {
            if (sleepTime > 0) {
                sleepTime = 0;
                if (Configs.getInstance().isDebug())
                    System.out.println("Restart");
            }
            try {
            discordController.updateActivity(track);
            } catch (NullPointerException e) {
                if (Configs.getInstance().isDebug())
                    e.printStackTrace();
            }
        }
    }

    private void sleep() {
        sleepTime += Configs.getInstance().getInterval();
        if (sleepTime >= Configs.getInstance().getSleepTime()) {
            discordController.updateActivity(null);
            if (Configs.getInstance().isDebug())
                System.out.println("Sleeping...");
        }
    }

    private void CMUSNotRunning() {
        if (Configs.getInstance().isDebug())
            System.out.println("CMUS not running");
        if (!intervalTimeIncrement) {
            Configs.getInstance().setInterval(Configs.getInstance().getInterval() * 2);
            intervalTimeIncrement = true;
            discordController.updateActivity(null);
            if (Configs.getInstance().isDebug())
                System.out.println("Interval time incremented to " + Configs.getInstance().getInterval());
        }
    }

    private void pauseLoop() {
        // Sleep
        try {
            Thread.sleep(Configs.getInstance().getInterval());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setDiscordController(DiscordController discordController) {
        this.discordController = discordController;
    }
}
