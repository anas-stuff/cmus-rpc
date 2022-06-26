package com.anas.cmusrpc.util;

import com.anas.cmusrpc.track.Tag;
import com.anas.cmusrpc.track.Track;

/**
 * It replaces all the tags in the format string with the corresponding values from the track
 */
public class PartFormatterUtil {
    private PartFormatterUtil() {
        throw new IllegalStateException("Utility class");
    }
    /**
     * It replaces all the tags in the format string with the corresponding values from the track
     *
     * @param partFormat The format string to be formatted.
     * @param track The track to format
     * @return A string with the tags replaced with the values of the tags.
     */
    public static String format(final String partFormat,
                                final Track track) {
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
