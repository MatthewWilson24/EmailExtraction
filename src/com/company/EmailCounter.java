package com.company;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailCounter {

    private String txt;

    private static String domain = "[\\w.'%+-]+";
    private static Pattern pattern = Pattern.compile(" " + domain + "@" + domain + "\\." + domain);

    public EmailCounter(String input) {
        txt = input;
    }

    public int runCount() {
        int count = 0;
        Matcher matcher = pattern.matcher(txt);

        while (matcher.find()) {
            count += 1;
        }

        return count;
    }
}
