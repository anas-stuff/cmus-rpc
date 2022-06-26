package com.anas.cmusrpc.util;

import com.anas.cmusrpc.track.Tag;

import java.util.EnumMap;
import java.util.Map;

/**
 * simple parser for `cmus-remote -Q` output
 */
public class CMUSOutParserUtil {

    private CMUSOutParserUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * It takes a string, splits it into lines, splits each line into words, and then uses the second word to look up a tag
     * in an enum, and then uses the third word as the value for that tag
     *
     * @param out the output of the command
     * @return A map of tags and their values.
     */
    public static Map<Tag, String> parse(String out) {
        Map<Tag, String> map = new EnumMap<>(Tag.class);
        Tag key = null;
        String value = null;
        String[] lines = out.split("\n");
        for (String line : lines) {
            String[] parts = line.split("\\s+");
            if (parts.length >= 3) {
                key = Tag.getTag(parts[1]);
                value = line.substring(line.indexOf(parts[2]));
            }
            if (parts[0].equals("tag") && key != null) { // skip non-tag lines and unknown tags
                map.put(key, value);
            }
        }
        return map;
    }
}
