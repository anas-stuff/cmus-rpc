package com.anas.jcmusintegrationwithdiscord.teracke;

import com.anas.jcmusintegrationwithdiscord.configs.Configs;
import com.anas.jcmusintegrationwithdiscord.discord.DiscordController;
import com.anas.jcmusintegrationwithdiscord.shell.Shell;
import com.anas.jcmusintegrationwithdiscord.track.Track;

public class CMUSTracker implements Runnable {
    private static CMUSTracker instance;
    private DiscordController discordController;

    private CMUSTracker() {
        discordController = null;
    }

    public static CMUSTracker getInstance() {
        if (instance == null) {
            instance = new CMUSTracker();
        }
        return instance;
    }

    @Override
    public void run() {
        while (true) {
            Track track = new Track(Shell.getInstance().execute("cmus-remote -Q").getResponce());
            if (discordController != null) {
                discordController.updateActivity(track);
            }
            // Sleep
            try {
                Thread.sleep(Configs.getInstance().getInterval());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setDiscordController(DiscordController discordController) {
        this.discordController = discordController;
    }
}
