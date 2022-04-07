package com.anas.jcmusintegrationwithdiscord.track;

import com.anas.jcmusintegrationwithdiscord.Displayable;

public enum Tag implements Displayable {
    TITLE,
    ALBUM,
    ARTIST,
    TRACK_NUMBER,
    DISC_NUMBER,
    ALBUM_ARTIST,
    GENRE,
    DATE,
    LABEL;

    public boolean equals(String tag) {
        return tag.equalsIgnoreCase(this.name().replaceAll("_", ""));
    }

    public static Tag getTag(String tag) {
        for (Tag t : Tag.values()) {
            if (t.equals(tag)) {
                return t;
            }
        }
        return null;
    }

    @Override
    public String getDisplayName() {
        return this.name().replaceAll("_", " ");
    }
}
