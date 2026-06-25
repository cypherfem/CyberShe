package com.ajith.practice;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter your sentence, or type exit to quit:");
            String userText = scanner.nextLine().toLowerCase();

            if (userText.equals("exit")) {
                break;
            }
            analyzeSentence(userText);


        }
        scanner.close();
        System.out.println("Program ended.");
    }
    public static void analyzeSentence(String userText) {

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
        emotionalWords.add("calculated");
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

        int appearanceCount = checkWords(userWords, appearanceWords, "Appearance");
        int emotionalCount = checkWords(userWords, emotionalWords, "Emotional/support/nurturing");
        int skillCount = checkWords(userWords, powerWords, "Power/skill/brains");
        int genderCount = checkWords(userWords, genderWords, "Gender pronouns");

        System.out.println("-- BIAS AGAINST WOMEN COUNT ---");
        System.out.println("Appearance count: " + appearanceCount);
        System.out.println("Emotional/support count: " + emotionalCount);
        System.out.println("Skill and Power count: " + skillCount);
        System.out.println("Gender pronoun count: " + genderCount);

        int finalBiasCount = appearanceCount + emotionalCount + genderCount - skillCount;

        System.out.println("Bias score: " + finalBiasCount);

        if (finalBiasCount <= 0) {
            System.out.println("Low bias detected");
        } else if (finalBiasCount <= 3) {
            System.out.println("Medium bias detected");
        } else {
            System.out.println("High bias detected");
        }

        System.out.println();

        if (appearanceCount > 0 && skillCount == 0) {
            System.out.println("Diagnosis: This sentence focuses on appearance however it never focuses on skill or intellect.");
        }

        if (emotionalCount > 0 && skillCount == 0) {
            System.out.println("Diagnosis: This sentence has more nurturing and caregiver words rather than skill words.");
        }

        if (skillCount > 0) {
            System.out.println("Diagnosis: This sentence has some power/skill language.");
        }

        String newText = rewriteWords(userText);

        if (!newText.equals(userText)) {
            System.out.println("Rewritten sentence: " + newText);
        } else {
            System.out.println("No rewrite changes needed based on current word list.");
        }


    }

    public static int checkWords(String[] userWords, ArrayList<String> wordList, String categoryName) {
        boolean foundAny = false;
        int count = 0;
        System.out.println(categoryName + " words found:");

        for (String w : userWords) {
            w = w.replaceAll("[^a-z]", "");

            if (wordList.contains(w)) {
                count++;
                foundAny = true;
                System.out.println("- " + w);


            }
        } if (!foundAny) {
            System.out.println("No words found.");
        }

        return count;
    }

    public static String rewriteWords(String userText) {
        int rewriteCount = 0;
        String newText = userText;

        if (newText.contains("calculated")) {
            rewriteCount++;
            newText = newText.replace("calculated", "strategic");
        }

        if (newText.contains("pretty")) {
            rewriteCount++;
            newText = newText.replace("pretty", "capable");
        }

        if (newText.contains("emotional")) {
            rewriteCount++;
            newText = newText.replace("emotional", "logical");
        }

        if (newText.contains("chick")) {
            rewriteCount++;
            newText = newText.replace("chick", "woman");
        }

        if (newText.contains("bossy")) {
            rewriteCount++;
            newText = newText.replace("bossy", "assertive");
        }

        if (newText.contains("submissive")) {
            rewriteCount++;
            newText = newText.replace("submissive", "cooperative");
        }

        if (rewriteCount > 0) {
            System.out.println("Rewrite changes made: " + rewriteCount);
        }

        return newText;
    }

}

