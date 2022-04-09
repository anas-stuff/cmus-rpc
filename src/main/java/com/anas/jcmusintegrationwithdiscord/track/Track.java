package com.anas.jcmusintegrationwithdiscord.track;

import java.util.HashMap;
import java.util.Map;

public class Track {
    private TrackInfo trackInfo;
    private Map<Tag, String> tags;

    public Track() {
        tags = new HashMap<>();
        trackInfo = null;
    }

    public TrackInfo getTrackInfo() {
        return trackInfo;
    }

    public Map<Tag, String> getTags() {
        return tags;
    }

    protected void setTrackInfo(TrackInfo trackInfo) {
        this.trackInfo = trackInfo;
    }

    protected void setTags(Map<Tag, String> tags) {
        this.tags = tags;
    }

    public String getTag(Tag key) {
        return tags.get(key);
    }

    public void update(Track track) {
        if ((this.trackInfo != null && track.getTrackInfo() != null && track.getTrackInfo().getFile() != null) &&
                track.getTrackInfo().getFile().equals(this.trackInfo.getFile())) {
            // Update the current time and the status if the file is the same
            this.trackInfo.setCurrentTime(track.getTrackInfo().getCurrentTime());
            this.trackInfo.setStatus(track.getTrackInfo().getStatus());
        } else {
            // Update the all attributes if the file is different
            this.trackInfo = track.getTrackInfo();
            this.tags = track.getTags();
        }
    }

    public static Track build(String response) {
        Track track = new Track();

        track.setTags(CMUSOutParserUtil.parse(response));
        track.setTrackInfo(new TrackInfo(response));

        return track;
    }

    public String timeToString() {
        return trackInfo.getCurrentTime().toString() + "/" + trackInfo.getDuration().toString();
    }
}
