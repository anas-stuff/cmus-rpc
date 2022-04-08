package com.anas.jcmusintegrationwithdiscord.track;

import java.io.File;

public class TrackInfo {
    private Status status;
    private File file;
    private TrackTime duration, currentTime;

    public static enum Status {
        PLAYING,
        PAUSED,
        STOPPED
    }

    protected TrackInfo(String output) {
        init(output);
    }

    private void init(String output) {
        String[] lines = output.split("\n");
        loop: for (String line : lines) {
            String[] parts = line.split(" ");
             switch (parts[0].toLowerCase()) {
                 case "status" -> status = Status.valueOf(parts[1].toUpperCase());
                 case "file" -> file = new File(line.substring(line.indexOf("file ")));
                 case "duration" -> duration = new TrackTime(Integer.parseInt(parts[1]));
                 case "position" -> currentTime = new TrackTime(Integer.parseInt(parts[1]));
                 default -> {
                     break loop; // break the loop because we don't need to parse the rest of the lines
                 }
             }
        }
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public File getFile() {
        return file;
    }

    public TrackTime getDuration() {
        return duration;
    }

    public TrackTime getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(TrackTime currentTime) {
        this.currentTime = currentTime;
    }

    @Override
    public boolean equals(Object o) {
        TrackInfo track = (TrackInfo) o;
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
