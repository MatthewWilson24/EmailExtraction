package com.company;

import java.io.IOException;
import java.nio.file.Paths;

import static java.nio.file.Files.readString;

public class Main {

    public static void main(String[] args) throws IOException {
        String txt = readString(Paths.get("src/sample.txt"));
        EmailCounter counter = new EmailCounter(txt);
        System.out.println(counter.runCount());
    }
}