package com.anas.jcmusintegrationwithdiscord;

import com.anas.jcmusintegrationwithdiscord.discord.DiscordController;
import com.anas.jcmusintegrationwithdiscord.teracke.CMUSTracker;

public class MainController {
    private final DiscordController discordController;

    public MainController(String... args) {
        discordController = new DiscordController("961407969986232380", "CMUS");
        start();
    }

    private void start() {
        CMUSTracker.getInstance().setDiscordController(discordController);
        new Thread(CMUSTracker.getInstance()).start();
    }
}
