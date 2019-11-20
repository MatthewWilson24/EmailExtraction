package com.company;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

import static java.nio.file.Files.readString;

public class Main {

    public static void main(String[] args) throws IOException {
        String txt = readString(Paths.get("src/sample.txt"));
        EmailFinder finder = new EmailFinder(txt);
        System.out.println(finder.count());
        System.out.println(finder.find());
        System.out.println(finder.count("softwire.com"));
        System.out.println(finder.find("softwire.com"));
    }
}