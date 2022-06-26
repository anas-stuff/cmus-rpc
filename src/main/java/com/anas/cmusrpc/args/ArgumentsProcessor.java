package com.anas.cmusrpc.args;

import com.anas.cmusrpc.DebugManager;
import com.anas.cmusrpc.Main;
import com.anas.cmusrpc.configs.ConfigsManger;
import org.apache.commons.cli.*;

public class ArgumentsProcessor {
    private Options options;
    private CommandLineParser parser;
    private final String[] args;

    public ArgumentsProcessor(String[] args) {
        initialize();
        setupOptions();
        this.args = args;
    }

    public void process() {
        try {
            CommandLine commandLine = parser.parse(options, args);
            check(commandLine);
        } catch (ParseException e) {
            System.out.println("Error: " + e.getMessage());
            showHelp();
        }
    }

    private void check(CommandLine commandLine) {
        if (commandLine.hasOption("h")) {
            showHelp();
        } else if (commandLine.hasOption("v")) {
            System.out.println("Version: " + Main.VERSION);
            System.exit(0);
        } else {
            checkMultiOptions(commandLine);
        }
    }

    private void checkMultiOptions(CommandLine commandLine) {
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

    private void showHelp() {
        new HelpFormatter()
                .printHelp("java -jar jcmusintegrationwithdiscord.jar [OPTION] [VALUE] [OPTION] [VALUE] ...",
                        options);
        System.exit(0);
    }

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

    private void initialize() {
        options = new Options();
        parser = new DefaultParser();
    }
}
