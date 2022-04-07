package com.anas.jcmusintegrationwithdiscord.discord;

import com.anas.jcmusintegrationwithdiscord.track.Tag;
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
            System.out.println("Shutting down Discord controller...");
            DiscordRPC.discordShutdown();
        }));

        DiscordRPC.discordInitialize(ID, null, true);
        DiscordRPC.discordRegister(ID, "");
    }

    public void updateActivity(Track track) {
        if (track.getTrackInfo().getStatus() == TrackInfo.Status.PLAYING) {
            DiscordRichPresence.Builder builder = new DiscordRichPresence.Builder("Now playing");
            builder.setDetails(track.getTag(Tag.ARTIST) + " - " + track.getTag(Tag.TITLE));
            builder.setBigImage("cmus", icon);
            builder.setStartTimestamps(startTime);

            DiscordRPC.discordUpdatePresence(builder.build());
        } else { // If track is paused or stopped then clear the presence
            DiscordRPC.discordClearPresence();
        }
    }
}
