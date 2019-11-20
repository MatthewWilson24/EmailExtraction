package com.company;

import java.util.ArrayList;
import java.util.Arrays;

public class EmailCounter {

    int count;
    int charNum;
    String txt;
    Character currChar;
    int length;
    CheckStage stage;

    static ArrayList<Character> specialChars = new ArrayList<Character>(Arrays.asList('\'', '_', '%', '+', '-'));
    static CheckStage[] stageList = CheckStage.values();

    public EmailCounter(String input) {
        count = 0;
        charNum = 0;
        txt = input;
        currChar = txt.charAt(0);
        length = txt.length();
        stage = CheckStage.LOCALSTART;
    }

    private enum CheckStage {
        LOCALSTART, LOCALEND, SUB1START, SUB1END, SUB2START
    }

    public int runCount() {
        count = 0;

        while (charNum < length) {
            currChar = txt.charAt(charNum);

            switch (stage) {
                case LOCALSTART:
                    if (isValidChar(currChar)) {
                        next();
                    }
                    break;

                case LOCALEND:
                    if (currChar.equals('@')) {
                        next();
                    }
                    else if (!isValidChar(currChar)) {
                        reset();
                    }
                    break;

                case SUB1START:
                    if (isValidChar(currChar)) {
                        next();
                    }
                    else {
                        reset();
                    }
                    break;

                case SUB1END:
                    if (currChar.equals('.')) {
                        next();
                    }
                    else if (!isValidChar(currChar)) {
                        reset();
                    }
                    break;

                case SUB2START:
                    if (isValidChar(currChar)) {
                        count += 1;
                    }
                    reset();
                    break;
            }

            charNum += 1;
        }

        return count;
    }

    private void next() {
        stage = stageList[(stage.ordinal()) + 1];
    }

    private void reset() {
        stage = CheckStage.LOCALSTART;
    }

    private static boolean isValidChar(Character c) {
        return Character.isLetter(c) || Character.isDigit(c) || specialChars.contains(c);
    }
}
