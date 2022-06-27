package com.anas.cmusrpc;

import com.anas.cmusrpc.args.ArgumentsProcessor;
import com.anas.cmusrpc.discord.DiscordController;
import com.anas.cmusrpc.teracke.CMUSTracker;

/**
 * It processes the command line arguments, creates a new instance of the `CMUSTracker` class, and starts it in a new
 * thread
 */
public class Main {
    public static final String VERSION = "2.0.0";
    private static DiscordController discordController;

    /**
     * The entry point of the application.
     * @param args the command line arguments
     */
    public static void main(final String... args) {
        new ArgumentsProcessor().process(args); // process arguments
        DebugManager.getInstance().debug("cmus-rpc v" + VERSION);
        discordController = new DiscordController("961407969986232380");
        start();
    }

    /**
     * Start the CMUS tracker in a new thread.
     */
    private static void start() {
        CMUSTracker.getInstance().setDiscordController(discordController);
        DebugManager.getInstance().debug("Starting CMUS tracker...");
        new Thread(CMUSTracker.getInstance()).start();
    }
}
