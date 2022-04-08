package com.anas.jcmusintegrationwithdiscord.discord;

import com.anas.jcmusintegrationwithdiscord.PartFormatterHelper;
import com.anas.jcmusintegrationwithdiscord.configs.ConfigsManger;
import com.anas.jcmusintegrationwithdiscord.track.Track;
import com.anas.jcmusintegrationwithdiscord.track.TrackInfo;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;

public class DiscordController {
    private final String ID;
    private long startTime;

    public DiscordController(String ID) {
        this.ID = ID;
        setup();
        startTime = -1;
    }

    private void setup() {
        // Discord shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (ConfigsManger.getInstance().isDebug())
                System.out.println("Shutting down Discord controller...");
            DiscordRPC.discordShutdown();
        }));

        DiscordRPC.discordInitialize(ID, null, true);
        DiscordRPC.discordRegister(ID, "");
    }

    public void updateActivity(Track track) {
        if (track == null ||
                track.getTrackInfo().getStatus() == TrackInfo.Status.STOPPED) {
            // If track is paused for while of time or track is stopped, clear the activity
            DiscordRPC.discordClearPresence();
            startTime = -1;
            return;
        }
        if (startTime == -1) { // If start time is not set, set it
            updateStartTime();
        }

        DiscordRPC.discordUpdatePresence(buildRichPresence(track));
    }

    private DiscordRichPresence buildRichPresence(Track track) {
        DiscordRichPresence.Builder builder = new DiscordRichPresence.Builder(
                PartFormatterHelper.format(ConfigsManger.getInstance().getConfigs().getPartTowFormat(), track));

        builder.setDetails(PartFormatterHelper.format(ConfigsManger.getInstance().getConfigs().getPartOneFormat(), track));
        builder.setBigImage(ConfigsManger.getInstance().getConfigs().getCaverImage(), "CMUS Player");
        builder.setStartTimestamps(startTime);
        if (track.getTrackInfo().getStatus() == TrackInfo.Status.PLAYING) {
            builder.setSmallImage(ConfigsManger.getInstance().getConfigs().getPlayIcon(), "Playing");
        } else if (track.getTrackInfo().getStatus() == TrackInfo.Status.PAUSED) {
            builder.setSmallImage(ConfigsManger.getInstance().getConfigs().getPauseIcon(), "Paused");
        }
        return builder.build();
    }

    private void updateStartTime() {
        startTime = System.currentTimeMillis();
        if (ConfigsManger.getInstance().isDebug())
            System.out.println("Start time set to " + startTime);
    }
}
