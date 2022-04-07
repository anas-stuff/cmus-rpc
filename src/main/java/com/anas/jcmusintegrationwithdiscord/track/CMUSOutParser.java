package com.anas.jcmusintegrationwithdiscord.track;

import java.util.HashMap;
import java.util.Map;

public class CMUSOutParser {
    public static Map<Tag, String> parse(String out) {
        Map<Tag, String> map = new HashMap<>();
        Tag key = null;
        String value = null;
        String[] lines = out.split("\n");
        for (String line : lines) {
            if (!line.startsWith("tag ")) {
                continue; // skip non-tag lines
            }
            String[] parts = line.split(" ");
            if (parts.length >= 3) {
                key = Tag.getTag(parts[1]);
                if (key == null)
                    continue; // skip unknown tags
                value = parts[2];
            }
                map.put(key, value);
        }
        return map;
    }
}
