package com.unogwudan.util;


/**
 * Created by Daniel Unogwu on 21/06/20.
 */
public class CaseConverter {

    public static String capitalize(String value) {
        String[] words = value.trim().replaceAll("\\s+", " ").split("\\s");
        String[] newWords = new String[words.length];
        int j = 0;
        for (String word : words) {
            if (value.length() == 0) newWords[j] = word;
            else newWords[j] = (word.substring(0, 1).toUpperCase() + word.substring(1));
            j++;
        }
        return String.join(" ", newWords);
    }
}
