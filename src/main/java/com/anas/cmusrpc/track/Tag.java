package com.anas.cmusrpc.track;

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

    /**
     * If the tag is equal to the name of the enum, return true.
     *
     * @param tag The tag to compare to.
     * @return The boolean value of the comparison of the tag and the name of the enum.
     */
    public boolean equals(String tag) {
        return tag.equalsIgnoreCase(this.name().replace("_", ""));
    }

    /**
     * If the tag is in the list of tags, return the tag, otherwise return null.
     *
     * @param tag The tag you want to get.
     * @return The tag that matches the tag passed in.
     */
    public static Tag getTag(String tag) {
        for (Tag t : Tag.values()) {
            if (t.equals(tag)) {
                return t;
            }
        }
        return null;
    }
}
