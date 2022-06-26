package com.anas.cmusrpc.track;

/**
 * TrackTime is a class that represents a track's time in minutes and seconds.
 */
public class TrackTime {
    private int minutes;
    private int seconds;

    /**
     * Constructor for TrackTime.
     *
     * @param timeInSeconds The time in seconds.
     */
    public TrackTime(final int timeInSeconds) {
        initialize(timeInSeconds);
    }

    /**
     * This function takes an integer representing a number of seconds and sets the minutes and seconds fields to the
     * correct values.
     *
     * @param timeInSeconds The time in seconds that you want to convert to minutes and seconds.
     */
    private void initialize(final int timeInSeconds) {
        this.minutes = timeInSeconds / 60;
        this.seconds = timeInSeconds % 60;
    }

    /**
     * This function returns the minutes of the time.
     *
     * @return The minutes of the time.
     */
    public int getMinutes() {
        return this.minutes;
    }

    /**
     * This function returns the seconds of the time.
     *
     * @return The number of seconds in the time.
     */
    public int getSeconds() {
        return this.seconds;
    }

    @Override
    public String toString() {
        return this.minutes + ":" + this.seconds;
    }
}
