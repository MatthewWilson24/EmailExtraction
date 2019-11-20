package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class EmailFinder {

    private String txt;

    private static String word = "[\\w'%+-]+";
    private static String local = word + "@";
    private static String subdomain = "\\." + word;
    private static String domain = "(?:" + word + subdomain + ")";
    private static Pattern defaultPattern = Pattern.compile(wrap(local + domain));

    private static String wrap(String capture) {
        return "\\s(" + capture + ")(?=\\s)"; // Positive lookahead
    }

    EmailFinder(String input) {
        txt = input;
    }

    int count() {
        return count(defaultPattern);
    }

    int count(String searchDomain) {
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

    HashMap<String, Integer> countAll() {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        Matcher matcher = defaultPattern.matcher(txt);

        while (matcher.find()) {
            Address curr = new Address(matcher.group());
            map.put(curr.domain, map.getOrDefault(curr.domain, 0) + 1);
        }

        return map;
    }

    ArrayList<String> find() {
        return find(defaultPattern);
    }

    ArrayList<String> find(String searchDomain) {
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

    HashMap<String, ArrayList<String>> sortAll() {
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        Matcher matcher = defaultPattern.matcher(txt);

        while (matcher.find()) {
            Address curr = new Address(matcher.group());
            if (map.containsKey(curr.domain)) {
                ArrayList<String> list = map.get(curr.domain);
                list.add(curr.local);
            }
            else {
                map.put(curr.domain, new ArrayList<String>(Collections.singletonList(curr.local)));
            }
        }

        return map;
    }

    private static class Address {
        String address;
        String local;
        String domain;

        Address(String add) {
            address = add;
            local = firstMatch("([^@]*)@");
            domain = firstMatch("@(.*)");
        }

        private String firstMatch(String regex) {
            Matcher matcher = Pattern.compile(regex).matcher(address);
            matcher.find();
            return matcher.group();
        }
    }

}
