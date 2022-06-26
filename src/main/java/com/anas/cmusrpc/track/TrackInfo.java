package com.anas.cmusrpc.track;

import java.io.File;

public class TrackInfo {
    private Status status;
    private File file;
    private TrackTime duration;
    private TrackTime currentTime;

    // Defining an enum represent the status of track
    public enum Status {
        PLAYING,
        PAUSED,
        STOPPED
    }

    /**
     * Create track info object
     * @param output the output of the `cmus-remote -Q` command
     */
    protected TrackInfo(String output) {
        init(output);
    }

    /**
     * It takes the output of the `cmus-remote -Q` command and get the base file info from it.
     * like the current status and the track file path and the duration and the current position.
     *
     * @param output The output of the command `cmus-remote -Q`
     */
    private void init(String output) {
        String[] lines = output.split("\n");
        boolean end = false;
        for (String line : lines) {
            String[] parts = line.split(" ");
            switch (parts[0].toLowerCase()) {
                case "status" -> status = Status.valueOf(parts[1].toUpperCase());
                case "file" -> file = new File(line.substring(line.indexOf("file ")));
                case "duration" -> duration = new TrackTime(Integer.parseInt(parts[1]));
                case "position" -> currentTime = new TrackTime(Integer.parseInt(parts[1]));
                default -> end = true;
            }
            if (end) {
                break; // break the loop because we don't need to parse the rest of the lines
            }
        }
    }

    /**
     * Returns the status of the track.
     *
     * @return The status.
     */
    public Status getStatus() {
        return status;
    }

    /**
     * This function sets the status of track
     *
     * @param status The status of the track
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * This function returns the file.
     *
     * @return The track file
     */
    public File getFile() {
        return file;
    }

    /**
     * This function returns the duration of the track
     *
     * @return The duration of the track.
     */
    public TrackTime getDuration() {
        return duration;
    }

    /**
     * This function returns the current time of the track.
     *
     * @return The current time of the track.
     */
    public TrackTime getCurrentTime() {
        return currentTime;
    }

    /**
     * This function sets the current time of the track.
     *
     * @param currentTime The current time of the track.
     */
    public void setCurrentTime(TrackTime currentTime) {
        this.currentTime = currentTime;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof TrackInfo track)) return false;

        return ((this.currentTime != null && track.getCurrentTime() != null) &&
                (this.currentTime.getMinutes() == track.getCurrentTime().getMinutes() &&
                        this.currentTime.getSeconds() == track.getCurrentTime().getSeconds())) &&
                ((this.duration != null && track.getDuration() != null) &&
                        (this.duration.getMinutes() == track.getDuration().getMinutes() &&
                                this.duration.getSeconds() == track.getDuration().getSeconds())) &&
                ((this.file != null && track.getFile() != null) &&
                        this.getFile().getName().equals(track.getFile().getName()));
    }
}
