package com.anas.jcmusintegrationwithdiscord.args;

import com.anas.jcmusintegrationwithdiscord.MainController;
import com.anas.jcmusintegrationwithdiscord.configs.ConfigsManger;
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void check(CommandLine commandLine) {
        if (commandLine.hasOption("h")) {
            showHelp();
        } else if (commandLine.hasOption("v")) {
            System.out.println("Version: " + MainController.VERSION);
            System.exit(0);
        } else {
            checkMultiOptions(commandLine);
        }
    }

    private void checkMultiOptions(CommandLine commandLine) {
        if (commandLine.hasOption("d")) {
        ConfigsManger.getInstance().setDebug(true);
        }
        if (commandLine.hasOption("link")) {
            ConfigsManger.getInstance().getConfigs().setLink(true);
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
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("java -jar jcmusintegrationwithdiscord.jar [OPTIONS]", options);
        System.exit(0);
    }

    private void setupOptions() {
        options.addOption("h", "help", false, "Show this help");
        options.addOption("v", "version", false, "Show version");
        options.addOption("d", "debug", false, "Activate debug mode");
        options.addOption("link", false, "Linking with cmus (close the program if cmus is not running)");
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
