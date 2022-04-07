package com.anas.jcmusintegrationwithdiscord.track;

import java.util.Map;

public class Track {
    private final TrackInfo trackInfo;
    private final Map<Tag, String> tags;

    public Track(String response) {
        tags = CMUSOutParser.parse(response);
        trackInfo = new TrackInfo(response);
    }

    public TrackInfo getTrackInfo() {
        return trackInfo;
    }

    public Map<Tag, String> getTags() {
        return tags;
    }

    public String getTag(Tag key) {
        return tags.get(key);
    }
}
