package com.anas.jcmusintegrationwithdiscord.util;

import com.anas.jcmusintegrationwithdiscord.track.Tag;
import com.anas.jcmusintegrationwithdiscord.track.Track;

public class PartFormatterUtil {
    private PartFormatterUtil() {
        throw new IllegalStateException("Utility class");
    }
    public static String format(String partFormat, Track track) {
        return partFormat
                // Tags
                .replace("%title%", track.getTrackName())
                .replace("%artist%", track.getTag(Tag.ARTIST))
                .replace("%album%", track.getTag(Tag.ALBUM))
                .replace("%genre%", track.getTag(Tag.GENRE))
                .replace("%date%", track.getTag(Tag.DATE))
                .replace("%album artist%", track.getTag(Tag.ALBUM_ARTIST))
                .replace("%disc number%", track.getTag(Tag.DISC_NUMBER))
                .replace("%track number%", track.getTag(Tag.TRACK_NUMBER))
                .replace("%track number%", track.getTag(Tag.TRACK_NUMBER))
                // Basic info
                .replace("%file%", track.getTrackInfo().getFile().getName())
                .replace("%file path%", track.getTrackInfo().getFile().getAbsolutePath())
                .replace("%file size%", track.getTrackInfo().getFile().length() + "")
                .replace("%duration%", track.getTrackInfo().getDuration() + "")
                .replace("%current position%", track.getTrackInfo().getCurrentTime() + "");
    }
}
