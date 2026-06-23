package com.ajith.practice;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("--BIAS AGAINST WOMEN ANALYZER");
        System.out.println("Enter your sentence:");

        String userText = scanner.nextLine().toLowerCase();

        ArrayList<String> appearanceWords = new ArrayList<String>();
        appearanceWords.add("beautiful");
        appearanceWords.add("pretty");
        appearanceWords.add("short");
        appearanceWords.add("skinny");
        appearanceWords.add("ugly");
        appearanceWords.add("attractive");
        appearanceWords.add("chick");


        ArrayList<String> emotionalWords = new ArrayList<String>();
        emotionalWords.add("kind");
        emotionalWords.add("helpful");
        emotionalWords.add("sensitive");
        emotionalWords.add("caring");
        emotionalWords.add("sweet");
        emotionalWords.add("mature");
        emotionalWords.add("gentle");
        emotionalWords.add("loving");
        emotionalWords.add("submissive");
        emotionalWords.add("bossy");
        emotionalWords.add("nurturing");

        ArrayList<String> powerWords = new ArrayList<String>();
        powerWords.add("skilled");
        powerWords.add("technical");
        powerWords.add("leader");
        powerWords.add("confident");
        powerWords.add("boss");
        powerWords.add("assertive");
        powerWords.add("clever");
        powerWords.add("sharp");
        powerWords.add("powerful");
        powerWords.add("dominant");
        powerWords.add("loud");
        powerWords.add("expert");
        powerWords.add("engineer");
        powerWords.add("mathematician");
        powerWords.add("capable");

        ArrayList<String> genderWords = new ArrayList<String>();
        genderWords.add("she");
        genderWords.add("her");
        genderWords.add("hers");
        genderWords.add("woman");
        genderWords.add("women");
        genderWords.add("girl");
        genderWords.add("girls");
        genderWords.add("female");
        genderWords.add("lady");
        genderWords.add("ms");
        genderWords.add("mrs");
        genderWords.add("ladies");

        String[] userWords = userText.split(" ");

        System.out.println();
        System.out.println("--- Analysis Result ----");

        checkWords(userWords, appearanceWords, "Appearance");
        checkWords(userWords, emotionalWords, "Emotional/support/nuturing");
        checkWords(userWords, powerWords, "Power/skill/brains");
        checkWords(userWords, genderWords, "Gender prnouns");

        scanner.close();
    }

    public static void checkWords(String[] userWords, ArrayList<String> wordList, String categoryName) {
        boolean foundAny = false;
        System.out.println(categoryName + " words found:");

        for (String w : userWords) {
            w = w.replaceAll("[^a-z]", "");

            if (wordList.contains(w)) {
                foundAny = true;
                System.out.println("- " + w);


        }
        } if (foundAny == false) {
            System.out.println("No words found.");
        }

    }
}
