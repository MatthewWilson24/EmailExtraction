package com.company;

import java.io.IOException;
import java.nio.file.Paths;

import static java.nio.file.Files.readString;

public class Main {

    public static void main(String[] args) throws IOException {
        String txt = readString(Paths.get("src/sample.txt"));
        EmailFinder counter = new EmailFinder(txt);
        System.out.println(counter.count());
    }
}