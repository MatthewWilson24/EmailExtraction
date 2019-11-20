package com.company;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailFinder {

    private String txt;

    private static String word = "[\\w.'%+-]+";
    private static String local = word + "@";
    private static String subdomain = "\\." + word;
    private static String domain = "(?:" + word + subdomain + ")";
    private static Pattern defaultPattern = Pattern.compile(wrap(local + domain));

    private static String wrap(String capture) {
        return "\\s(" + capture + ")\\s";
    }

    public EmailFinder(String input) {
        txt = input;
    }

    public int count() {
        return count(defaultPattern);
    }

    public int count(String searchDomain) {
        return count(Pattern.compile(wrap(local + searchDomain)));
    }

    private int count(Pattern pattern) {
        int count = 0;
        Matcher matcher = pattern.matcher(txt);

        while (matcher.find()) {
            count += 1;
        }

        return count;
    }

    public ArrayList<String> find() {
        return find(defaultPattern);
    }

    public ArrayList<String> find(String searchDomain) {
        return find(Pattern.compile(wrap(local + searchDomain)));
    }

    private ArrayList<String> find(Pattern pattern) {
        ArrayList<String> matched = new ArrayList<String>();
        Matcher matcher = pattern.matcher(txt);

        while (matcher.find()) {
            matched.add(matcher.group());
        }

        return matched;
    }
}
