package com.anas.cmusrpc.args;

import com.anas.cmusrpc.DebugManager;
import com.anas.cmusrpc.Main;
import com.anas.cmusrpc.configs.ConfigsManger;
import org.apache.commons.cli.*;

/**
 * It processes the arguments and sets the configuration
 */
public class ArgumentsProcessor {
    private final Options options;

    public ArgumentsProcessor() {
        options = new Options();
        setupOptions();
    }

    /**
     * It parses the command line arguments, checks if they are valid, and if not, shows the help message and exits.
     *
     * @param args The arguments passed to the program.
     */
    public void process(final String[] args) {
        try {
            CommandLine commandLine = new DefaultParser().parse(options, args);
            check(commandLine);
        } catch (ParseException e) {
            System.out.println("Error: " + e.getMessage());
            showHelp();
        }
    }

    /**
     * If the user has specified the -h option, show the help. If the user has specified the -v option, show the version.
     * Otherwise, check the other options
     *
     * @param commandLine The command line object that contains the options and arguments.
     */
    private void check(final CommandLine commandLine) {
        if (commandLine.hasOption("h")) {
            showHelp();
        } else if (commandLine.hasOption("v")) {
            System.out.println("Version: " + Main.VERSION);
            System.exit(0);
        } else {
            checkMultiOptions(commandLine);
        }
    }

    /**
     * A function that checks the command line options.
     *
     * @param commandLine The object that contains the parameters passed in by the user.
     */
    private void checkMultiOptions(final CommandLine commandLine) {
        if (commandLine.hasOption("d")) {
            DebugManager.getInstance().setDebug(true);
            ConfigsManger.getInstance().setDebug(true);
        }
        if (commandLine.hasOption("l")) {
            ConfigsManger.getInstance().getConfigs().setLink(true);
        }
        if (commandLine.hasOption("c")) {
            ConfigsManger.getInstance().setConfigsPath(commandLine.getOptionValue("c"));
            ConfigsManger.getInstance().reLoadConfigs();
        }
        if (commandLine.hasOption("i")) {
            ConfigsManger.getInstance().getConfigs().setInterval(Integer.parseInt(commandLine.getOptionValue("i")) * 1000);
        }
        if (commandLine.hasOption("s")) {
            ConfigsManger.getInstance().getConfigs()
                    .setSleepTime(Integer.parseInt(commandLine.getOptionValue("s")) * 1000);
        }
        if (commandLine.hasOption("partOneFormat")) {
            ConfigsManger.getInstance().getConfigs().setPartOneFormat(commandLine.getOptionValue("partOneFormat"));
        }
        if (commandLine.hasOption("partTowFormat")) {
            ConfigsManger.getInstance().getConfigs().setPartTowFormat(commandLine.getOptionValue("partTowFormat"));
        }
    }

    /**
     * It prints out the help message and exits the program
     */
    private void showHelp() {
        new HelpFormatter()
                .printHelp("cmus-rpc [OPTION] [VALUE] [OPTION] [VALUE] ...",
                        options);
        System.exit(0);
    }

    /**
     * It adds options to the options object
     */
    private void setupOptions() {
        options.addOption("h", "help", false, "Show this help");
        options.addOption("v", "version", false, "Show version");
        options.addOption("d", "debug", false, "Activate debug mode");
        options.addOption("l", "link", false, "Linking with cmus (close the program if cmus is not running)");
        options.addOption("c", "config", true, "Custom path to config file");
        options.addOption("i", "interval", true, "Set interval between checks (in seconds)");
        options.addOption("s", "sleep", true, "Set sleep when there is no activity (in seconds)");
        options.addOption("p1f", "partOneFormat", true, "Set the format for the first part");
        options.addOption("p2f", "partTowFormat", true, "Set the format for the second part");
    }
}
