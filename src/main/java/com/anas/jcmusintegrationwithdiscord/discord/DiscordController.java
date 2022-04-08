package com.anas.jcmusintegrationwithdiscord.discord;

import com.anas.jcmusintegrationwithdiscord.PartFormatter;
import com.anas.jcmusintegrationwithdiscord.configs.Configs;
import com.anas.jcmusintegrationwithdiscord.track.Track;
import com.anas.jcmusintegrationwithdiscord.track.TrackInfo;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;

public class DiscordController {
    private String ID;
    private String icon;
    private long startTime;

    public DiscordController(String ID, String icon) {
        this.ID = ID;
        this.icon = icon;
        setup();
        startTime = System.currentTimeMillis();
    }

    private void setup() {
        // Discord shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (Configs.getInstance().isDebug())
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
            return;
        }
        DiscordRichPresence.Builder builder = new DiscordRichPresence.Builder(
                PartFormatter.format(Configs.getInstance().getPartOneFormat(), track));

        builder.setDetails(PartFormatter.format(Configs.getInstance().getPartTwoFormat(), track));
        builder.setBigImage(Configs.getInstance().getCaverImage(), icon);
        builder.setStartTimestamps(startTime);
        if (track.getTrackInfo().getStatus() == TrackInfo.Status.PLAYING) {
            builder.setSmallImage(Configs.getInstance().getPlayIcon(), "Playing");
        } else if (track.getTrackInfo().getStatus() == TrackInfo.Status.PAUSED) {
            builder.setSmallImage(Configs.getInstance().getPauseIcon(), "Paused");
        }

        DiscordRPC.discordUpdatePresence(builder.build());
    }
}
