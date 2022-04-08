package com.anas.jcmusintegrationwithdiscord;

import com.anas.jcmusintegrationwithdiscord.args.ArgumentsProcessor;
import com.anas.jcmusintegrationwithdiscord.discord.DiscordController;
import com.anas.jcmusintegrationwithdiscord.teracke.CMUSTracker;

public class MainController {
    public static final String VERSION = "1.0.0";
    private final DiscordController discordController;

    public MainController(String... args) {
        new ArgumentsProcessor(args).process(); // process arguments
        discordController = new DiscordController("961407969986232380");
        start();
    }

    private void start() {
        CMUSTracker.getInstance().setDiscordController(discordController);
        new Thread(CMUSTracker.getInstance()).start();
    }
}
