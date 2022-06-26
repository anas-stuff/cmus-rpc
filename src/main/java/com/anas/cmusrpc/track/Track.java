package com.anas.cmusrpc.track;

import com.anas.cmusrpc.util.CMUSOutParserUtil;

import java.util.EnumMap;
import java.util.Map;

public class Track {
    private TrackInfo trackInfo;
    private Map<Tag, String> tags;

    public Track() {
        tags = new EnumMap<>(Tag.class);
        trackInfo = null;
    }

    /**
     * This function returns the trackInfo object.
     *
     * @return The trackInfo object.
     */
    public TrackInfo getTrackInfo() {
        return trackInfo;
    }

    /**
     * Returns a map of tags to values.
     *
     * @return A map of tags and their values.
     */
    public Map<Tag, String> getTags() {
        return tags;
    }

    /**
     * > This function sets the trackInfo variable to the trackInfo parameter
     *
     * @param trackInfo The track info object that contains the track's information.
     */
    protected void setTrackInfo(final TrackInfo trackInfo) {
        this.trackInfo = trackInfo;
    }

    /**
     * > This function sets the tags of the current object to the tags passed in as a parameter
     *
     * @param tags A map of tags to be added to the resource.
     */
    protected void setTags(final Map<Tag, String> tags) {
        this.tags = tags;
    }

    /**
     * If the key is in the map, return the value, otherwise return "Unknown"
     *
     * @param key The key of the tag you want to get.
     * @return The value of the tag if it exists, otherwise "Unknown".
     */
    public String getTag(final Tag key) {
        if (tags.containsKey(key)) {
            return tags.get(key);
        }
        return "Unknown";
    }

    /**
     * If the file is the same, update the current time and status only, otherwise update all attributes
     *
     * @param track The track object that is being updated.
     */
    public void update(final Track track) {
        if ((this.trackInfo != null &&
                track.getTrackInfo() != null &&
                track.getTrackInfo().getFile() != null) &&
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

    /**
     * It takes a string, parses it, and returns a Track object
     *
     * @param response The response from the cmus-remote command
     * @return A Track object
     */
    public static Track build(final String response) {
        final var track = new Track();

        track.setTags(CMUSOutParserUtil.parse(response));
        track.setTrackInfo(new TrackInfo(response));

        return track;
    }

    /**
     * If the track has a title, return it. Otherwise, return the name of the file
     *
     * @return The name of the track.
     */
    public String getTrackName() {
        var name = getTag(Tag.TITLE);
        if (name.equals("Unknown")) {
            // Getting the name of the file and removing the extension.
            name = trackInfo.getFile().getName()
                    .substring(0, trackInfo.getFile().getName().lastIndexOf("."));
        }
        return name;
    }
}
