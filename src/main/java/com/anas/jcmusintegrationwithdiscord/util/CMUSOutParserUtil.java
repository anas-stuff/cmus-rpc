package com.anas.jcmusintegrationwithdiscord.util;

import com.anas.jcmusintegrationwithdiscord.track.Tag;

import java.util.EnumMap;
import java.util.Map;

public class CMUSOutParserUtil {

    private CMUSOutParserUtil() {
        throw new IllegalStateException("Utility class");
    }

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
