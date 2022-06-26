package com.anas.cmusrpc.track;

public class TrackTime {
    private int minutes;
    private int seconds;

    public TrackTime(int timeInSeconds) {
        initialize(timeInSeconds);
    }

    private void initialize(int timeInSeconds) {
        this.minutes = timeInSeconds / 60;
        this.seconds = timeInSeconds % 60;
    }

    public int getMinutes() {
        return this.minutes;
    }

    public int getSeconds() {
        return this.seconds;
    }

    public String toString() {
        return this.minutes + ":" + this.seconds;
    }
}
