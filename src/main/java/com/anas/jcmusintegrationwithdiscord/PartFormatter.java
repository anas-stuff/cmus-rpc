package com.anas.jcmusintegrationwithdiscord;

import com.anas.jcmusintegrationwithdiscord.track.Tag;
import com.anas.jcmusintegrationwithdiscord.track.Track;

public class PartFormatter {
    public static String format(String partFormat, Track track) {
        return partFormat
                // Tags
                .replaceAll("%title%", track.getTag(Tag.TITLE))
                .replaceAll("%artist%", track.getTag(Tag.ARTIST))
                .replaceAll("%album%", track.getTag(Tag.ALBUM))
                .replaceAll("%genre%", track.getTag(Tag.GENRE))
                .replaceAll("%date%", track.getTag(Tag.DATE))
                .replaceAll("%album artist%", track.getTag(Tag.ALBUM_ARTIST))
                .replaceAll("%disc number%", track.getTag(Tag.DISC_NUMBER))
                .replaceAll("%track number%", track.getTag(Tag.TRACK_NUMBER))
                .replaceAll("%track number%", track.getTag(Tag.TRACK_NUMBER))

                // Basic info
                .replaceAll("%file%", track.getTrackInfo().getFile().getName())
                .replaceAll("%file path%", track.getTrackInfo().getFile().getAbsolutePath())
                .replaceAll("%file size%", track.getTrackInfo().getFile().length() + "")
                .replaceAll("%duration%", track.getTrackInfo().getDuration() + "")
                .replaceAll("%current postion%", track.getTrackInfo().getDuration() + "");
    }
}
