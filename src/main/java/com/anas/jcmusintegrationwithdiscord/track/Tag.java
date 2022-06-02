package com.anas.jcmusintegrationwithdiscord.track;

public enum Tag {
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
        return tag.equalsIgnoreCase(this.name().replace("_", ""));
    }

    public static Tag getTag(String tag) {
        for (Tag t : Tag.values()) {
            if (t.equals(tag)) {
                return t;
            }
        }
        return null;
    }
}
