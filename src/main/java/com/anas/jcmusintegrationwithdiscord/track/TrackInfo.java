package com.anas.jcmusintegrationwithdiscord.track;

import java.io.File;

public class TrackInfo {
    private Status status;
    private File file;
    private int duration, position;

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
                 case "file" -> file = new File(parts[1]);
                 case "duration" -> duration = Integer.parseInt(parts[1]);
                 case "position" -> position = Integer.parseInt(parts[1]);
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

    public int getDuration() {
        return duration;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
