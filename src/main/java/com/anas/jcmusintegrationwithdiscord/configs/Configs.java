package com.anas.jcmusintegrationwithdiscord.configs;

import com.anas.jcmusintegrationwithdiscord.Displayable;

import java.util.HashMap;
import java.util.Map;

public class Configs {
    private int interval;
    private Map<Displayable, Boolean> display;

    private static Configs instance;

    private Configs() {
        interval = 1000; // 1 second
        display = new HashMap<>();
    }

    public static Configs getInstance() {
        if (instance == null) {
            instance = new Configs();
        }
        return instance;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public Map<Displayable, Boolean> getDisplay() {
        return display;
    }

    public void setDisplay(Map<Displayable, Boolean> display) {
        this.display = display;
    }
}
