package com.company;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailFinder {

    private String txt;

    private static String word = "[\\w.'%+-]+";
    private static String local = word + "@";
    private static String subdomain = "." + word;
    private static String domain = "(?:" + subdomain + ")";
    private static String capture = "(" + local + domain + ")";
    private static Pattern pattern = Pattern.compile(" " + capture + " ");

    public EmailFinder(String input) {
        txt = input;
    }

    public int count() {
        int count = 0;
        Matcher matcher = pattern.matcher(txt);

        while (matcher.find()) {
            count += 1;
        }

        return count;
    }
}
